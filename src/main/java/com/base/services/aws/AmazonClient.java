package com.base.services.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class AmazonClient {

    private static final Logger logger = LoggerFactory.getLogger(AmazonClient.class);


    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazon.s3.default-bucket}")
    private String bucketName;
    @Value("${amazon.aws.access-key-id}")
    private String accessKey;
    @Value("${amazon.aws.access-key-secret}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3client = AmazonS3ClientBuilder.standard().withRegion("sa-east-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
//        ClientConfiguration clientConfig = new ClientConfiguration();
//        clientConfig.setProtocol(Protocol.HTTP);
//        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
//        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.SA_EAST_1).build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {

        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadBase64(String base64Data, Long userID) {

        try {
            byte[] imageByte = org.apache.commons.codec.binary.Base64.decodeBase64((base64Data.substring(base64Data.indexOf(",") + 1)).getBytes());


            String fileName = "";
            BufferedImage image = null;
            InputStream fis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(fis);
            fis.close();
            File outputfile = new File("image.jpg");
            ImageIO.write(image, "jpg", outputfile);

            //Resize da imagem
//            outputfile = imageRisize.resize(outputfile, true, 400);


            //Set metadata for object
            //ObjectMetadata metadata = new ObjectMetadata();
            //metadata.setContentLength(bbI.length);
            //metadata.setContentType("image/jpeg");
            //metadata.setCacheControl("public, max-age=31536000");

            fileName = fileName + new Date().getTime() + "-" + "Profile-" + userID.toString();

            //
            //s3client.putObject(bucketName, fileName, fis, metadata);
            // s3client.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);

            uploadFileTos3bucket(fileName, outputfile);

            //Generate Url to image
            String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            return null;
        }
    }

    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = null;
        String pathname = "";

        try {

            File file = convertMultiPartToFile(multipartFile);
            String fileOrigeNamePath = file.getName();
            String fileName = generateFileName(multipartFile);

            uploadFileTos3bucket(fileName, file);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
        }

        return fileUrl;
    }

    public String uploadFileImage(MultipartFile multipartFile, boolean isThumb) {

        String fileUrl = null;
        String pathname = "";


        try {


            File file = convertMultiPartToFile(multipartFile);
            String fileOrigeNamePath = file.getName();
            String fileName = generateFileName(multipartFile);

//          recize da imagem
//            if (isThumb) {
//                file = imageRisize.resize(file, true, 400);
//                pathname = "thumb.jpg";
//            }
//            else {
//
//                file = imageRisize.resize(file, false, 1280);
//                pathname = "description.jpg";
//            }

            uploadFileTos3bucket(fileName, file);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            file.delete();
            Files.delete(Paths.get(fileOrigeNamePath));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
        }

        return fileUrl;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }

}
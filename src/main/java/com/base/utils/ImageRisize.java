//package com.icare.utils;
//
//
//import Catalano.Imaging.FastBitmap;
//import Catalano.Imaging.Filters.ResizeBicubic;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//public class ImageRisize {
//
//    public static File resize(File image, boolean thumb, int size)
//
//            throws IOException {
//        // reads input image
//        BufferedImage inputImage = ImageIO.read(image);
//        File file = new File("image.jpg");
//        //double percent = 0.5;
//
//        int imageWidth = (int) (inputImage.getWidth());
//        int imageHeight = (int) (inputImage.getHeight());
//        // int sizeImage = (int) image.length();
//        //sizeImage = (int) (sizeImage/1000);
//        FastBitmap fb = new FastBitmap(inputImage);
//
//
//        if (thumb) {
//
//            if (imageWidth > 400) {
//
//                if (imageWidth >= imageHeight) {
//                    imageHeight = size * (imageWidth / imageHeight);
//                    imageWidth = size;
//                } else {
//                    imageWidth = size * (imageHeight / imageWidth);
//                    imageHeight = size;
//                }
//
//                ResizeBicubic bicubic = new ResizeBicubic(imageWidth, imageHeight);
//
//                FastBitmap temp = bicubic.apply(fb);
//                String pathname = "thumb.jpg";
//                temp.saveAsJPG(pathname, 0.80f);
//                file = new File(pathname);
//                return file;
//
//            }
//
////            if (imageHeight != 400 || imageWidth != 400) {
////                BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, inputImage.getType());
////                Graphics2D g2d = outputImage.createGraphics();
////                g2d.drawImage(inputImage, 0, 0, imageWidth, imageHeight, null);
////                g2d.dispose();
////
////                String pathname = "thumb.jpg";
////                FastBitmap fb = new FastBitmap(outputImage);
////                fb.saveAsJPG(pathname, 0.80f);
////                file = new File(pathname);
////
////                return file;
////
////            }
//
//        } else if (imageHeight > 1280 || imageWidth > 720) {
//
//
//            ResizeBicubic bicubic = new ResizeBicubic(1280, 720);
//
//            FastBitmap temp = bicubic.apply(fb);
//            String pathname = "description.jpg";
//            temp.saveAsJPG(pathname, 0.70f);
//            file = new File(pathname);
//            return file;
//
//        }
//
//        return image;
//    }
//
//}

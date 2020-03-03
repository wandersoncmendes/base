package com.base.utils;


import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;

/**
 * Classe que cria pdfs
 */
public class ThymeleafUtils {


    /**
     * converte o template Thymeleaf para pdf
     *
     * @param template
     * @param marcaDagua
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static byte[] watermarkPdf(String template, String marcaDagua) throws IOException, DocumentException {

        ITextRenderer renderer = new ITextRenderer();

        String xHtml = convertToXhtml(template);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "test", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();
        renderer.createPDF(out);
        out.close();


        PdfReader reader = new PdfReader(out.toByteArray());
        PdfStamper stamper = new PdfStamper(reader, out);
        // image watermark
        Image img = Image.getInstance(marcaDagua);
        float w = (float) (img.getScaledWidth() * 0.5);
        float h = (float) (img.getScaledHeight() * 0.5);

        // properties
        PdfContentByte over;
        Rectangle pagesize;
        float x, y;

        // loop over every page
        int n = reader.getNumberOfPages();
        for (int i = 1; i <= n; i++) {

            // get page size and position
            pagesize = reader.getPageSizeWithRotation(i);
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = stamper.getOverContent(i);
            over.saveState();

            // set transparency
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.1f);
            over.setGState(state);

            // add watermark text and image
            over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));


            over.restoreState();
        }
        stamper.close();
        reader.close();


        return out.toByteArray();


    }

    /**
     * converte o template Thymeleaf para pdf
     *
     * @param template
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static byte[] generatePdf(String template) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String xHtml = convertToXhtml(template);

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "test", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        return out.toByteArray();


    }

    /**
     * converte o html para xhtml
     *
     * @param html
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }


}
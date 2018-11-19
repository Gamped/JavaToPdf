package com.mathiasgam.JavaToPdfTest;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;

public class QuickTest {
    public static void main(String[] args){
        new QuickTest().createPdfTest();
    }

    public void createPdfTest(){
        String fileLocation = System.getProperty("user.home") + "/Desktop/";
        String name = "TEST.pdf";
        PDDocument document = new PDDocument();

        try {
        // Create page & fill in text into the document
        PDPage testPage = new PDPage();
        PDPageContentStream cs = new PDPageContentStream(document, testPage);

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 12);
        cs.newLineAtOffset(25, 700);
        cs.setLeading(14);
        cs.showText("HELLO WORLD!");
        cs.newLine();
        cs.showText("This document were created by code in Java :D");
        cs.endText();
        cs.close();

        document.addPage(testPage);

        // Save the document
            document.save(fileLocation + name);
            document.close();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }
}

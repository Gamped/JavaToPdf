package com.mathiasgam.PdfCreator;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.*;

// Abstract class for creating .pdf files using Apache PDFBox
public abstract class PDFCreator {
    protected String filePath, fileName;
    protected PDDocument doc = new PDDocument();
    protected ArrayList<PDPage> pages = new ArrayList<>();
    protected PDDocumentInformation pdi = doc.getDocumentInformation();
    protected PDPageContentStream cs;
    protected int X_OFFSET;

    // @param FilePath: The location of the file
    // @param FileName: The name of the file (DO NOT! end with .pdf)
    PDFCreator(String FilePath, String FileName, int xOffset){
        filePath = FilePath;
        fileName = FileName;
        X_OFFSET = xOffset;

        pages.add(new PDPage());
        doc.addPage(pages.get(0));
        cs = setupCS();

        setCreationDay();
    }

    private PDPageContentStream setupCS(){
        try {
            return new PDPageContentStream(doc, pages.get(0));
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return null;
    }

    private void setCSFont(int fontSize){
        try {
            cs.setFont(PDType1Font.TIMES_ROMAN, fontSize);
            cs.setLeading(fontSize + 5f);
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }

    public PDFCreator setCreationDay(){
        Calendar date = new GregorianCalendar();
        pdi.setCreationDate(date);
        return this;
    }

    public PDFCreator setTitle(String Title){
        pdi.setTitle(Title);
        return this;
    }

    public PDFCreator setAuthor(String Author){
        pdi.setAuthor(Author);
        return this;
    }

    public PDFCreator setSubject(String Subject){
        pdi.setSubject(Subject);
        return this;
    }

    public PDFCreator writeTitle(String Title, int y){
        try {
            cs.beginText();
            setCSFont(25);
            cs.newLineAtOffset(X_OFFSET, y);
            cs.showText(Title);
            cs.endText();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }

    public PDFCreator makeLine(char c, int amount, int y){
        String line = "";
        try{
            for (int i = 0; i < amount; i++){line += c;}
            cs.beginText();
            setCSFont(14);
            cs.newLineAtOffset(X_OFFSET, y);
            cs.showText(line);
            cs.endText();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }

    // Note this is the default implementation to write lines, however it is
    // encouraged to overwrite this to make it fit with styling etc.
    public PDFCreator writeLines(LinkedList<String> Lines, int yOffset){
        try {
            cs.beginText();
            setCSFont(12);
            cs.newLineAtOffset(X_OFFSET, yOffset);
            for (String s: Lines) {
                cs.showText(s);
                cs.newLine();
            }
            cs.endText();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }

    private String exactFilePath(){return filePath + "/" + fileName + ".pdf";}

    // Note this HAS to be the last function called
    public void saveFile(){
        try {
            cs.close();
            doc.save(exactFilePath());
            doc.close();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }
}

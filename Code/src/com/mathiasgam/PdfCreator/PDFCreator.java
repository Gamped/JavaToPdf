package com.mathiasgam.PdfCreator;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.*;

// Abstract class for creating .pdf files using Apache PDFBox
public abstract class PDFCreator {
    private String filePath, fileName;
    private PDDocument doc = new PDDocument();
    private ArrayList<PDPage> pages = new ArrayList<>();
    private PDDocumentInformation pdi = doc.getDocumentInformation();
    private PDPageContentStream cs;

    // @param FilePath: The location of the file
    // @param FileName: The name of the file (DO NOT! end with .pdf)
    PDFCreator(String FilePath, String FileName){
        this.filePath = FilePath;
        this.fileName = FileName;
        pages.add(new PDPage());
        doc.addPage(pages.get(0));
        setCreationDay();
        cs = setupCS();
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

    public PDFCreator setCreationDay(){
        Calendar date = new GregorianCalendar();
        pdi.setCreationDate(date);
        return this;
    }

    private PDPageContentStream setupCS(){
        try {
            return new PDPageContentStream(doc, pages.get(0));
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return null;
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

    private void setCSFont(PDPageContentStream stream, int fontSize){
        try {
            stream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
            stream.setLeading(fontSize + 5f);
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }

    public PDFCreator writeTitle(String Title){
        try {
            cs.beginText();
            setCSFont(cs, 25);
            cs.newLineAtOffset(30, 725);
            cs.showText(Title);
            cs.endText();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }

    // Note this is the default implementation to write lines, however it is
    // encouraged to overwrite this to make it fit with styling etc.
    public PDFCreator writeLines(LinkedList<String> Lines, int yOffset){
        try {
            cs.beginText();
            setCSFont(cs, 12);
            cs.newLineAtOffset(30, yOffset);
            for (String s: Lines) {
                cs.showText(s);
                cs.newLine();
            }
            cs.endText();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }
}

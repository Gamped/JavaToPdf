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

    // @param FilePath: The location of the file
    // @param FileName: The name of the file (DO NOT! end with .pdf)
    PDFCreator(String FilePath, String FileName){
        this.filePath = FilePath;
        this.fileName = FileName;
        pages.add(new PDPage());
        doc.addPage(pages.get(0));
        setCreationDay();
    }

    public PDFCreator setTitle(String Title){
        PDDocumentInformation pdi = doc.getDocumentInformation();
        pdi.setTitle(Title);
        return this;
    }

    public PDFCreator setAuthor(String Author){
        PDDocumentInformation pdi = doc.getDocumentInformation();
        pdi.setAuthor(Author);
        return this;
    }

    public PDFCreator setSubject(String Subject){
        PDDocumentInformation pdi = doc.getDocumentInformation();
        pdi.setSubject(Subject);
        return this;
    }

    public PDFCreator setCreationDay(){
        PDDocumentInformation pdi = doc.getDocumentInformation();
        Calendar date = new GregorianCalendar();
        pdi.setCreationDate(date);
        return this;
    }

    // Creates the exact path for the file
    private String exactFilePath(){return filePath + "/" + fileName + ".pdf";}

    // Saves the file
    public void saveFile(){
        try {
            doc.save(exactFilePath());
            doc.close();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }

    // Provide default styling to content stream
    private void defaultContentSteam(PDPageContentStream contentStream, int fontSize){
        try {
            contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
            contentStream.setLeading(fontSize + 5f);
            contentStream.newLineAtOffset(25, 725);
        } catch (IOException e) {System.out.print("ERROR: " + e);}
    }

    public PDFCreator writeTitle(String Title){
        try {
            PDPageContentStream cs = new PDPageContentStream(doc, pages.get(0));
            cs.beginText();
            defaultContentSteam(cs, 25);
            cs.showText(Title);
            cs.endText();
            cs.close();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }

    // TODO: Make this function work actually output stuff :D (and make abstract after)
    public PDFCreator writeLines(LinkedList<String> Lines){
        try {
            PDPageContentStream cs = new PDPageContentStream(doc, pages.get(0), PDPageContentStream.AppendMode.APPEND, true);
            cs.beginText();
            defaultContentSteam(cs, 12);
            for (String s: Lines) {
                cs.showText(s);
                cs.newLine();
            }
            cs.close();
        } catch (IOException e) {System.out.print("ERROR: " + e);}
        return this;
    }
}

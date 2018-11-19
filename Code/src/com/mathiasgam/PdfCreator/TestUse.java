package com.mathiasgam.PdfCreator;

import java.util.LinkedList;

// This class is used to test if the PDFCreator works :)
public class TestUse {
    PDFCreator creator = new PluckListCreator(System.getProperty("user.home") + "/Desktop/", "TestDocument");

    public static void main(String[] args){new TestUse().makeFile();}

    private void makeFile(){
        LinkedList<String> lines = new LinkedList<>();

        lines.add("This is line 1");
        lines.add("This is line 2");
        lines.add("This is line 3");


        creator .setAuthor("Mathias")
                .setTitle("A test document")
                .setSubject("Creating awesome .pdf files")
                .writeTitle("HELLO WORLD")
                .writeLines(lines)
                .saveFile();
    }
}

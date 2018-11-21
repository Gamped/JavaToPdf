package com.mathiasgam.PdfCreator;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

// This class is used to test if the PDFCreator works :)
public class TestUse {
    private SecurePDFCreator creator = new PluckListCreator(System.getProperty("user.home") + "/Desktop/", "TestDocument", 30);

    public static void main(String[] args){new TestUse().makeFile();}

    // This is the test case for
    private void makeFile(){
        LinkedList<String> lines = new LinkedList<>();
        int randomResult;
        String productName;

        for (int i = 0; i < 15; i++){
            randomResult = ThreadLocalRandom.current().nextInt(0, 10000);
            productName = "";
            for (int j = 0; j < 10; j++){
                productName +=  (char)(ThreadLocalRandom.current().nextInt(0, 26) + 'A');
            }
            lines.add(  "ID: " + randomResult +
                        " | " +
                        " Product name: " + productName +
                        " | Packed? [ ]");
        }

        creator.protectDocument("123"); // Super secure password (luckily only used for test :)
        creator.setAuthor("A great company")
                .setTitle("Pluck-list")
                .setSubject("Pluck-list for order 4648")
                .writeTitle("Pluck-list: Order 4648", 720)
                .makeLine('_', 70, 715)
                .writeLines(lines, 695)
                .saveFile();
    }
}

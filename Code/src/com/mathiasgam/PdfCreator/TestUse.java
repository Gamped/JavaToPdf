package com.mathiasgam.PdfCreator;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

// This class is used to test if the PDFCreator works :)
public class TestUse {
    private DeliveryNoteCreator creator = new DeliveryNoteCreator(System.getProperty("user.home") + "/Desktop/",
                                                    "TestDocument", 50);

    public static void main(String[] args){new TestUse().makeFile();}

    // This is the test case for
    private void makeFile(){
        LinkedList<String> lines = new LinkedList<>();
        int ID, amount;
        String productName;

        for (int i = 0; i < 15; i++){
            ID = ThreadLocalRandom.current().nextInt(0, 90000);
            amount = ThreadLocalRandom.current().nextInt(0, 999);
            productName = "";
            for (int j = 0; j < 10; j++){
                productName +=  (char)(ThreadLocalRandom.current().nextInt(0, 26) + 'A');
            }
            lines.add(ID + ";" + productName + ";" + amount);
        }

       // creator.protectDocument("123"); // Super secure password (luckily only used for test :)
        creator .writeDeliveryInfo( "Great company;A random person;SuperStreet 456A;9200 Aalborg;Denmark",
                        "Leveres til:")
                .writeDNoteInfo("56468;1856;1.1.2556;9483464486;Buying some stuff;That guy over there",
                        "FØLGESEDDEL;Ordernr.;Dato;Kundenr.;Sag;Reference")
                .writeProductInfo(lines,"Some delivery;88888888", "Hermed følger;Titel;Rekv.nr.;" +
                                    "S L U T L E V E R I N G;Varenr.;Beskrivelse;Antal")
                .setAuthor("JavaToPdf")
                .setTitle("A test of this program")
                .setSubject("Testing the output of a delivery note")
                .saveFile();
    }
}

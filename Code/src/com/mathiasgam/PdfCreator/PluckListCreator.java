package com.mathiasgam.PdfCreator;

// The class for easy creation of pluck-lists in .pdf, with the option of password-securing the document
public class PluckListCreator extends SecurePDFCreator {
    public PluckListCreator(String FilePath, String FileName, int xOffset){
        super(FilePath, FileName, xOffset);
    }
}

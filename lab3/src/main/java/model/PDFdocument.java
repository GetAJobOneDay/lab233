package model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PDFdocument {
    private String name;
    private String filePath;
    private PDDocument doc;
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    public PDFdocument(String filePath)throws IOException{
        this.name = Paths.get(filePath).getFileName().toString();
        this.filePath=filePath;
        File pdf = new File(filePath);
        this.doc =PDDocument.load(pdf);
    }

    public PDDocument getDocument() {
        return this.doc;
    }

    public String getName() {
        return this.name;
    }

    public String getFilePath() {
        return this.filePath;
    }
}

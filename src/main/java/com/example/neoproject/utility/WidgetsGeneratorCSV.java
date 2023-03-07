package com.example.neoproject.utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class WidgetsGeneratorCSV {

    public static List<String[]> dataLines;
    private static Integer NUM_WIDGETS= 10000;

    public static void main(String argv[]){

        dataLines = new ArrayList<>();

        for (int j = 1; j <= NUM_WIDGETS; j++) {
            dataLines.add(new String[]
                    //idPostoletto,dimensione,posizione,testo
                    {""+j, ""+new Random().nextInt(1,1000), ""+new Random().nextInt(1,1000), "jflsklkòfs"+j});
        }


        WidgetsGeneratorCSV gen = new WidgetsGeneratorCSV();
        try {
            gen.givenDataArray_whenConvertToCSV_thenOutputCreated();
        }
        catch (IOException io){
            io.printStackTrace();
        }

    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File csvOutputFile = new File("/home/venturino/Scrivania/widgets.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}

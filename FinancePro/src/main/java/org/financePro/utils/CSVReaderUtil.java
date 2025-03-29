package org.financePro.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (isHeader) {  // Skip the first line (header)
                    isHeader = false;
                    continue;
                }
                data.add(line.split(",")); // Assuming CSV is comma-separated
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

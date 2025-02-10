package org.example;

import java.io.*;
import java.util.*;

public class Main {
    private static void processFile(String fileName, List<Integer> integers, List<Double> floats, List<String> strings) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                classifyData(line.trim(), integers, floats, strings);
            }
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлом " + fileName + ": " + e.getMessage());
        }
    }

    private static void classifyData(String line, List<Integer> integers, List<Double> floats, List<String> strings) {
        try {
            if (line.contains(".")) {
                floats.add(Double.parseDouble(line));
            } else {
                integers.add(Integer.parseInt(line));
            }
        } catch (NumberFormatException e) {
            strings.add(line);
        }
    }

    private static void writeToFile(String outputPath, String fileName, List<?> data, boolean append) {
        if (data.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath + fileName, append))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName + ": " + e.getMessage());
        }
    }
}

package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No files for processing"); //для обработки
            return;
        }

        boolean append = false;
        String outputPath = "./";
        String prefix = "";
        boolean shortStats = false;
        boolean fullStats = false;
        List<String> inputFiles = new ArrayList<>();

        // Обработка аргументов командной строки
        for (String arg : args) {
            switch (arg) {
                case "-a":
                    append = true;
                    break;
                case "-o":
                    outputPath = nextArg(args, arg);
                    break;
                case "-p":
                    prefix = nextArg(args, arg);
                    break;
                case "-s":
                    shortStats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                default:
                    inputFiles.add(arg);
                    break;
            }
        }

        // Хранение данных
        List<Integer> integers = new ArrayList<>();
        List<Double> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        // Обработка файлов
        for (String fileName : inputFiles) {
            processFile(fileName, integers, floats, strings);
        }

        // Запись в выходные файлы
        writeToFile(outputPath, prefix + "integers.txt", integers, append);
        writeToFile(outputPath, prefix + "floats.txt", floats, append);
        writeToFile(outputPath, prefix + "strings.txt", strings, append);

        // Вывод статистики
        if (shortStats) {
            printShortStatistics(integers, "Integers");
            printShortStatistics(floats, "Floats");
            printShortStatistics(strings, "Strings");
        }

        if (fullStats) {
            printFullStatistics(integers);
            printFullStatistics(floats);
            printFullStatistics(strings);
        }
    }

    public static void processFile(String fileName, List<Integer> integers, List<Double> floats, List<String> strings) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                classifyData(line.trim(), integers, floats, strings);
            }
        } catch (IOException e) {
            System.err.println("Error work with file " + fileName + ": " + e.getMessage());
        }
    }

    public static void classifyData(String line, List<Integer> integers, List<Double> floats, List<String> strings) {
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

    public static void writeToFile(String outputPath, String fileName, List<?> data, boolean append) {
        if (data.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath + fileName, append))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error write in file " + fileName + ": " + e.getMessage());
        }
    }

    public static void printShortStatistics(List<?> data, String dataType) {
        System.out.println(dataType + " count: " + data.size());
    }

    public static void printFullStatistics(List<?> data) {
        if (data instanceof List<?>) {
            if (!data.isEmpty()) {
                double sum = 0;
                double max = Double.MIN_VALUE;
                double min = Double.MAX_VALUE;
                if (data.get(0) instanceof Integer) {
                    for (Object item : data) {
                        int num = (Integer) item;
                        sum += num;
                        if (num > max) max = num;
                        if (num < min) min = num;
                    }
                    double average = sum / data.size();
                    System.out.println("Integers - Min: " + min + ", Max: " + max + ", Sum: " + sum + ", Average: " + average);
                } else if (data.get(0) instanceof Double) {
                    for (Object item : data) {
                        double num = (Double) item;
                        sum += num;
                        if (num > max) max = num;
                        if (num < min) min = num;
                    }
                    double average = sum / data.size();
                    System.out.println("Floats - Min: " + min + ", Max: " + max + ", Sum: " + sum + ", Average: " + average);
                } else if (data.get(0) instanceof String) {
                    int minLength = Integer.MAX_VALUE;
                    int maxLength = Integer.MIN_VALUE;
                    for (Object item : data) {
                        String str = (String) item;
                        int length = str.length();
                        if (length < minLength) minLength = length;
                        if (length > maxLength) maxLength = length;
                    }
                    System.out.println("Strings - Min Length: " + minLength + ", Max Length: " + maxLength);
                }
            }
        }
    }

    public static String nextArg(String[] args, String currentArg) {
        int index = Arrays.asList(args).indexOf(currentArg);
        if (index + 1 < args.length) {
            return args[index + 1];
        }
        return "";
    }
}

package org.example;

import org.example.threads.CalculationThread;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 3;  // Количество потоков
        int calculationLength = 10;  // Длина расчета

        for (int i = 0; i < numberOfThreads; i++) {
            new CalculationThread(i + 1, calculationLength).start();
        }
    }
}
package org.example.threads;

public class CalculationThread extends Thread {
    private final int threadNumber;
    private final int calculationLength;

    public CalculationThread(int threadNumber, int calculationLength) {
        this.threadNumber = threadNumber;
        this.calculationLength = calculationLength;
    }

    @Override
    public void run() {
        StringBuilder progressBar = new StringBuilder("Thread " + threadNumber + ": [");

        for (int i = 0; i < calculationLength; i++) {
            progressBar.append("#");
            System.out.print("\r" + progressBar.toString() + " " + (i + 1) + "/" + calculationLength + "]");

            try {
                Thread.sleep(500);  // Задержка для имитации расчета
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\r" + progressBar.toString() + " " + calculationLength + "/" + calculationLength + "] Done!");
    }
}

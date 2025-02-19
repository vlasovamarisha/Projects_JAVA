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
        long startTime = System.currentTimeMillis(); //время начала работы потока

        StringBuilder progressBar = new StringBuilder("Thread " + threadNumber + " (ID: " + getId() + "): [");

        for (int i = 0; i < calculationLength; i++) {
            progressBar.append("#");
            System.out.print("\r" + progressBar.toString() + " " + (i + 1) + "/" + calculationLength + "]");

            try {
                Thread.sleep(500);  //задержка для имитации расчета
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long totalTime = System.currentTimeMillis() - startTime; //вычисляем общее время выполнения и выводим результат
        System.out.println("\r" + progressBar.toString() + " " + calculationLength + "/" + calculationLength + "] Done!");
        System.out.println("Thread " + threadNumber + " (ID: " + getId() + ") completed in " + totalTime + " ms.");
    }
}
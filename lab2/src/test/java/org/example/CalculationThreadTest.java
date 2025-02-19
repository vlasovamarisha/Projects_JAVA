package org.example;

import org.example.threads.CalculationThread;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;

public class CalculationThreadTest {

    @Test
    void testThreadCompletion() throws InterruptedException {
        int calculationLength = 10; //длина расчета
        CalculationThread thread = new CalculationThread(1, calculationLength);

        assertTimeout(Duration.ofSeconds(6), () -> {  //запускаем поток и ожидаем его завершения
            thread.start();
            thread.join(); //ждем пока поток завершится
        });

        assertFalse(thread.isAlive(), "Thread should be finished.");    //проверяем, что поток завершился
    }
}


package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final List<Integer> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    @BeforeEach
    void setup() {
        integers.clear();
        floats.clear();
        strings.clear();
    }

    @Test
    void testClassifyData_Integer() {
        Main.classifyData("42", integers, floats, strings);
        assertEquals(1, integers.size());
        assertEquals(42, integers.getFirst());
        assertTrue(floats.isEmpty());
        assertTrue(strings.isEmpty());
    }

    @Test
    void testClassifyData_Float() {
        Main.classifyData("3.14", integers, floats, strings);
        assertEquals(1, floats.size());
        assertEquals(3.14, floats.getFirst());
        assertTrue(integers.isEmpty());
        assertTrue(strings.isEmpty());
    }

    @Test
    void testClassifyData_String() {
        Main.classifyData("Hello", integers, floats, strings);
        assertEquals(1, strings.size());
        assertEquals("Hello", strings.getFirst());
        assertTrue(integers.isEmpty());
        assertTrue(floats.isEmpty());
    }

    @Test
    void testProcessFile() throws IOException {
        String fileName = "test_input.txt";
        List<String> lines = List.of("42", "3.14", "Hello");
        Files.write(Paths.get(fileName), lines);

        Main.processFile(fileName, integers, floats, strings);

        assertEquals(1, integers.size());
        assertEquals(42, integers.getFirst());
        assertEquals(1, floats.size());
        assertEquals(3.14, floats.getFirst());
        assertEquals(1, strings.size());
        assertEquals("Hello", strings.getFirst());

        Files.delete(Paths.get(fileName));
    }

    @Test
    void testWriteToFile() throws IOException {
        String fileName = "test_output.txt";
        List<Integer> testData = List.of(1, 2, 3);

        Main.writeToFile("./", fileName, testData, false);

        List<String> lines = Files.readAllLines(Paths.get(fileName));
        assertEquals(3, lines.size());
        assertEquals("1", lines.get(0));
        assertEquals("2", lines.get(1));
        assertEquals("3", lines.get(2));

        Files.delete(Paths.get(fileName));
    }

    @Test
    void testPrintShortStatistics() {
        integers.add(1);
        integers.add(2);
        integers.add(3);
        assertDoesNotThrow(() -> Main.printShortStatistics(integers, "Integers"));
    }

    @Test
    void testPrintFullStatistics_Integers() {
        integers.add(1);
        integers.add(2);
        integers.add(3);
        assertDoesNotThrow(() -> Main.printFullStatistics(integers));
    }

    @Test
    void testPrintFullStatistics_Floats() {
        floats.add(1.1);
        floats.add(2.2);
        floats.add(3.3);
        assertDoesNotThrow(() -> Main.printFullStatistics(floats));
    }

    @Test
    void testPrintFullStatistics_Strings() {
        strings.add("a");
        strings.add("abc");
        strings.add("abcd");
        assertDoesNotThrow(() -> Main.printFullStatistics(strings));
    }

    @Test
    void testNextArg_Valid() {
        String[] args = {"-o", "outputPath", "-p", "prefix"};
        assertEquals("outputPath", Main.nextArg(args, "-o"));
        assertEquals("prefix", Main.nextArg(args, "-p"));
    }
}
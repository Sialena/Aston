package output;

import entity.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriteToJsonTest {

    private static final String TEST_FILE_PATH = "src/main/java/files/busesDatabase.jsonl";
    private WriteToJson writer;

    @BeforeEach
    void setUp() {
        writer = new WriteToJson();
    }

    @Test
    void shouldWriteBusesToJsonlFile() throws Exception {

        Path testFilePath = Paths.get(TEST_FILE_PATH);
        Files.deleteIfExists(testFilePath);
        Files.createDirectories(testFilePath.getParent());

        List<Bus> buses = Arrays.asList(
                new Bus("100", "Mercedes", 50000),
                new Bus("200", "Iveco", 120000)
        );

        writer.writeToJson(buses);

        assertTrue(Files.exists(testFilePath), "The file must be created");

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(2, lines.size());

        assertTrue(lines.get(0).contains("busNumber"));
        assertTrue(lines.get(0).contains("model"));
        assertTrue(lines.get(0).contains("mileage"));

        assertTrue(lines.get(1).contains("busNumber"));
        assertTrue(lines.get(1).contains("model"));
        assertTrue(lines.get(1).contains("mileage"));
    }

    @Test
    void shouldWriteSingleBusToJsonlFile() throws Exception {
        Path testFilePath = Paths.get(TEST_FILE_PATH);
        Files.deleteIfExists(testFilePath);
        Files.createDirectories(testFilePath.getParent());

        List<Bus> buses = Arrays.asList(new Bus("300", "MAN", 180000));

        writer.writeToJson(buses);

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(1, lines.size());

        String line = lines.get(0);
        assertTrue(line.contains("\"busNumber\":\"300\""));
        assertTrue(line.contains("\"model\":\"MAN\""));
        assertTrue(line.contains("\"mileage\":180000"));
    }

    @Test
    void shouldNotFailOnEmptyList() throws Exception {
        Path testFilePath = Paths.get(TEST_FILE_PATH);
        Files.deleteIfExists(testFilePath);
        Files.createDirectories(testFilePath.getParent());

        List<Bus> emptyList = Arrays.asList();

        writer.writeToJson(emptyList);

        assertTrue(Files.exists(testFilePath), "The file must be created (or already exist)");
        List<String> lines = Files.readAllLines(testFilePath);

    }
}

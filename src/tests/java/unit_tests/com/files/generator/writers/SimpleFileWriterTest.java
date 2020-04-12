package unit_tests.com.files.generator.writers;

import com.files.generator.writers.FileWriter;
import com.files.generator.writers.SimpleFileWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class SimpleFileWriterTest {

    private static final Path FILE_PATH = Path.of("test.txt");
    private static final List<Long> VALUES = Arrays.asList(1L, 2L, 3L);

    @Test
    void testWrite() throws IOException {
        FileWriter<Long> writer = new SimpleFileWriter();
        writer.write(FILE_PATH, VALUES);
        verifyResults(9, 3);
    }

    @Test
    void testWriteAppendingMode() throws IOException {
        FileWriter<Long> writer = new SimpleFileWriter();
        writer.write(FILE_PATH, VALUES);
        verifyResults(9, 3);
        writer.write(FILE_PATH, VALUES);
        verifyResults(18, 6);
        writer.write(FILE_PATH, VALUES);
        verifyResults(27, 9);
    }

    private void verifyResults(long size, long count) throws IOException {
        Assertions.assertTrue(Files.exists(FILE_PATH), "The file should exist");
        Assertions.assertEquals(size, Files.size(FILE_PATH), "Wrong file size");
        Assertions.assertEquals(count, Files.lines(FILE_PATH).count(), "Wrong lines number");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(FILE_PATH);
    }
}
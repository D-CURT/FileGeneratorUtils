package E2E_tests.com.files.generator;

import com.files.generator.FileGeneratorUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FileGeneratorUtilsTest {

    private static final String SAMPLE_FILE_NAME = "sample.txt";
    private static final long SAMPLE_FILE_SIZE = 7_888_896;
    private static final long SAMPLE_FILE_LINES = 1_000_000;
    private Path filePath;

    @Test
    void testLifecycleGenerateSimpleNoneArguments() throws IOException {
        this.filePath = Path.of(SAMPLE_FILE_NAME);
        FileGeneratorUtils.generateSimple();
        verifyGeneratedFile(SAMPLE_FILE_SIZE, SAMPLE_FILE_LINES);
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\d+$")), "The file should contains digits only");
    }

    @Test
    void testLifecycleGenerateSimpleSpecifiedPath() throws IOException {
        this.filePath = Path.of("test.txt");
        FileGeneratorUtils.generateSimple(filePath);
        verifyGeneratedFile(SAMPLE_FILE_SIZE, SAMPLE_FILE_LINES);
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\d+$")), "The file should contains digits only");
    }

    @Test
    void testLifecycleGenerateSimpleSpecifiedMaxLines() throws IOException {
        this.filePath = Path.of(SAMPLE_FILE_NAME);
        FileGeneratorUtils.generateSimple(12_000_000L);
        verifyGeneratedFile(108_888_897, 12_000_000);
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\d+$")), "The file should contains digits only");
    }

    @Test
    void testLifecycleGenerateSimpleAllArguments() throws IOException {
        this.filePath = Path.of("test.txt");
        FileGeneratorUtils.generateSimple(filePath, 12_000_000L);
        verifyGeneratedFile(108_888_897, 12_000_000);
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\d+$")), "The file should contains digits only");
    }

    @Test
    void testLifecycleGenerateLettersNoneArguments() throws IOException {
        this.filePath = Path.of(SAMPLE_FILE_NAME);
        FileGeneratorUtils.generateLetters();
        verifyGeneratedFile(12_000_000, SAMPLE_FILE_LINES);
        Assertions.assertTrue(Files.lines(filePath).map(String::length).allMatch(Integer.valueOf(10)::equals));
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\D+$")), "The file should contains letters only");
    }

    @Test
    void testLifecycleGenerateLettersSpecifiedPath() throws IOException {
        this.filePath = Path.of("test.txt");
        FileGeneratorUtils.generateLetters(filePath);
        verifyGeneratedFile(12_000_000, SAMPLE_FILE_LINES);
        Assertions.assertTrue(Files.lines(filePath).map(String::length).allMatch(Integer.valueOf(10)::equals));
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\D+$")), "The file should contains letters only");
    }

    @Test
    void testLifecycleGenerateLettersSpecifiedPathAndMaxLines() throws IOException {
        this.filePath = Path.of("test.txt");
        FileGeneratorUtils.generateLetters(filePath, 12_000_000L);
        verifyGeneratedFile(144_000_000, 12_000_000);
        Assertions.assertTrue(Files.lines(filePath).map(String::length).allMatch(Integer.valueOf(10)::equals));
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\D+$")), "The file should contains letters only");
    }

    @Test
    void testLifecycleGenerateLettersAllArguments() throws IOException {
        this.filePath = Path.of("test.txt");
        FileGeneratorUtils.generateLetters(filePath, 10_000_000L, 50);
        verifyGeneratedFile(520_000_000, 10_000_000);
        Assertions.assertTrue(Files.lines(filePath).map(String::length).allMatch(Integer.valueOf(50)::equals));
        Assertions.assertTrue(Files.lines(filePath).allMatch(s -> s.matches("^\\D+$")), "The file should contains letters only");
    }

    private void verifyGeneratedFile(long size, long count) throws IOException {
        Assertions.assertTrue(Files.exists(filePath), "The file should exist");
        Assertions.assertTrue(Files.isRegularFile(filePath), "The file should be a regular one");
        Assertions.assertEquals(size, Files.size(filePath), "Wrong file size");
        Assertions.assertEquals(count, Files.lines(filePath).count(), "Wrong lines number");
        Assertions.assertTrue(Files.lines(filePath).allMatch(StringUtils::isNotBlank), "The file shouldn`t contains empty lines");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(filePath);
    }

}
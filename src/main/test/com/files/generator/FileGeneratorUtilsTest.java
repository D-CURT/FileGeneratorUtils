package com.files.generator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FileGeneratorUtilsTest {

    public static final String SAMPLE_FILE_NAME = "sample.txt";

    @Test
    void testLifecycleGenerateSimpleNoneArguments() throws IOException {
        FileGeneratorUtils.generateSimple();
        Path path = Path.of(SAMPLE_FILE_NAME);
        Assertions.assertTrue(Files.exists(path), "The file should exist");
        Assertions.assertTrue(Files.isRegularFile(path), "The file should be a regular one");
        Assertions.assertEquals(7_888_896, Files.size(path), "Wrong file size");
        Assertions.assertEquals(1_000_000, Files.lines(path).count(), "Wrong lines number");
        Assertions.assertTrue(Files.lines(path).allMatch(StringUtils::isNotBlank), "The file shouldn`t contains empty lines");
        Assertions.assertTrue(Files.lines(path).allMatch(s -> s.matches("^\\d+$")), "The file should contains digits only");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(SAMPLE_FILE_NAME));
    }

}
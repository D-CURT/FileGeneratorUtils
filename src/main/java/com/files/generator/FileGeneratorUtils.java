package com.files.generator;

import com.files.generator.generators.FileGeneratorConfig;
import com.files.generator.utils.FileGeneratorFactory;
import com.files.generator.utils.LoggerFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public final class FileGeneratorUtils {

    private FileGeneratorUtils() {
    }

    private static Logger log = LoggerFactory.getLogger(FileGeneratorUtils.class);

    private static final int DEFAULT_LINE_SIZE = 10;
    private final static long DEFAULT_MAX_LINES_NUMBER = 1_000_000;
    private static final Path DEFAULT_FILE_PATH = Path.of("sample.txt");

    public static void generateSimple() throws IOException {
        generateSimple(DEFAULT_FILE_PATH);
    }

    public static void generateSimple(Path filePath) throws IOException {
        generateSimple(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateSimple(Long maxLines) throws IOException {
        generateSimple(DEFAULT_FILE_PATH, maxLines);
    }

    public static void generateSimple(Path filePath, Long maxLines) throws IOException {
        Path path = Validator.getNonNullValue(filePath, DEFAULT_FILE_PATH, Validator.PATH_ERROR_MESSAGE);
        Long linesNumber = Validator.getNonNullValue(maxLines, DEFAULT_MAX_LINES_NUMBER,
                Validator.LINES_NUMBER_ERROR_MESSAGE);
        FileGeneratorFactory.getGenerator(FileType.DEFAULT)
                .generate(
                        FileGeneratorConfig.builder()
                                .path(path)
                                .linesNumber(linesNumber)
                                .build());
    }

    public static void generateLetters() throws IOException {
        generateLetters(DEFAULT_FILE_PATH);
    }

    public static void generateLetters(Path filePath) throws IOException {
        generateLetters(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateLetters(Long maxLines) throws IOException {
        generateLetters(DEFAULT_FILE_PATH, maxLines);
    }

    public static void generateLetters(Path filePath, Long maxLines) throws IOException {
        generateLetters(filePath, maxLines, DEFAULT_LINE_SIZE);
    }

    public static void generateLetters(Long maxLines, Integer lineSize) throws IOException {
        generateLetters(DEFAULT_FILE_PATH, maxLines, lineSize);
    }

    public static void generateLetters(Path filePath, Long maxLines, Integer lineSize) throws IOException {
        Path path = Validator.getNonNullValue(filePath, DEFAULT_FILE_PATH, Validator.PATH_ERROR_MESSAGE);
        Long linesNumber = Validator.getValidNumber(maxLines, DEFAULT_MAX_LINES_NUMBER,
                Validator.LINES_NUMBER_ERROR_MESSAGE);
        Integer maxLineSize = Validator.getValidNumber(lineSize, DEFAULT_LINE_SIZE,
                "Line size was not specified, default will be set...");
        FileGeneratorFactory.getGenerator(FileType.TEXT)
                .generate(
                        FileGeneratorConfig.builder()
                                .path(path)
                                .linesNumber(linesNumber)
                                .lineSize(maxLineSize)
                                .build());
    }

}

package com.files.generator;

import com.files.generator.generators.FileGeneratorConfig;
import com.files.generator.utils.FileGeneratorFactory;
import com.files.generator.utils.LoggerFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public final class FileGeneratorUtils {

    private FileGeneratorUtils() {
    }

    private static Logger log = LoggerFactory.getLogger(FileGeneratorUtils.class);

    private static final int DEFAULT_LINE_SIZE = 10;
    private final static long DEFAULT_MAX_LINES_NUMBER = 1_000_000;
    private static final Path DEFAULT_FILE_PATH = Path.of("sample.txt");
    private static final String PATH_ERROR_MESSAGE = "File path was not specified, default will be set...";
    private static final String LINES_NUMBER_ERROR_MESSAGE = "Lines number was not specified, default will be set...";

    public static void generateSimple() throws IOException {
        generateSimple(DEFAULT_FILE_PATH);
    }

    public static void generateSimple(Path filePath) throws IOException {
        generateSimple(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateSimple(Path filePath, Long maxLines) throws IOException {
        Path path = getNonNullValue(filePath, DEFAULT_FILE_PATH, PATH_ERROR_MESSAGE);
        Long linesNumber = getNonNullValue(maxLines, DEFAULT_MAX_LINES_NUMBER, LINES_NUMBER_ERROR_MESSAGE);
        FileGeneratorFactory.getGenerator(FileType.DEFAULT)
                .generate(
                        FileGeneratorConfig.builder()
                                .path(path)
                                .linesNumber(linesNumber)
                                .build());
    }

    public static void generateText() throws IOException {
        generateText(DEFAULT_FILE_PATH);
    }

    public static void generateText(Path filePath) throws IOException {
        generateText(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateText(Path filePath, Long maxLines) throws IOException {
        generateText(filePath, maxLines, DEFAULT_LINE_SIZE);
    }

    public static void generateText(Path filePath, Long maxLines, Integer lineSize) throws IOException {
        Path path = getNonNullValue(filePath, DEFAULT_FILE_PATH, PATH_ERROR_MESSAGE);
        Long linesNumber = getNonNullValue(maxLines, DEFAULT_MAX_LINES_NUMBER, LINES_NUMBER_ERROR_MESSAGE);
        Integer maxLineSize = getNonNullValue(lineSize, DEFAULT_LINE_SIZE,
                "Line size was not specified, default will be set...");
        FileGeneratorFactory.getGenerator(FileType.TEXT)
                .generate(
                        FileGeneratorConfig.builder()
                                .path(path)
                                .linesNumber(linesNumber)
                                .lineSize(maxLineSize)
                                .build());
    }

    private static <T> T getNonNullValue(T value, T defaultValue, String errorMessage) {
        return Optional.ofNullable(value).orElseGet(() -> {
            log.info(errorMessage);
            return defaultValue;
        });
    }

}

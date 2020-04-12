package com.files.generator;

import com.files.generator.generators.FileGeneratorConfig;
import com.files.generator.utils.LoggerFactory;
import com.files.generator.utils.FileGeneratorFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public final class FileGeneratorUtils {

    private FileGeneratorUtils() {
    }

    private static Logger log = LoggerFactory.getLogger(FileGeneratorUtils.class);

    private final static long DEFAULT_MAX_LINES_NUMBER = 1_000_000;
    private static final Path DEFAULT_FILE_PATH = Path.of("sample.txt");

    public static void generateSimple() throws IOException {
        generateSimple(DEFAULT_FILE_PATH);
    }

    public static void generateSimple(Path filePath) throws IOException {
        generateSimple(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateSimple(Path filePath, Long maxLines) throws IOException {
        Path path = getNonNullValue(filePath, DEFAULT_FILE_PATH,
                "File path was not specified, default will be set...");
        Long linesNumber = getNonNullValue(maxLines, DEFAULT_MAX_LINES_NUMBER,
                "Lines number was not specified, default will be set...");
        FileGeneratorFactory.getGenerator(FileType.DEFAULT)
                .generate(
                        FileGeneratorConfig.builder()
                                .path(path)
                                .linesNumber(linesNumber)
                                .build());
    }

    private static <T> T getNonNullValue(T value, T defaultValue, String errorMessage) {
        return Optional.ofNullable(value).orElseGet(() -> {
            log.info(errorMessage);
            return defaultValue;
        });
    }

}

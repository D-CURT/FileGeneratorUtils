package com.gmail.estrynvladislav;

import com.gmail.estrynvladislav.utils.LoggerFactory;
import com.google.common.base.Stopwatch;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class FileGeneratorUtils {

    private FileGeneratorUtils() {
    }

    private static Logger log = LoggerFactory.getLogger(FileGeneratorUtils.class);

    private final static long DEFAULT_MAX_LINES_NUMBER = 100_000;
    private static final Path DEFAULT_FILE_PATH = Path.of("sample.txt");
    private final static long MAX_RANGE = 100_000;
    private static final int MIN_RANGE = 0;
    private static final boolean IS_APPEND_TO_FILE = true;
    private static AtomicLong counter = new AtomicLong();

    public static void generateSimple() throws IOException {
        generateSimple(DEFAULT_FILE_PATH);
    }

    public static void generateSimple(Path filePath) throws IOException {
       generateSimple(filePath, DEFAULT_MAX_LINES_NUMBER);
    }

    public static void generateSimple(Path filePath, Long maxLines) throws IOException {
        Path path = getVerifiedValue(filePath, DEFAULT_FILE_PATH,
                "File path was not specified, default will be set...");
        Long linesNumber = getVerifiedValue(maxLines, DEFAULT_MAX_LINES_NUMBER,
                "Lines number was not specified, default will be set...");
        while (counter.get() < linesNumber) {
            Stopwatch timer = Stopwatch.createStarted();

            List<Long> list = LongStream.range(MIN_RANGE, MAX_RANGE)
                    .mapToObj(FileGeneratorUtils::generateNext)
                    .collect(Collectors.toList());

            FileUtils.writeLines(path.toFile(), list, IS_APPEND_TO_FILE);

            log.info("Time: " + timer.stop() + ". Lines: " + counter.get());
        }
    }

    private static <T> T getVerifiedValue(T value, T defaultValue, String errorMessage) {
        return Optional.ofNullable(value).orElseGet(() -> {
            log.info(errorMessage);
            return defaultValue;
        });
    }

    private static long generateNext(Long ignored) {
        return counter.incrementAndGet();
    }

}

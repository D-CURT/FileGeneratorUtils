package com.files.generator.writers;

import com.files.generator.utils.LoggerFactory;
import com.google.common.base.Stopwatch;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SimpleTextFileWriter implements FileWriter {

    private final static long MAX_RANGE = 1_000;
    private static final int MIN_RANGE = 0;
    private static final boolean IS_APPEND_TO_FILE = true;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AtomicLong counter;

    public SimpleTextFileWriter() {
        this.counter = new AtomicLong();
    }

    @Override
    public boolean write(Path path, Long linesLimit) throws IOException {
        while (counter.get() < linesLimit) {
            Stopwatch timer = Stopwatch.createStarted();
            List<Long> list = LongStream.range(MIN_RANGE, MAX_RANGE)
                    .mapToObj(this::generateNext)
                    .collect(Collectors.toList());
            FileUtils.writeLines(path.toFile(), list, IS_APPEND_TO_FILE);
            log.info("Time: " + timer.stop() + ". Lines: " + counter.get());
        }
        return false;
    }

    private long generateNext(Long ignored) {
        return counter.incrementAndGet();
    }

}

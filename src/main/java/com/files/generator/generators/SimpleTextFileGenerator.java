package com.files.generator.generators;

import com.files.generator.utils.LoggerFactory;
import com.files.generator.writers.SimpleFileWriter;
import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SimpleTextFileGenerator implements FileGenerator {

    private final static int DEFAULT_MAX_RANGE = 1_000_000;
    private static final int MIN_RANGE = 0;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AtomicLong counter;

    public SimpleTextFileGenerator() {
        this.counter = new AtomicLong();
    }

    @Override
    public boolean generate(Path path, Long linesLimit) throws IOException {
        final long maxRange = linesLimit < DEFAULT_MAX_RANGE ? linesLimit : DEFAULT_MAX_RANGE;
        while (counter.get() < linesLimit) {
            Stopwatch timer = Stopwatch.createStarted();
            final long rowsRemains = linesLimit - counter.get();
            List<Long> list = LongStream.range(MIN_RANGE, Math.min(rowsRemains, maxRange))
                    .mapToObj(this::generateNext)
                    .collect(Collectors.toList());
            SimpleFileWriter.of(path, list).write();
            log.info("Time: " + timer.stop() + ". Lines: " + counter.get());
        }
        return true;
    }

    private long generateNext(Long ignored) {
        return counter.incrementAndGet();
    }

}

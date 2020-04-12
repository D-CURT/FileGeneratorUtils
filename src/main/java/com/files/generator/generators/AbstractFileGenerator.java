package com.files.generator.generators;

import com.files.generator.utils.LoggerFactory;
import com.files.generator.writers.FileWriter;
import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public abstract class AbstractFileGenerator<Value> implements FileGenerator {

    public static final int DEFAULT_MAX_RANGE = 1_000_000;
    public static final int MIN_RANGE = 0;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Getter(AccessLevel.PROTECTED)
    private final FileWriter<Value> writer;

    @Getter(AccessLevel.PROTECTED)
    private final AtomicLong counter;

    public AbstractFileGenerator(FileWriter<Value> writer) {
        this.counter = new AtomicLong();
        this.writer = writer;
    }

    @Override
    public boolean generate(FileGeneratorConfig config) throws IOException {
        Long linesLimit = config.getLinesNumber();
        final long maxRange = linesLimit < DEFAULT_MAX_RANGE ? linesLimit : DEFAULT_MAX_RANGE;
        while (counter.get() < linesLimit) {
            Stopwatch timer = Stopwatch.createStarted();
            final long rowsRemains = linesLimit - counter.get();
            List<Value> values = LongStream.range(MIN_RANGE, Math.min(rowsRemains, maxRange))
                    .mapToObj(this::generateNext)
                    .collect(Collectors.toList());
            writer.write(config.getPath(), values);
            log.info("Time: " + timer.stop() + ". Lines: " + counter.get());
        }
        return true;
    }

    protected abstract Value generateNext(Long ignored);

}

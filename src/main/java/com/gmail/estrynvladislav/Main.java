package com.gmail.estrynvladislav;

import com.google.common.base.Stopwatch;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

    private final static long MAX_LINES_NUMBER = 3_000_000_000L;
    private final static long MAX_RANGE = 100_000_000;
    public static final int MIN_RANGE = 0;
    public static final boolean IS_APPEND_TO_FILE = true;
    private static AtomicLong counter = new AtomicLong();

    public static void main(String[] args) throws IOException {

        File file = new File("file.txt");

        while (counter.get() < MAX_LINES_NUMBER) {

            Stopwatch timer = Stopwatch.createStarted();

            List<Long> list = LongStream.range(MIN_RANGE, MAX_RANGE)
                    .mapToObj(Main::generateNext)
                    .collect(Collectors.toList());

            FileUtils.writeLines(file, list, IS_APPEND_TO_FILE);

            System.out.println("Time: " + timer.stop() + ". Counter: " + counter);
        }
    }

    private static long generateNext(Long ignored) {
        return counter.incrementAndGet();
    }
}
package com.files.generator.generators;

import com.files.generator.writers.FileWriter;

public class SimpleTextFileGenerator extends AbstractFileGenerator<Long> {

    public SimpleTextFileGenerator(FileWriter<Long> writer) {
        super(writer);
    }

    @Override
    protected Long generateNext(Long ignored) {
        return getCounter().incrementAndGet();
    }

}

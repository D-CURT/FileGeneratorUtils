package com.files.generator.generators;

import com.files.generator.writers.FileWriter;
import org.apache.commons.lang3.RandomStringUtils;

public class TextFileGenerator extends AbstractFileGenerator<String> {

    public TextFileGenerator(FileWriter<String> writer) {
        super(writer);
    }

    @Override
    protected String generateNext(Long ignored) {
        String random = RandomStringUtils.random(getConfig().getLineSize(), true, false);
        getCounter().incrementAndGet();
        return random;
    }

}

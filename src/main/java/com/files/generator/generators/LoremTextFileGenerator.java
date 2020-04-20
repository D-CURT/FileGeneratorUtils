package com.files.generator.generators;

import com.files.generator.writers.FileWriter;
import com.thedeanda.lorem.LoremIpsum;

import java.util.concurrent.atomic.AtomicLong;

public class LoremTextFileGenerator extends AbstractFileGenerator<String> {

    private AtomicLong wordsRemains;

    public LoremTextFileGenerator(FileWriter<String> writer) {
        super(writer);
        this.wordsRemains = new AtomicLong();
    }

    @Override
    protected String generateNext(Long ignored) {
        FileGeneratorConfig config = getConfig();
        getCounter().incrementAndGet();
        return LoremIpsum.getInstance().getWords(computeWordsInRow());
    }

    @Override
    protected void initSpecific(FileGeneratorConfig config) {
        super.linesLimit.addAndGet(Double.valueOf(Math.ceil(config.getWordsNumber() / config.getWordsInRow())).longValue());
        this.wordsRemains.addAndGet(config.getWordsNumber());
    }

    private int computeWordsInRow() {
        if (this.wordsRemains.updateAndGet(current -> current - getConfig().getWordsInRow()) < 0) {
            return Long.valueOf(Math.abs(this.wordsRemains.get())).intValue();
        }
        return getConfig().getWordsInRow();
    }

}

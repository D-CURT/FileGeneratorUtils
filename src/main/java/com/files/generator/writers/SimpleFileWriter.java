package com.files.generator.writers;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SimpleFileWriter implements FileWriter<Long> {

    private static final boolean IS_APPEND_TO_FILE = true;

    @Override
    public void write(Path path, List<Long> values) throws IOException {
        FileUtils.writeLines(path.toFile(), values, IS_APPEND_TO_FILE);
    }

}

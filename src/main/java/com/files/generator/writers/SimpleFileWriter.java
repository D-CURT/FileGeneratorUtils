package com.files.generator.writers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class SimpleFileWriter<T> implements FileWriter {

    private static final boolean IS_APPEND_TO_FILE = true;
    private final Path path;
    private final List<T> source;

    @Override
    public void write() throws IOException {
        FileUtils.writeLines(path.toFile(), source, IS_APPEND_TO_FILE);
    }

}

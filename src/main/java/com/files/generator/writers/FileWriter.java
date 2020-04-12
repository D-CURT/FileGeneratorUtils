package com.files.generator.writers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileWriter<T> {

    void write(Path path, List<T> values) throws IOException;

}

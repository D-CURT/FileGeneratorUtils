package com.files.generator.writers;

import java.io.IOException;
import java.nio.file.Path;

public interface FileWriter {

    boolean write(Path path, Long linesLimit) throws IOException;

}

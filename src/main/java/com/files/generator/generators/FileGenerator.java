package com.files.generator.generators;

import java.io.IOException;
import java.nio.file.Path;

public interface FileGenerator {

    boolean generate(Path path, Long linesLimit) throws IOException;

}

package com.files.generator.generators;

import java.io.IOException;

public interface FileGenerator {

    boolean generate(FileGeneratorConfig config) throws IOException;

}

package com.files.generator.generators;

import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;

@Getter
@Builder
public class FileGeneratorConfig {

    private Path path;

    private Long linesNumber;

    private Integer lineSize;

    private Long wordsNumber;

    private Integer wordsInRow;

}

package com.files.generator.utils;

import com.files.generator.FileType;
import com.files.generator.generators.FileGenerator;
import com.files.generator.generators.SimpleTextFileGenerator;

import java.util.Arrays;

public final class FileGeneratorFactory {

    private FileGeneratorFactory() {
    }

    private enum Types {
        TEXT {
            @Override
            FileGenerator createGenerator() {
                return null;
            }
        }, CSV {
            @Override
            FileGenerator createGenerator() {
                return null;
            }
        }, DEFAULT {
            @Override
            FileGenerator createGenerator() {
                return new SimpleTextFileGenerator();
            }
        };

        abstract FileGenerator createGenerator();

        boolean isEqual(FileType type) {
            return this.name().equals(type.name());
        }

        static Types findType(FileType type) {
            return Arrays.stream(values())
                    .filter(writerType -> writerType.isEqual(type))
                    .findAny()
                    .orElse(DEFAULT);
        }
    }

    public static FileGenerator getGenerator(FileType type) {
        return Types.findType(type).createGenerator();
    }

}

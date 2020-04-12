package com.files.generator.utils;

import com.files.generator.FileType;
import com.files.generator.generators.FileGenerator;
import com.files.generator.generators.SimpleTextFileGenerator;
import com.files.generator.generators.TextFileGenerator;
import com.files.generator.writers.SimpleFileWriter;
import com.files.generator.writers.TextFileWriter;
import com.google.common.collect.Maps;

import java.util.EnumSet;
import java.util.Map;

public final class FileGeneratorFactory {

    private FileGeneratorFactory() {
    }

    private enum Types {
        TEXT {
            @Override
            FileGenerator createGenerator() {
                return new TextFileGenerator(new TextFileWriter());
            }
        }, CSV {
            @Override
            FileGenerator createGenerator() {
                return null;
            }
        }, DEFAULT {
            @Override
            FileGenerator createGenerator() {
                return new SimpleTextFileGenerator(new SimpleFileWriter());
            }
        };

        private static final Map<FileType, Types> types =
                Maps.asMap(EnumSet.allOf(FileType.class), type -> Types.valueOf(type.name()));

        abstract FileGenerator createGenerator();

        static Types findType(FileType type) {
            return types.get(type);
        }
    }

    public static FileGenerator getGenerator(FileType type) {
        return Types.findType(type).createGenerator();
    }

}

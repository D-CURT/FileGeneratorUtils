package com.files.generator.utils;

import com.files.generator.FileType;
import com.files.generator.writers.FileWriter;
import com.files.generator.writers.SimpleTextFileWriter;

import java.util.Arrays;

public final class FileWriterFactory {

    private FileWriterFactory(){
    }

    private enum Types {
        TEXT {
            @Override
            FileWriter createWriter() {
                return null;
            }
        }, CSV {
            @Override
            FileWriter createWriter() {
                return null;
            }
        }, DEFAULT {
            @Override
            FileWriter createWriter() {
                return new SimpleTextFileWriter();
            }
        };

        abstract FileWriter createWriter();

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

    public static FileWriter getWriter(FileType type) {
        return Types.findType(type).createWriter();
    }

}

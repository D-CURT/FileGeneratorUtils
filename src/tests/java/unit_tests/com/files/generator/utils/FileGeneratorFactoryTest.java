package unit_tests.com.files.generator.utils;

import com.files.generator.FileType;
import com.files.generator.generators.AbstractFileGenerator;
import com.files.generator.generators.FileGenerator;
import com.files.generator.generators.SimpleTextFileGenerator;
import com.files.generator.generators.TextFileGenerator;
import com.files.generator.utils.FileGeneratorFactory;
import com.files.generator.writers.SimpleFileWriter;
import com.files.generator.writers.TextFileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.junit.platform.commons.util.ReflectionUtils;

class FileGeneratorFactoryTest {

    @Test
    void testGetGeneratorDefault() throws Exception {
        FileGenerator generator = FileGeneratorFactory.getGenerator(FileType.DEFAULT);
        verifyGenerator(generator, SimpleTextFileGenerator.class, SimpleFileWriter.class);
    }

    @Test
    void testGetGeneratorText() throws Exception {
        FileGenerator generator = FileGeneratorFactory.getGenerator(FileType.TEXT);
        verifyGenerator(generator, TextFileGenerator.class, TextFileWriter.class);
    }

    private <T extends AbstractFileGenerator> void verifyGenerator(FileGenerator generator, Class<T> generatorType,
                                                                   Class<?> writerType) throws Exception {
        Assertions.assertEquals(generatorType, generator.getClass(), "Wrong generator type");
        Try<Object> writerTry = ReflectionUtils.tryToReadFieldValue(
                AbstractFileGenerator.class,
                "writer",
                (T) generator
        );
        Assertions.assertEquals(writerType, writerTry.get().getClass(), "Wrong file writer type");
    }

}
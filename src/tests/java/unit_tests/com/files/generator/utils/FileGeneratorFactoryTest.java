package unit_tests.com.files.generator.utils;

import com.files.generator.FileType;
import com.files.generator.generators.AbstractFileGenerator;
import com.files.generator.generators.FileGenerator;
import com.files.generator.generators.SimpleTextFileGenerator;
import com.files.generator.utils.FileGeneratorFactory;
import com.files.generator.writers.SimpleFileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class FileGeneratorFactoryTest {

    @Test
    void testGetGenerator() throws Exception {
        FileGenerator generator = FileGeneratorFactory.getGenerator(FileType.DEFAULT);
        Assertions.assertEquals(SimpleTextFileGenerator.class, generator.getClass());
        Assertions.assertEquals(
                SimpleFileWriter.class,
                ReflectionUtils.tryToReadFieldValue(
                        AbstractFileGenerator.class,
                        "writer",
                        (SimpleTextFileGenerator) generator
                ).get().getClass());
    }

}
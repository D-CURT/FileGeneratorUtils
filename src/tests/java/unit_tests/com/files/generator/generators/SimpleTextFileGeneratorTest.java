package unit_tests.com.files.generator.generators;

import com.files.generator.generators.AbstractFileGenerator;
import com.files.generator.generators.FileGeneratorConfig;
import com.files.generator.generators.SimpleTextFileGenerator;
import com.files.generator.writers.FileWriter;
import com.files.generator.writers.SimpleFileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class SimpleTextFileGeneratorTest {

    private FileWriter<Long> writer = mock(SimpleFileWriter.class);

    @InjectMocks
    private SimpleTextFileGenerator generator;

    @BeforeEach
    void setUp() throws IOException {
        this.generator = new SimpleTextFileGenerator(this.writer);
        doNothing().when(writer).write(any(), any());
    }

    @Test
    void testGenerate() throws Exception {
        generator.generate(FileGeneratorConfig.builder().path(Path.of("test.txt")).linesNumber(1_000L).build());
        Assertions.assertEquals(
                1_000,
                ((AtomicLong) ReflectionUtils.tryToReadFieldValue(
                        AbstractFileGenerator.class,
                        "counter",
                        generator)
                        .get()
                ).get()
        );
    }

    @Test
    void testGenerateNext() {
        Method generateNext = ReflectionUtils.findMethod(generator.getClass(), "generateNext", Long.class)
                .orElseThrow();
        ReflectionUtils.makeAccessible(generateNext);
        verifyGenerateNext(generateNext);
    }

    private void verifyGenerateNext(Method generateNext) {
        for (int i = 1; i <= 5; i++) {
            Long result = (Long) ReflectionUtils.invokeMethod(generateNext, generator, 0L);
            Assertions.assertNotNull(result, "The result shouldn`t be empty");
            Assertions.assertEquals(i, result, "The result should be equal the current counter");
        }
    }
}
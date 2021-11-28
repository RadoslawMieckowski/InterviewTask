import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    @Order(3)
    @DisplayName("Ensure that the source file exists")
    void shouldFindFile(@TempDir Path path) throws IOException {
        path = Path.of("src/main/resources/data.txt");
        File expectedFile = Main.generateAndWrite(new File(String.valueOf(path)));
        assertTrue(Files.exists(expectedFile.toPath()), "File should exist");
    }

    @Test
    @Order(1)
    void shouldThrowNullPointerExceptionOnInvalidArgument(@TempDir Path path) {
        path = Path.of("/wrong-path");
        Path finalPath = path;
        assertThrows(FileNotFoundException.class, () -> {
            Main.generateAndWrite(new File(String.valueOf(finalPath)));
        });
    }
    @Test
    @Order(2)
    void shouldThrowFileNotFoundExceptionOnEmptyArgument(@TempDir Path path) {
        path = Path.of("");
        Path finalPath = path;
        assertThrows(FileNotFoundException.class, () -> {
            Main.generateAndWrite(new File(String.valueOf(finalPath)));
        });
    }
    @Test
    @Order(6)
    void shouldCheckExactSizeOfMap() throws IOException {
        int size=10;
        File file=Main.generateAndWrite(new File("src/main/resources/data.txt"));
        TreeMap<Long,Integer> data= Main.readAndMakeMap(file);
        assertThat(data).hasSize(size);
    }

    @Test
    @Order(5)
    void shouldThrowNullPointerExceptionOnInvalidArgument2() {
        Path path = Path.of("wrong-path");
        Throwable throwable=catchThrowable(()->Main.readAndMakeMap(new File(String.valueOf(path))));
        assertThrows(NullPointerException.class, (Executable) throwable);
    }
    @Test
    @Order(4)
    void shouldThrowFileNotFoundExceptionOnEmptyArgument2(){
        Path path = Path.of("");
        assertThrows(FileNotFoundException.class, () -> {
            Main.readAndMakeMap(new File(String.valueOf(path)));
        });
    }

    @Test
    @Order(8)
    void shouldCheckValuesOfMap() throws IOException {
        File file=Main.generateAndWrite(new File("src/main/resources/data.txt"));
        TreeMap<Long,Integer> data= Main.readAndMakeMap(file);
        TreeMap<Long,Integer>processedData=Main.makeTreeMapOfChanges(data);
        assertFalse(processedData.containsValue(2));
    }

    @Test
    @Order(7)
    void shouldCheckValuesOfMap2() throws IOException {
        File file=Main.generateAndWrite(new File("src/main/resources/data.txt"));
        TreeMap<Long,Integer> data= Main.readAndMakeMap(file);
        TreeMap<Long,Integer>processedData=Main.makeTreeMapOfChanges(data);
        assertThat(data).containsAllEntriesOf(processedData);
    }

}
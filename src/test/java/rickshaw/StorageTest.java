package rickshaw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import rickshaw.task.Task;

public class StorageTest {

    @Test
    public void loadEmptyFile_returnsEmptyList_success() throws IOException, RickshawException {
        Storage storage = new Storage("src/test/resources/data/test_empty.txt");
        ArrayList<Task> tasks = storage.load();
        assertEquals(0, tasks.size());
    }

    @Test
    public void loadValidFile_returnsTasks_success() throws IOException, RickshawException {
        Storage storage = new Storage("src/test/resources/data/test_valid.txt");
        ArrayList<Task> tasks = storage.load();
        assertEquals(2, tasks.size());
        assertEquals("read book", tasks.get(0).getDescription());
        assertEquals(false, tasks.get(0).isDone());
        assertEquals("go to the library", tasks.get(1).getDescription());
        assertEquals(false, tasks.get(1).isDone());
    }
}

package rickshaw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rickshaw.commands.TodoCommand;

public class TaskListTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui("TestBot");
        storage = new Storage("data/test.txt");
    }

    @Test
    public void addTodo_validTodoInput_success() {
        TodoCommand todo = new TodoCommand("go to the library");
        todo.run(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("go to the library", taskList.getTask(0).getDescription());
        assertEquals(false, taskList.getTask(0).isDone());
    }
}

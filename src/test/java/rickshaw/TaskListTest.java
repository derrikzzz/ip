package rickshaw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        storage = new Storage("src/test/resources/data/test.txt");
    }

    @Test
    public void addTodo_validTodoInput_success() throws RickshawException {
        TodoCommand todo = new TodoCommand("go to the library");
        todo.run(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("go to the library", taskList.getTask(0).getDescription());
        assertEquals(false, taskList.getTask(0).isDone());
    }

    @Test
     public void addTodo_duplicateTodoInput_throwsException() throws RickshawException {
      TodoCommand todo = new TodoCommand("go gym");
      todo.run(taskList, ui, storage);
      RickshawException e = assertThrows(RickshawException.class, () -> {
          todo.run(taskList, ui, storage);
      });
      assertEquals("This task already exists in the list: go gym", e.getMessage());
  }
}

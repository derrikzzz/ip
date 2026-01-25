package rickshaw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.beans.Transient;

import rickshaw.TaskList;
import rickshaw.RickshawException;

public class TaskList {
  private TaskList taskList;

  @BeforeEach
  public void setUp() {
    taskList = new TaskList();
  }

  @Test
  public void addTodo_validTodoInput_success() {
    TodoCommand todo = new TodoCommand("go to the library");
    todo.run(taskList, ui, storage);
    assetEquals(1, taskList.size());
    assertEquals("go to the library", taskList.getTask(0).getDescription());
    assertEquals(false, taskList.getTask(0).isDone());
  }
}

package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;
import rickshaw.task.Todo;

public class TodoCommand extends Command {
    private final String description;
  /**
   * Constructs a new TodoCommand with given description
   * @param description The description of the todo task
   */
  public TodoCommand(String description) {
    this.description = description;
  }

  /**
   * Adds a new todo task to the task list, display task added message and save tasks to storage
   * @param tasks The task list
   * @param ui The UI component
   * @param storage The storage component
   */
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task newTodo = new Todo(description);
    tasks.addTask(newTodo);
    ui.showTaskAdded(newTodo, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}

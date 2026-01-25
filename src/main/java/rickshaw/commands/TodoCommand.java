package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;
import rickshaw.task.Todo;

public class TodoCommand extends Command {
  private final String description;

  public TodoCommand(String description) {
    this.description = description;
  }

  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task newTodo = new Todo(description);
    tasks.addTask(newTodo);
    ui.showTaskAdded(newTodo, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}

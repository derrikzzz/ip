package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;
import rickshaw.task.Event;

public class EventCommand extends Command {
  private final String description;
  private final String from;
  private final String to;

  public EventCommand(String description, String from, String to) {
    this.description = description;
    this.from = from;
    this.to = to;
  }

  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task newEvent = new Event(description, from, to);
    tasks.addTask(newEvent);
    ui.showTaskAdded(newEvent, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}

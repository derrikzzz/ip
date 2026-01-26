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

  /**
   * Constructs a new EventCommand with given description, from and to
   * @param description The description of the event task
   * @param from The start date and time of the event task
   * @param to The end date and time of the event task
   */
  public EventCommand(String description, String from, String to) {
    this.description = description;
    this.from = from;
    this.to = to;
  }

  /**
   * Adds a new event task to the task list, display task added message and save tasks to storage
   * @param tasks The task list
   * @param ui The UI component
   * @param storage The storage component
   */
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task newEvent = new Event(description, from, to);
    tasks.addTask(newEvent);
    ui.showTaskAdded(newEvent, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}

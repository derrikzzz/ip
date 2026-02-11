# Rickshaw User Guide

Rickshaw is a task management chatbot that helps you track your todos, deadlines and events.
It supports both a command-line interface and a graphical user interface.

## Quick Start

1. Ensure you have **JDK 17** installed.
2. Download the latest `rickshaw.jar` from the releases page.
3. Run the application:
   ```
   java -jar rickshaw.jar
   ```
4. Type commands in the input box and press Enter.

## Features

### Adding a todo: `todo`

Adds a task with no date attached.

Format: `todo DESCRIPTION`

Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a deadline: `deadline`

Adds a task with a due date.

Format: `deadline DESCRIPTION /by DATE_TIME`

- `DATE_TIME` must follow the format `d/MM/yyyy HHmm` (e.g. `2/12/2024 1800`).

Example: `deadline submit report /by 2/12/2024 1800`

```
Got it. I've added this task:
  [D][ ] submit report (by: Dec 02 2024, 6:00 PM)
Now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds a task with a start and end time.

Format: `event DESCRIPTION /from START /to END`

Example: `event team meeting /from 2pm /to 4pm`

```
Got it. I've added this task:
  [E][ ] team meeting (from: 2pm to: 4pm)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Displays all tasks in the list.

Format: `list`

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit report (by: Dec 02 2024, 6:00 PM)
3.[E][ ] team meeting (from: 2pm to: 4pm)
```

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark TASK_NUMBER`

Example: `mark 1`

```
Nice! I've marked this task as done:
  [T][X] read book
```

### Marking a task as not done: `unmark`

Marks the specified task as not done.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Deleting a task: `delete`

Removes the specified task from the list.

Format: `delete TASK_NUMBER`

Example: `delete 2`

```
Noted. I've removed this task:
  [D][ ] submit report (by: Dec 02 2024, 6:00 PM)
Now you have 2 tasks in the list.
```

### Finding tasks by keyword: `find`

Finds all tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Example: `find book`

```
Here are the matching tasks in your list:
1.[T][ ] read book
```

### Tagging a task: `tag`

Adds a tag to the specified task. Tags cannot contain `|` or `,` characters.

Format: `tag TASK_NUMBER TAG`

Example: `tag 1 urgent`

```
Added tag urgent to task 1
```

### Removing a tag: `untag`

Removes a tag from the specified task.

Format: `untag TASK_NUMBER TAG`

Example: `untag 1 urgent`

```
OK, I've untagged this task:
  [T][ ] read book
```

### Exiting the application: `bye`

Exits the application.

Format: `bye`

```
Bye. Hope to see you again soon!
```

## Data Storage

Tasks are saved automatically to `data/rickshaw.txt` after every command that modifies the list.
The file is created automatically if it does not exist.

## Command Summary

| Command    | Format                                       |
|------------|----------------------------------------------|
| `todo`     | `todo DESCRIPTION`                           |
| `deadline` | `deadline DESCRIPTION /by DATE_TIME`         |
| `event`    | `event DESCRIPTION /from START /to END`      |
| `list`     | `list`                                       |
| `mark`     | `mark TASK_NUMBER`                           |
| `unmark`   | `unmark TASK_NUMBER`                         |
| `delete`   | `delete TASK_NUMBER`                         |
| `find`     | `find KEYWORD`                               |
| `tag`      | `tag TASK_NUMBER TAG`                         |
| `untag`    | `untag TASK_NUMBER TAG`                       |
| `bye`      | `bye`                                        |

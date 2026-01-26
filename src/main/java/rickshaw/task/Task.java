package rickshaw.task;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; 
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getIcon() {
        return this.isDone ? "X" : "";
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIcon(), this.description);
    }

    public abstract String toFileFormat();

}

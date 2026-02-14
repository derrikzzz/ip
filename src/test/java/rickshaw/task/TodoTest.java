package rickshaw.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void constructor_validDescription_success() {
        Todo todo = new Todo("read book");
        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone());
        assertTrue(todo.getTags().isEmpty());
    }

    @Test
    public void toString_notDone_correctFormat() {
        Todo todo = new Todo("read book");
        assertEquals("[T][] read book", todo.toString());
        assertTrue(todo.getTags().isEmpty());
    }

    @Test
    public void toString_done_correctFormat() {
        Todo todo = new Todo("read book");
        todo.markDone();
        assertEquals("[T][X] read book", todo.toString());
        assertTrue(todo.getTags().isEmpty());
    }

    @Test
    public void toString_withTags_includesTags() {
        Todo todo = new Todo("read book");
        todo.addTag("urgent");
        assertTrue(todo.toString().contains("#urgent"));
    }

    @Test
    public void toFileFormat_notDone_correctFormat() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
        assertTrue(todo.getTags().isEmpty());
    }

    @Test
    public void toFileFormat_done_correctFormat() {
        Todo todo = new Todo("read book");
        todo.markDone();
        assertEquals("T | 1 | read book", todo.toFileFormat());
        assertTrue(todo.getTags().isEmpty());
    }

    @Test
    public void toFileFormat_withTags_includesTags() {
        Todo todo = new Todo("read book");
        todo.addTag("fun");
        assertEquals("T | 0 | read book | fun", todo.toFileFormat());
    }

    @Test
    public void equals_sameDescription_returnsTrue() {
        Todo todo1 = new Todo("read book");
        Todo todo2 = new Todo("read book");
        assertEquals(todo1, todo2);
        assertTrue(todo1.getTags().isEmpty());
        assertTrue(todo2.getTags().isEmpty());
    }

    @Test
    public void equals_differentDescription_returnsFalse() {
        Todo todo1 = new Todo("read book");
        Todo todo2 = new Todo("write essay");
        assertNotEquals(todo1, todo2);
        assertTrue(todo1.getTags().isEmpty());
        assertTrue(todo2.getTags().isEmpty());
    }

    @Test
    public void equals_sameDescriptionDifferentDoneStatus_returnsTrue() {
        Todo todo1 = new Todo("read book");
        Todo todo2 = new Todo("read book");
        todo2.markDone();
        assertEquals(todo1, todo2);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Todo todo = new Todo("read book");
        assertNotEquals(null, todo);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        Todo todo = new Todo("read book");
        assertNotEquals("read book", todo);
    }

    @Test
    public void markDone_setsIsDoneTrue() {
        Todo todo = new Todo("read book");
        todo.markDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void markUndone_setsIsDoneFalse() {
        Todo todo = new Todo("read book");
        todo.markDone();
        todo.markUndone();
        assertFalse(todo.isDone());
    }
}

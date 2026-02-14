package rickshaw.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_validInput_success() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        assertEquals("return book", deadline.getDescription());
        assertFalse(deadline.isDone());
        assertTrue(deadline.getTags().isEmpty());
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("return book", "2024-12-01");
        });
        assertTrue(e.getMessage().contains("Invalid date format"));
    }

    @Test
    public void constructor_missingTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("return book", "1/12/2024");
        });
    }

    @Test
    public void toString_notDone_correctFormat() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        assertEquals("[D][] return book (by: Dec 01 2024, 6:00 PM)", deadline.toString());
    }

    @Test
    public void toString_done_correctFormat() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        deadline.markDone();
        assertEquals("[D][X] return book (by: Dec 01 2024, 6:00 PM)", deadline.toString());
    }

    @Test
    public void toFileFormat_notDone_correctFormat() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        assertEquals("D | 0 | return book | 1/12/2024 1800", deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_done_correctFormat() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        deadline.markDone();
        assertEquals("D | 1 | return book | 1/12/2024 1800", deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_withTags_includesTags() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        deadline.addTag("library");
        assertEquals("D | 0 | return book | 1/12/2024 1800 | library", deadline.toFileFormat());
    }

    @Test
    public void equals_sameDescriptionAndDate_returnsTrue() {
        Deadline d1 = new Deadline("return book", "1/12/2024 1800");
        Deadline d2 = new Deadline("return book", "1/12/2024 1800");
        assertEquals(d1, d2);
    }

    @Test
    public void equals_differentDescription_returnsFalse() {
        Deadline d1 = new Deadline("return book", "1/12/2024 1800");
        Deadline d2 = new Deadline("buy book", "1/12/2024 1800");
        assertNotEquals(d1, d2);
    }

    @Test
    public void equals_differentDate_returnsFalse() {
        Deadline d1 = new Deadline("return book", "1/12/2024 1800");
        Deadline d2 = new Deadline("return book", "2/12/2024 1800");
        assertNotEquals(d1, d2);
    }

    @Test
    public void equals_differentDoneStatus_returnsFalse() {
        Deadline d1 = new Deadline("return book", "1/12/2024 1800");
        Deadline d2 = new Deadline("return book", "1/12/2024 1800");
        d2.markDone();
        assertNotEquals(d1, d2);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        assertNotEquals(null, deadline);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        Todo todo = new Todo("return book");
        assertNotEquals(deadline, todo);
    }

    @Test
    public void markDone_setsIsDoneTrue() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        deadline.markDone();
        assertTrue(deadline.isDone());
    }

    @Test
    public void markUndone_setsIsDoneFalse() {
        Deadline deadline = new Deadline("return book", "1/12/2024 1800");
        deadline.markDone();
        deadline.markUndone();
        assertFalse(deadline.isDone());
    }
}

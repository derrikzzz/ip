package rickshaw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import rickshaw.Parser;
import rickshaw.RickshawException;
import rickshaw.commands.Command;
import rickshaw.commands.DeadlineCommand;

public class ParserTest {

  @Test
  public void parseDeadline_validInput_success() throws RickshawException {
    Parser parser = new Parser();
    String input = "deadline return book /by Tuesday";

    Command result = parser.parseDeadline(input);

    if (result instanceof DeadlineCommand) {
      DeadlineCommand deadline = (DeadlineCommand) result;
      assertEquals("return book", deadline.getDescription());
      assertEquals("Tuesday", deadline.getBy());
    }
  }

  @Test
  public void parseDeadline_missingTime_throwsException() {
    Parser parser = new Parser();
    String input = "deadline return book";

    try {
      parser.parseDeadline(input);
      fail("Expected RickshawException to be thrown");
    } catch (RickshawException e) {
      assertEquals("I recognise that you want to add a deadline task, but the format is incorrect. Usage: deadline <description> /by <time>", e.getMessage());
    }
  }
}

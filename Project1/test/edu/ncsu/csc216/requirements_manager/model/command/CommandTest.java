package edu.ncsu.csc216.requirements_manager.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommandTest {
	
	
	
	@Test
	public void testCommandValid() {
		assertDoesNotThrow(
				() -> new Command(Command.CommandValue.REVIEW, null),
				"Unexpected exception");
		
		assertDoesNotThrow(
				() -> new Command(Command.CommandValue.ASSIGN, "Command Information"),
				"Unexpected exception");
	}
	
	@Test
	public void testCommandInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Command(null, "Command Information"));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REVIEW, "Command Information"));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.CONFIRM, "Command Information"));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REOPEN, "Command Information"));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.RESUBMIT, "Command Information"));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, null));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REJECT, null));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.BACKLOG, null));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, ""));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REJECT, ""));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.BACKLOG, ""));	
	}
	
	@Test
	public void testGetCommand() {
		Command c = new Command(Command.CommandValue.BACKLOG, "Command Information");
		assertEquals(Command.CommandValue.BACKLOG, c.getCommand(), "Command is incorrect");
	}
	
	@Test
	public void testGetCommandInformation() {
		Command c = new Command(Command.CommandValue.BACKLOG, "Command Information");
		assertEquals("Command Information", c.getCommandInformation(), "Command information incorrect");
	}
}

	

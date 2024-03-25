package edu.ncsu.csc216.requirements_manager.model.command;

/**
 * Command creates objects that encapsulate user actions (or transitions) that cause the state of a 
 * UserStory to update.
 * 
 * @author Madelynn Moore
 *
 */
public class Command {
	
	/** Represents one of the seven possible commands that a user can make for the user story FSM */
	private CommandValue command;
	/** Needed information when switching to a stage where additional information is needed  */
	private String commandInformation;
	
	/** All the possible command values that can cause transitions in our FSM */
	public enum CommandValue { BACKLOG, ASSIGN, REVIEW, CONFIRM, REOPEN, REJECT, RESUBMIT }

	/**
	 * Constructs a Command object with the given parameters Command command and String 
	 * commandInformation. Checks if the command is null. Checks if commandInformation is a null or
	 * empty String for any BACKLOG, ASSIGN, or REJECT command. Checks if the commandInformation is
	 * not null for any REVIEW, CONFIRM, REOPEN, or RESUBMIT commands.
	 * @throws IllegalArgumentException if command is null
	 * @throws IllegalArgumentException if command is BACKLOG, ASSIGN, or REJECT and has a null or 
	 * empty String
	 * @throws IllegalArgumentException if the command is REVIEW, CONFIRM, REOPEN, or RESUBMIT and has
	 * a non-null String
	 * @param command the stage in the FSM that Command is 
	 * @param commandInformation the needed additional information for stage (if applicable)
	 */
	public Command(CommandValue command, String commandInformation) {
		if (command == null) {
			throw new IllegalArgumentException();
		}
		if ((command == Command.CommandValue.BACKLOG || command == Command.CommandValue.ASSIGN 
				|| command == Command.CommandValue.REJECT) && (commandInformation == null 
				|| "".equals(commandInformation))) {
			throw new IllegalArgumentException();
		}
		if ((command == Command.CommandValue.REVIEW || command == Command.CommandValue.CONFIRM 
				|| command == Command.CommandValue.REOPEN || command == Command.CommandValue.RESUBMIT) 
				&& commandInformation != null) {
			throw new IllegalArgumentException();
		}
		this.command = command;
		this.commandInformation = commandInformation;
	}

	/**
	 * Returns one of the possible commands for the FSM
	 * @return command the command
	 */
	public CommandValue getCommand() {
		return command;
	}

	/**
	 * Returns the additional command information necessary for a given instance  
	 * @return commandInformation the command information
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
	
}

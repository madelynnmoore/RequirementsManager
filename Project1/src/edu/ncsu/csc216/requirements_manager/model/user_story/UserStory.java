package edu.ncsu.csc216.requirements_manager.model.user_story;

import edu.ncsu.csc216.requirements_manager.model.command.Command;

/**
 * UserStory represents an user story managed by our system. An UserStory knows its storyId, state, 
 * title, user, action, value, priority, developerId, and rejectionReason. Each UserStory has its own 
 * state, which is updated from Commands propagated to it from the UI.
 * @author Maddie Moore
 *
 */
public class UserStory {
	/** A constant string for the submitted state’s name */
	public static final String SUBMITTED_NAME = "Submitted";
	/** A constant string for the backlog state’s name */
	public static final String BACKLOG_NAME = "Backlog";
	/** A constant string for the working state’s name */
	public static final String WORKING_NAME = "Working";
	/** A constant string for the verifying state’s name */
	public static final String VERIFYING_NAME = "Verifying";
	/** A constant string for the completed state’s name */
	public static final String COMPLETED_NAME = "Completed";
	/** A constant string for the rejected state’s name */
	public static final String REJECTED_NAME = "Rejected";
	/** A constant string for the priority of “High” */
	public static final String HIGH_PRIORITY = "High";
	/** A constant string for the priority of “Medium” */
	public static final String MEDIUM_PRIORITY = "Medium";
	/** A constant string for the priority of “Low” */
	public static final String LOW_PRIORITY = "Low";
	/** A constant string for the rejection reason of “Duplicate”. */
	public static final String DUPLICATE_REJECTION = "Duplicate";
	/** A constant string for the rejection reason of “Inappropriate” */
	public static final String INAPPROPRIATE_REJECTION = "Inapropriate";
	/**  A constant string for the rejection reason of “Infeasible” */
	public static final String INFEASIBLE_REJECTION = "Infeasible";
	/** Unique id for an user story */
	private int storyId;
	/** Current state for the user story of type UserStoryState */
	private String state;
	/** Title of the user story as provided by the user on creation */
	private String title;
	/** The user information for the statement “As a [user]” */
	private String user;
	/** The action information for the statement “I want to [action]” */
	private String action;
	/** The value information for the statement “so I can [value]” */
	private String value;
	/** The user story’s priority. */
	private String priority;
	/** The user story’s assigned developer. */
	private String developerId;
	/** The user story’s rejection reason */
	private String rejectionReason;
	/** Counter used to account for the id of each user story. */
	private static int counter;
	/** Represents the current state of the UserStory. */
	private static UserStoryState currentState;
	/** Final instance of the SubmittedState inner class */
	private final Class<SubmittedState> submittedState = SubmittedState.class;
	/** Final instance of the BacklogState inner class. */
	private final Class<BacklogState> backlogState = BacklogState.class;
	/** Final instance of the WorkingState inner class. */
	private final Class<WorkingState> workingState = WorkingState.class;
	/** Final instance of the VerifyingState inner class */
	private final Class<VerifyingState> verifyingState = VerifyingState.class;
	/**  Final instance of the CompletedState inner class. */
	private final Class<CompletedState> completedState = CompletedState.class;
	/** Final instance of the RejectedState inner class. */
	private final Class<RejectedState> rejectedState = RejectedState.class;
	
	
	/**
	 * Constructs a UserStory from the provided title, user, action, and value.
	 * @param title title of user story
	 * @param user user information for the statement “As a [user]”
	 * @param action action information for the statement “I want to [action]”
	 * @param value value information for the statement “so I can [value]”
	 * @throws IllegalArgumentException if title is null
	 * @throws IllegalArgumentException if user is null
	 * @throws IllegalArgumentException if action is null
	 * @throws IllegalArgumentException if value is null
	 */
	public UserStory(String title, String user, String action, String value) {
		this(counter, SUBMITTED_NAME, title, user, action, value, null, null, null);
	}
	
	/**
	 * Constructs a UserStory from the provided information from the ProjectReader class.
	 * @param id unique id for an user story
	 * @param state state for the user story of type UserStoryState
	 * @param title title of user story
	 * @param user user information for the statement “As a [user]”
	 * @param action action information for the statement “I want to [action]”
	 * @param value value information for the statement “so I can [value]”
	 * @param priority user story’s priority.
	 * @param developerId user story’s assigned developer
	 * @param rejectionReason user story’s rejection reason
	 */
	public UserStory(int id, String state, String title, String user, String action, String value, 
			String priority, String developerId, String rejectionReason) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		if (user == null || "".equals(user)) {
			throw new IllegalArgumentException("User cannot be null or empty");
		}
		if (action == null || "".equals(action)) {
			throw new IllegalArgumentException("Action cannot be null or empty");
		}
		if (value == null || "".equals(value)) {
			throw new IllegalArgumentException("Value cannot be null or empty");
		}
		if (id > counter) {
			setCounter(id);
		}
		setId(counter);
		setState(state);
		setTitle(title);
		setUser(user);
		setAction(action);
		setValue(value);
		setPriority(priority);
		setDeveloperId(developerId);
		setRejectionReason(rejectionReason);
		incrementCounter();
	}

	/**
	 * Retrieves the id of the user story
	 * @return the id number of the current user story
	 */
	public int getId() {
		return storyId;
	}

	/**
	 * Sets the id of the user story
	 * @param id id number of the user story
	 */
	private void setId(int id) {
		this.storyId = id;
	}
	
	/**
	 * Returns the state of the current user story
	 * @return the state of user story
	 */
	public String getState() {
		return currentState.getStateName();
	}

	/**
	 * Sets the state of the current user story
	 * @param state the state to set
	 * @throws IllegalArgumentException if the state parameter is null or empty
	 * @throws IllegalArgumentException if the state parameter is not a valid state
	 */
	private void setState(String state) {
		if (state == null || "".equals(state)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(SUBMITTED_NAME)) {
			currentState = new SubmittedState();
		}
		else if (state.equals(BACKLOG_NAME)) {
			currentState = new BacklogState();
		}
		else if (state.equals(WORKING_NAME)) {
			currentState = new WorkingState();
		}
		else if (state.equals(VERIFYING_NAME)) {
			currentState = new VerifyingState();
		}
		else if (state.equals(COMPLETED_NAME)) {
			currentState = new CompletedState();
		}
		else if (state.equals(REJECTED_NAME)) {
			currentState = new RejectedState();
		}
		else 
			throw new IllegalArgumentException();
		this.state = state;
	}

	/**
	 * Retrieves the title of the current user story
	 * @return the title of the user story
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the user story
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Returns the user of the current user story
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user of the current user story
	 * @param user the user to set
	 */
	private void setUser(String user) {
		if (user == null || "".equals(user)) {
			throw new IllegalArgumentException();
		}
		this.user = user;
	}

	/**
	 * Returns the action of the current user story
	 * @return the action of the user story
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action of the current user story
	 * @param action the action to set
	 */
	private void setAction(String action) {
		if (action == null || "".equals(action)) {
			throw new IllegalArgumentException();
		}
		this.action = action;
	}

	/**
	 * Returns the value of the current user story
	 * @return the value of the user story
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the current user story
	 * @param value the value to set
	 */
	private void setValue(String value) {
		if (value == null || "".equals(value)) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	/**
	 * Returns the priority of the current user story
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Sets the priority of the current user story
	 * @param priority the priority to set
	 */
	private void setPriority(String priority) {
		if (state.equals(SUBMITTED_NAME) || state.equals(REJECTED_NAME)) {
			if (priority != null) {
				throw new IllegalArgumentException("Invalid priority");
			}
			this.priority = priority;
		}
		if (state.equals(BACKLOG_NAME) || state.equals(WORKING_NAME) || state.equals(VERIFYING_NAME) 
				|| state.equals(COMPLETED_NAME)) {
			if (!priority.equals(LOW_PRIORITY) && !priority.equals(MEDIUM_PRIORITY) 
					&& !priority.equals(HIGH_PRIORITY)) {
				throw new IllegalArgumentException("Invalid priority");
			}
			this.priority = priority;
		}
	}

	/**
	 * Returns the developer id of the current user story
	 * @return the developer id of the user story
	 */
	public String getDeveloperId() {
		return developerId;
	}

	/**
	 * Sets the developer id of the user story
	 * @param developerId the developer id to set
	 */
	private void setDeveloperId(String developerId) {
		if (state.equals(SUBMITTED_NAME) || state.equals(BACKLOG_NAME) || state.equals(REJECTED_NAME)) {
			if (developerId != null) {
				throw new IllegalArgumentException("Invalid Developer ID");
			}
			this.developerId = developerId;
		}
		if (state.equals(WORKING_NAME) || state.equals(VERIFYING_NAME) || state.equals(COMPLETED_NAME)) {
			if (developerId == null) {
				throw new IllegalArgumentException("Invalid Developer ID");
			}
			this.developerId = developerId;
		}
	}

	/**
	 * Returns the rejection reason of the current user story
	 * @return the rejectionReason of the user story
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}

	/**
	 * Sets the rejection reason of the current user story
	 * @param rejectionReason the rejectionReason to set
	 */
	private void setRejectionReason(String rejectionReason) {
		if (state.equals(SUBMITTED_NAME) || state.equals(BACKLOG_NAME) || state.equals(VERIFYING_NAME) 
				|| state.equals(VERIFYING_NAME) || state.equals(COMPLETED_NAME)) {
			if (rejectionReason != null) {
				throw new IllegalArgumentException("Invalid Rejection Reason");
			}
			this.rejectionReason = rejectionReason;
		}
		if (state.equals(REJECTED_NAME)) {
			if (!rejectionReason.equals(DUPLICATE_REJECTION) && !rejectionReason.equals(INAPPROPRIATE_REJECTION)
					&& !rejectionReason.equals(INFEASIBLE_REJECTION)) {
				throw new IllegalArgumentException("Invalid rejection reason");
			}
			this.rejectionReason = rejectionReason;
		}
	}

	/**
	 * Increments the counter field by 1
	 */
	public static void incrementCounter() {
		setCounter(counter + 1);
	}
	
	/** 
	 * sets the counter field to a specific value
	 * @param newCounter value to set counter 
	 */
	public static void setCounter(int newCounter) {
		counter = newCounter;
	}
	
	
	/**
	 * Returns all of the information of a user story into a String
	 */
	@Override
	public String toString() {
		if (currentState.getClass() == submittedState) {
			return "* " + storyId + "," + state + "," + title + "," + "\n" + "- " + user + "\n" 
					+ "- " + action + "\n" + "- " + value + "\n";
		}
		else if (currentState.getClass() == backlogState) {
			return "* " + storyId + "," + state + "," + title + "," + priority + "," + "\n" + "- " 
					+ user + "\n" + "- " + action + "\n" + "- " + value + "\n";
		}
		else if (currentState.getClass() == completedState || currentState.getClass() == verifyingState || 
				currentState.getClass() == workingState) {
			return "* " + storyId + "," + state + "," + title + "," + priority + "," + developerId + ","
					+ "\n" + "- " + user + "\n" + "- " + action + "\n" + "- " + value + "\n";
		}
		else if (currentState.getClass() == rejectedState) {
			return "* " + storyId + "," + state + "," + title + "," + rejectionReason + "\n" + "- " 
			+ user + "\n" + "- " + action + "\n" + "- " + value + "\n";
		}
		else 
			throw new IllegalArgumentException("Invalid state");
	}
	
	/**
	 * Updates the user story to call to a specific command so that the state can transition
	 * @param command command to call to transition to a new state
	 */
	public void update(Command command) {
		currentState.updateState(command);
	}

	/**
	 * Interface for states in the UserStory State Pattern.  All 
	 * concrete user story states must implement the UserStoryState interface.
	 * The UserStoryState interface should be a private interface of the 
	 * UserStory class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface UserStoryState {
		
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	/** 
	 * Concrete private inner class that represents the submitted state of the Requirements Manager FSM.
	 */
	private class SubmittedState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.REJECT) {
				currentState = new RejectedState();
				priority = null;
				developerId = null;
				rejectionReason = command.getCommandInformation();
			}
			else if (command.getCommand() == Command.CommandValue.BACKLOG) {
				currentState = new BacklogState();
				priority = command.getCommandInformation();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return SUBMITTED_NAME;
		}
	}
	
	/** 
	 * Concrete private inner class that represents the backlog state of the Requirements Manager FSM.
	 */
	private class BacklogState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.ASSIGN) {
				currentState = new WorkingState();
				developerId = command.getCommandInformation();
			}
			else if (command.getCommand() == Command.CommandValue.REJECT) {
				currentState = new RejectedState();
				priority = null;
				developerId = null;
				rejectionReason = command.getCommandInformation();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return BACKLOG_NAME;
		}
	}
	
	/** 
	 * Concrete private inner class that represents the working state of the Requirements Manager FSM.
	 */
	private class WorkingState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.ASSIGN) {
				currentState = new WorkingState();
				developerId = command.getCommandInformation();
			}
			else if (command.getCommand() == Command.CommandValue.REJECT) {
				currentState = new RejectedState();
				priority = null;
				developerId = null;
				rejectionReason = command.getCommandInformation();
			}
			else if (command.getCommand() == Command.CommandValue.REOPEN) {
				developerId = null;
				currentState = new BacklogState();
			}
			else if (command.getCommand() == Command.CommandValue.REVIEW) {
				currentState = new VerifyingState();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return WORKING_NAME;
		}
	}
	
	/** 
	 * Concrete private inner class that represents the verifying state of the Requirements Manager FSM.
	 */
	private class VerifyingState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.REOPEN) {
				currentState = new WorkingState();
			}
			else if (command.getCommand() == Command.CommandValue.CONFIRM) {
				currentState = new CompletedState();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}
	
	/** 
	 * Concrete private inner class that represents the completed state of the Requirements Manager FSM.
	 */
	private class CompletedState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.REOPEN) {
				currentState = new WorkingState();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return COMPLETED_NAME;
		}
	}
	
	/** 
	 * Concrete private inner class that represents the rejected state of the Requirements Manager FSM.
	 */
	private class RejectedState implements UserStoryState {
		/**
		 * Update the UserStory based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the UserStory's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.RESUBMIT) {
				rejectionReason = null;
				currentState = new SubmittedState();
			}
			else
				throw new UnsupportedOperationException();
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return REJECTED_NAME;
		}
	}	
}

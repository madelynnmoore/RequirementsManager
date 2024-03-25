package edu.ncsu.csc216.requirements_manager.model.user_story;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.requirements_manager.model.command.Command;
import edu.ncsu.csc216.requirements_manager.model.manager.Project;

class UserStoryTest {
	
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
	
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		try {
		UserStory.setCounter(0);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to reset counter");
		}
	}
	
	@Test
	public void testShortUserStory() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Creates the user story to test
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project 1");
		projects.add(project);
		UserStory shortUserStory = new UserStory("title1", "user1", "action1", "value1");
		project.addUserStory(shortUserStory);
		//Assert that all values are added correctly
		assertAll("User Story not properly added", 
				() -> assertEquals(0, shortUserStory.getId()), 
				() -> assertEquals(SUBMITTED_NAME, shortUserStory.getState()),
				() -> assertEquals("title1", shortUserStory.getTitle()),
				() -> assertEquals("user1", shortUserStory.getUser()),
				() -> assertEquals("action1", shortUserStory.getAction()),
				() -> assertEquals("value1", shortUserStory.getValue()),
				() -> assertEquals(null, shortUserStory.getDeveloperId()),
				() -> assertEquals(null, shortUserStory.getPriority()),
				() -> assertEquals(null, shortUserStory.getRejectionReason()));
	}
	
	@Test
	public void testLongUserStory() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Creates the user story to test
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project 1");
		projects.add(project);
		UserStory longUserStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		project.addUserStory(longUserStory);
		//Assert that all values are added correctly
				assertAll("User Story not properly added", 
						() -> assertEquals(0, longUserStory.getId()), 
						() -> assertEquals(SUBMITTED_NAME, longUserStory.getState()),
						() -> assertEquals("title1", longUserStory.getTitle()),
						() -> assertEquals("user1", longUserStory.getUser()),
						() -> assertEquals("action1", longUserStory.getAction()),
						() -> assertEquals("value1", longUserStory.getValue()),
						() -> assertEquals(null, longUserStory.getDeveloperId()),
						() -> assertEquals(null, longUserStory.getPriority()),
						() -> assertEquals(null, longUserStory.getRejectionReason()));
	}
	
	@Test
	public void testInvalidUserStory() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Creates the user story to test
		Exception exception1 = assertThrows(IllegalArgumentException.class, 
				() -> new UserStory(0, SUBMITTED_NAME, null, "user1", "action1", "value1", null, null, null));
		assertEquals(exception1.getMessage(), "Title cannot be null or empty");
		Exception exception2 = assertThrows(IllegalArgumentException.class, 
				() -> new UserStory(0, SUBMITTED_NAME, "title1", null, "action1", "value1", null, null, null));
		assertEquals(exception2.getMessage(), "User cannot be null or empty");
		Exception exception3 = assertThrows(IllegalArgumentException.class, 
				() -> new UserStory(0, SUBMITTED_NAME, "title1", "user1", null, "value1", null, null, null));
		assertEquals(exception3.getMessage(), "Action cannot be null or empty");
		Exception exception4 = assertThrows(IllegalArgumentException.class, 
				() -> new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", null, null, null, null));
		assertEquals(exception4.getMessage(), "Value cannot be null or empty");
	}
	
	@Test
	public void testGetId() {
		//Resets the counter 
				try {
					setUp();
				} catch (Exception e) {
					fail("Unable to reset counter");
				}
		//Creates the user story to test
		UserStory userStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		UserStory userStory1 = new UserStory(1, REJECTED_NAME, "title2", "user2", "action2", "value2", 
				null, null, "Duplicate");
		UserStory userStory2 = new UserStory(7, BACKLOG_NAME, "title3", "user3", "action3", "value3", 
				"High", null, null);
		UserStory userStory3 = new UserStory("title4", "user4", "action4", "value4");
		//Assert that the getId method works properly
		assertEquals(0, userStory.getId());
		assertEquals(1, userStory1.getId());
		assertEquals(7, userStory2.getId());
		assertEquals(8, userStory3.getId());
	}
	
	@Test 
	public void testGetState() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create user stories to get state from
		UserStory submittedStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		assertEquals(SUBMITTED_NAME, submittedStory.getState());
		
		UserStory backlogStory = new UserStory(1, BACKLOG_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, null, null);
		assertEquals(BACKLOG_NAME, backlogStory.getState());
		
		UserStory workingStory = new UserStory(2, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals(WORKING_NAME, workingStory.getState());
		
		UserStory verifyingStory = new UserStory(3, VERIFYING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals(VERIFYING_NAME, verifyingStory.getState());
		
		UserStory completedStory = new UserStory(4, COMPLETED_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals(COMPLETED_NAME, completedStory.getState());
		
		UserStory rejectedStory = new UserStory(5, REJECTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, DUPLICATE_REJECTION);
		assertEquals(REJECTED_NAME, rejectedStory.getState());
	}
	
	@Test
	public void testToString() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create user stories to get string from
		UserStory submittedStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		assertEquals("* 0,Submitted,title1,\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", submittedStory.toString());
		UserStory backlogStory = new UserStory(1, BACKLOG_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, null, null);
		assertEquals("* 1,Backlog,title1,High,\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", backlogStory.toString());
		UserStory workingStory = new UserStory(2, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals("* 2,Working,title1,High,developerId1,\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", workingStory.toString());
		UserStory verifyingStory = new UserStory(3, VERIFYING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals("* 3,Verifying,title1,High,developerId1,\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", verifyingStory.toString());
		UserStory completedStory = new UserStory(4, COMPLETED_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId1", null);
		assertEquals("* 4,Completed,title1,High,developerId1,\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", completedStory.toString());
		UserStory rejectedStory = new UserStory(5, REJECTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, DUPLICATE_REJECTION);
		assertEquals("* 5,Rejected,title1,Duplicate\n" 
				+ "- user1\n" 
				+ "- action1\n" 
				+ "- value1\n", rejectedStory.toString());
	}
	
	@Test
	public void testSubmittedA() {
		//Resets the counter 
			try {
				setUp();
			} catch (Exception e) {
				fail("Unable to reset counter");
			}
		//Create command object 
		Command command = new Command(Command.CommandValue.BACKLOG, HIGH_PRIORITY);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		//Assert that userStory is in Submitted State
		assertEquals(SUBMITTED_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(BACKLOG_NAME, userStory.getState());
		assertEquals(HIGH_PRIORITY, userStory.getPriority());
	}
	
	@Test
	public void testSubmittedB() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REJECT, DUPLICATE_REJECTION);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, SUBMITTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, null);
		//Assert that userStory is in Submitted State
		assertEquals(SUBMITTED_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(REJECTED_NAME, userStory.getState());
		assertEquals(null, userStory.getPriority());
		assertEquals(null, userStory.getDeveloperId());
		assertEquals(DUPLICATE_REJECTION, userStory.getRejectionReason());
	}
	
	@Test
	public void testBacklogA() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.ASSIGN, "developerId");
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, BACKLOG_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, null, null);
		//Assert that userStory is in Submitted State
		assertEquals(BACKLOG_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(WORKING_NAME, userStory.getState());
		assertEquals("developerId", userStory.getDeveloperId());
	}
	
	@Test
	public void testBacklogB() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REJECT, DUPLICATE_REJECTION);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, BACKLOG_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, null, null);
		//Assert that userStory is in Submitted State
		assertEquals(BACKLOG_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(REJECTED_NAME, userStory.getState());
		assertEquals(null, userStory.getPriority());
		assertEquals(null, userStory.getDeveloperId());
		assertEquals(DUPLICATE_REJECTION, userStory.getRejectionReason());
	}
	
	@Test
	public void testWorkingA() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.ASSIGN, "developerId1");
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(WORKING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(WORKING_NAME, userStory.getState());
		assertEquals("developerId1", userStory.getDeveloperId());
	}
	
	@Test
	public void testWorkingB() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REJECT, DUPLICATE_REJECTION);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(WORKING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(REJECTED_NAME, userStory.getState());
		assertEquals(null, userStory.getPriority());
		assertEquals(null, userStory.getDeveloperId());
		assertEquals(DUPLICATE_REJECTION, userStory.getRejectionReason());
	}
	
	@Test
	public void testWorkingC() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REOPEN, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(WORKING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(BACKLOG_NAME, userStory.getState());
		assertEquals(null, userStory.getDeveloperId());
	}
	
	@Test
	public void testWorkingD() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REVIEW, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, WORKING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(WORKING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(VERIFYING_NAME, userStory.getState());
	}
	
	@Test
	public void testVerifyingA() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REOPEN, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, VERIFYING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(VERIFYING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(WORKING_NAME, userStory.getState());
	}
	
	@Test
	public void testVerifyingB() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.CONFIRM, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, VERIFYING_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(VERIFYING_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(COMPLETED_NAME, userStory.getState());
	}
	
	@Test
	public void testCompletedA() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.REOPEN, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, COMPLETED_NAME, "title1", "user1", "action1", "value1", 
				HIGH_PRIORITY, "developerId", null);
		//Assert that userStory is in Submitted State
		assertEquals(COMPLETED_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(WORKING_NAME, userStory.getState());
	}
	
	@Test
	public void testRejectedA() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		//Create command object 
		Command command = new Command(Command.CommandValue.RESUBMIT, null);
		//Create user stories to get string from
		UserStory userStory = new UserStory(0, REJECTED_NAME, "title1", "user1", "action1", "value1", 
				null, null, DUPLICATE_REJECTION);
		//Assert that userStory is in Submitted State
		assertEquals(REJECTED_NAME, userStory.getState());
		//Attempt to update the userStory
		userStory.update(command);
		//Assert that the transition was successful
		assertEquals(SUBMITTED_NAME, userStory.getState());
		assertEquals(null, userStory.getRejectionReason());
	}
}
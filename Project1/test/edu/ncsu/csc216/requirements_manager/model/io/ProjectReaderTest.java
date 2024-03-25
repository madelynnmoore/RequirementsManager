package edu.ncsu.csc216.requirements_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.requirements_manager.model.manager.Project;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

class ProjectReaderTest {
	/** Valid test Submitted User Story file */
	private final String submittedFile = "test-files/expected_submitted.txt";
	/** Valid Backlog User Story file */
	private final String backlogFile = "test-files/expected_backlog.txt";
	/** Valid Project File with 1 Project */
	private final String validFile1 = "test-files/project1.txt";
	/** Invalid test File 1 */
	private final String invalidFile1 = "test-files/project4.txt";
//	/** Valid test file */
//	private final String workingFile = "test-files/expected_working.txt";
//	/** Valid test file */
//	private final String verifyingFile = "test-files/expected_verifying.txt";
//	/** Valid test file */
//	private final String completedFile = "test-files/expected_completed.txt";
//	/** Valid test file */
//	private final String rejectedFile = "test-files/expected_rejected.txt";
	
	
	
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
	public void testSubmittedFile() {
		//Resets the counter 
			try {
				setUp();
			} catch (Exception e) {
				fail("Unable to reset counter");
			}
		UserStory submittedUserStory = new UserStory(0, "Submitted", "title", "user", "action",
				"value", null, null, null);
		Project submittedProject = new Project("Project");
		submittedProject.addUserStory(submittedUserStory);
		ArrayList<Project> projectsFromFile = null;
		UserStory.setCounter(0);
		try {
			projectsFromFile = ProjectReader.readProjectFile(submittedFile);
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		if (projectsFromFile == null) {
			fail("Project File read as null");
		}
//		assertEquals(submittedUserStory.getId(), projectsFromFile.get(0).getUserStoryById(0).getId());
		assertEquals(submittedProject.getProjectName(), projectsFromFile.get(0).getProjectName());
		assertEquals(submittedUserStory.getId(), projectsFromFile.get(0).getUserStoryById(0).getId());
		assertEquals(submittedUserStory.getState(), projectsFromFile.get(0).getUserStoryById(0).getState());
		assertEquals(submittedUserStory.getTitle(), projectsFromFile.get(0).getUserStoryById(0).getTitle());
		assertEquals(submittedUserStory.getUser(), projectsFromFile.get(0).getUserStoryById(0).getUser());
		assertEquals(submittedUserStory.getAction(), projectsFromFile.get(0).getUserStoryById(0).getAction());
		assertEquals(submittedUserStory.getValue(), projectsFromFile.get(0).getUserStoryById(0).getValue());
		assertNull(projectsFromFile.get(0).getUserStoryById(0).getPriority());
		assertNull(projectsFromFile.get(0).getUserStoryById(0).getDeveloperId());
		assertNull(projectsFromFile.get(0).getUserStoryById(0).getRejectionReason());
	}
	
	@Test
	public void testBacklogFile() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
	Project backlogProject = new Project("Project");
	UserStory backlogUserStory = new UserStory(0, "Backlog", "title", "user", "action",
			"value", "Low", null, null);
	backlogProject.addUserStory(backlogUserStory);
	ArrayList<Project> projectsFromFile = null;
	UserStory.setCounter(0);
	try {
		projectsFromFile = ProjectReader.readProjectFile(backlogFile);
	} catch (Exception e) {
		fail("Unable to reset counter");
	}
	if (projectsFromFile == null) {
		fail("Project File read as null");
	}
//	assertEquals(submittedUserStory.getId(), projectsFromFile.get(0).getUserStoryById(0).getId());
	assertEquals(backlogProject.getProjectName(), projectsFromFile.get(0).getProjectName());
	assertEquals(backlogUserStory.getId(), projectsFromFile.get(0).getUserStoryById(0).getId());
	assertEquals(backlogUserStory.getState(), projectsFromFile.get(0).getUserStoryById(0).getState());
	assertEquals(backlogUserStory.getTitle(), projectsFromFile.get(0).getUserStoryById(0).getTitle());
	assertEquals(backlogUserStory.getUser(), projectsFromFile.get(0).getUserStoryById(0).getUser());
	assertEquals(backlogUserStory.getAction(), projectsFromFile.get(0).getUserStoryById(0).getAction());
	assertEquals(backlogUserStory.getValue(), projectsFromFile.get(0).getUserStoryById(0).getValue());
	assertEquals(backlogUserStory.getPriority(), projectsFromFile.get(0).getUserStoryById(0).getPriority());
	assertEquals(backlogUserStory.getDeveloperId(), projectsFromFile.get(0).getUserStoryById(0).getDeveloperId());
	assertEquals(backlogUserStory.getRejectionReason(), projectsFromFile.get(0).getUserStoryById(0).getRejectionReason());
	}
	
	@Test
	public void testInvalidFiles() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		assertEquals(0, ProjectReader.readProjectFile(invalidFile1).size());
	}
	
	@Test
	public void testValidFile1() {
		assertEquals(1, ProjectReader.readProjectFile(validFile1).size());
	}
}

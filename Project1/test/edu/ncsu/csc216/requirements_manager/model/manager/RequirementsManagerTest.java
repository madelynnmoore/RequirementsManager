package edu.ncsu.csc216.requirements_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.requirements_manager.model.command.Command;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

class RequirementsManagerTest {
	/** Valid test file */
	private final String submittedFile = "test-files/expected_submitted.txt";
//	/** Valid test file */
//	private final String backlogFile = "test-files/expected_backlog.txt";
//	/** Valid test file */
//	private final String workingFile = "test-files/expected_working.txt";
//	/** Valid test file */
//	private final String verifyingFile = "test-files/expected_verifying.txt";
//	/** Valid test file */
//	private final String completedFile = "test-files/expected_completed.txt";
//	/** Valid test file */
//	private final String rejectedFile = "test-files/expected_rejected.txt";

	@BeforeEach
	public void setUp() throws Exception {
		RequirementsManager.getInstance().resetManager();
	}
	
	@Test
	public void testGetInstanceNoInstance() {
		RequirementsManager instance1 = RequirementsManager.getInstance();
		assertEquals(instance1, RequirementsManager.getInstance());
	}
	
	@Test
	public void testSaveCurrentProject() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("test-files/project1");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to erase file contents");
		}
		writer.print("");
		writer.close();
		assertThrows(IllegalArgumentException.class, 
				() -> RequirementsManager.getInstance().saveCurrentProjectToFile("test-files/project1"));
		RequirementsManager.getInstance().createNewProject("Project 1");
		RequirementsManager.getInstance().loadProject("Project 1");
		RequirementsManager.getInstance().addUserStoryToProject("title", "user", "action", "value");
		RequirementsManager.getInstance().addUserStoryToProject("title", "user", "action", "value");
		RequirementsManager.getInstance().saveCurrentProjectToFile("test-files/project1");
	}
	
	@Test
	public void testLoadProjectsFromFile() {
		RequirementsManager.getInstance();
		RequirementsManager.getInstance().loadProjectsFromFile(submittedFile);
		assertEquals("Project", RequirementsManager.getInstance().getProjectList()[0]);
	}
	
	@Test
	public void testGetProjectName() {
		RequirementsManager.getInstance().resetManager();
		RequirementsManager.getInstance().createNewProject("Project 1");
		RequirementsManager.getInstance().loadProject("Project 1");
		assertEquals("Project 1", RequirementsManager.getInstance().getProjectName());
	}
	
	@Test
	public void testGetProjectList() {
		assertEquals(0, RequirementsManager.getInstance().getProjectList().length);
		RequirementsManager.getInstance().createNewProject("Project");
		assertEquals(1, RequirementsManager.getInstance().getProjectList().length);
	}
	
	@Test
	public void testResetManager() {
		assertEquals(0, RequirementsManager.getInstance().getProjectList().length);
		RequirementsManager.getInstance().createNewProject("Project");
		assertEquals(1, RequirementsManager.getInstance().getProjectList().length);
		RequirementsManager.getInstance().resetManager();
		assertEquals(0, RequirementsManager.getInstance().getProjectList().length);
	}
	
	@Test
	public void testExecuteCommand() {
		assertNull(RequirementsManager.getInstance().getProjectName());
		RequirementsManager.getInstance().createNewProject("Project 3");
		assertEquals("Project 3", RequirementsManager.getInstance().getProjectName());
		RequirementsManager.getInstance().addUserStoryToProject("title", "user", "action", "value");
		assertEquals("title", RequirementsManager.getInstance().getUserStoryById(0).getTitle());
		assertEquals("user", RequirementsManager.getInstance().getUserStoryById(0).getUser());
		assertEquals("action", RequirementsManager.getInstance().getUserStoryById(0).getAction());
		assertEquals("value", RequirementsManager.getInstance().getUserStoryById(0).getValue());
		Command c = new Command(Command.CommandValue.BACKLOG, "High");
		RequirementsManager.getInstance().executeCommand(0, c);
	}
	
	@Test
	public void testGetUserStoryById() {
		RequirementsManager.getInstance().createNewProject("Project 2");
		assertEquals("Project 2", RequirementsManager.getInstance().getProjectName());
		RequirementsManager.getInstance().addUserStoryToProject("title", "user", "action", "value");
		assertEquals("title", RequirementsManager.getInstance().getUserStoryById(0).getTitle());
		assertEquals("user", RequirementsManager.getInstance().getUserStoryById(0).getUser());
		assertEquals("action", RequirementsManager.getInstance().getUserStoryById(0).getAction());
		assertEquals("value", RequirementsManager.getInstance().getUserStoryById(0).getValue());
	}
	
	@Test
	public void testDeleteUserStoryById() {
		assertNull(RequirementsManager.getInstance().getProjectName());
		RequirementsManager.getInstance().createNewProject("Project 3");
		assertEquals("Project 3", RequirementsManager.getInstance().getProjectName());
		RequirementsManager.getInstance().addUserStoryToProject("title", "user", "action", "value");
		assertEquals(1, RequirementsManager.getInstance().getUserStoriesAsArray().length);
		RequirementsManager.getInstance().deleteUserStoryById(0);
		assertEquals(0, RequirementsManager.getInstance().getUserStoriesAsArray().length);
	}
	
	@Test
	public void testCurrentProjectNull() {
		assertNull(RequirementsManager.getInstance().getProjectName());
	}
}

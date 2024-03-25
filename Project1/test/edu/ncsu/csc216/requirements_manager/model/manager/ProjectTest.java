package edu.ncsu.csc216.requirements_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

class ProjectTest {

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
	public void testAddInvalidUserStory() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		Project project = new Project("Project");
		project.addUserStory("title", "user", "action", "value");
	}
	
	@Test
	public void testDeleteUserStoryById() {
		//Resets the counter 
		try {
			setUp();
		} catch (Exception e) {
			fail("Unable to reset counter");
		}
		Project project = new Project("Project");
		project.addUserStory("title", "user", "action", "value");
		project.deleteUserStoryById(0);
	}
}

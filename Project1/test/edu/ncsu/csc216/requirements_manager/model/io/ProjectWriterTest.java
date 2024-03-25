package edu.ncsu.csc216.requirements_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.requirements_manager.model.manager.Project;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

class ProjectWriterTest {
	
	@Test 
	public void testWriteProjectUserStorySubmitted() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Submitted", "title", "user", "action", "value", null, null, null);
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_submitted.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_submitted.txt", "test-files/actual_submitted.txt");
	}
	
	@Test
	public void testWriteValidProjectToFile()  {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project 1");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Rejected", "title1", "user1", "action1", "value1", null, null, "Duplicate");
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_project1.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_project1.txt", "test-files/actual_project1.txt");
	}
	
	@Test 
	public void testWriteProjectUserStoryBacklog() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Backlog", "title", "user", "action", "value", "Low", null, null);
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_backlog.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_backlog.txt", "test-files/actual_backlog.txt");
	}
	
	@Test 
	public void testWriteProjectUserStoryWorking() {
		Project project = new Project("Project");
		UserStory userStory = new UserStory(0, "Working", "title", "user", "action", "value", "Medium", "id", null);
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_working.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_working.txt", "test-files/actual_working.txt");
	}
	
	@Test 
	public void testWriteProjectUserStoryVerifying() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Verifying", "title", "user", "action", "value", "Medium", "id", null);
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_verifying.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_verifying.txt", "test-files/actual_verifying.txt");
	}
	
	@Test 
	public void testWriteProjectUserStoryCompleted() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Completed", "title", "user", "action", "value", "Medium", "id", null);
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_completed.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_completed.txt", "test-files/actual_completed.txt");
	}
	
	@Test 
	public void testWriteProjectUserStoryRejected() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project project = new Project("Project");
		projects.add(project);
		UserStory userStory = new UserStory(0, "Rejected", "title", "user", "action", "value", null, null, "Duplicate");
		project.addUserStory(userStory);
		try {
			ProjectWriter.writeProjectToFile("test-files/actual_rejected.txt", project);
		} catch (Exception e) {
			fail("Cannot save file");
		}
		
		checkFiles("test-files/expected_rejected.txt", "test-files/actual_rejected.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}

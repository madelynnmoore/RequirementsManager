package edu.ncsu.csc216.requirements_manager.model.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.requirements_manager.model.command.Command;
import edu.ncsu.csc216.requirements_manager.model.io.ProjectReader;
import edu.ncsu.csc216.requirements_manager.model.io.ProjectWriter;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

/**
 * RequirementsManager controls the creation and modification of many Projects.
 * @author Maddie Moore
 *
 */
public class RequirementsManager {
	/** List of projects */
	private static ArrayList<Project> projects;
	/** Current Project that is being referred */
	private static Project currentProject;
	/** Singleton instance of RequirementsManager */
	private static RequirementsManager singleton;
	
	/** 
	 * Constructs a RequirementsManager object to hold information regarding Projects
	 */
	private RequirementsManager() {
		projects = new ArrayList<>();
		currentProject = null;
	}
	
	/**
	 * Returns an instance of RequirementsManager. getInstance() will check if the singleton is null. 
	 * If the singleton is null, then the getInstance() calls the private RequirementsManager() 
	 * constructor to create the single instance. The singleton instance is always returned; the 
	 * null check is there to make sure there always is one.
	 * @return instance of RequirementsManager
	 */
	public static RequirementsManager getInstance() {
		if (singleton == null) {
			singleton = new RequirementsManager();
		}
		return singleton;
	}
	
	/**
	 * If the currentProject is null or if there are no UserStorys in the currentProject an 
	 * IllegalArgumentException should be thrown. Otherwise, write the Project to the file using 
	 * the ProjectWriter class.
	 * @param fileName name of file to save project to
	 * @throws IllegalArgumentException if there are no UserStorys in the currentProject
	 */
	public void saveCurrentProjectToFile(String fileName) {
		if (currentProject == null) {
			throw new IllegalArgumentException();
		}
		try {
			ProjectWriter.writeProjectToFile(fileName, currentProject);
		}
		catch (Exception e) {	
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Uses the ProjectReader to read the given fileName. The returned list of Projects are added to 
	 * the projects field. The first project in the list returned from ProjectReader is made the 
	 * currentProject.
	 * @param fileName name of file
	 */
	public void loadProjectsFromFile(String fileName) {
		try {
			projects.addAll(ProjectReader.readProjectFile(fileName));
			currentProject = projects.get(0);
		}
		catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new Project with the given name and adds it to the end of the projects list. The 
	 * project is then loaded as the currentProject by calling the loadProject(String projectName) 
	 * method. An IllegalArgumentException is thrown if the projectName parameter is null, empty 
	 * string, or a duplicate of an existing project name (case-insensitive).
	 * @param projectName name of project
	 * @throws IllegalArgumentException if the projectName is null, empty string, or duplicate
	 */
	public void createNewProject(String projectName) {
		if (projectName == null || "".equals(projectName)) {
			throw new IllegalArgumentException();
		}
		Project project = new Project(projectName);
		projects.add(project);
		UserStory.setCounter(0);
		currentProject = project;
	}
	
	/**
	 * Returns an array of user stories
	 * @return String array of user stories
	 */
	public String[][] getUserStoriesAsArray() {
		String[][] stories = new String[currentProject.getUserStories().size()][4];
		for (int i = 0; i < currentProject.getUserStories().size(); i++) {
			stories[i][0] = Integer.toString(currentProject.getUserStoryById(i).getId());
			stories[i][1] = currentProject.getUserStoryById(i).getState();
			stories[i][2] = currentProject.getUserStoryById(i).getTitle();
			stories[i][3] = currentProject.getUserStoryById(i).getDeveloperId();
		}
		return stories;
	}
	
	/**
	 * Returns a user story with the given id
	 * @param id id number of user story to be returned
	 * @return user story with given id
	 */
	public UserStory getUserStoryById(int id) {
		return currentProject.getUserStoryById(id);
	}
	
	/**
	 * Finds the UserStory with the given id and update it by passing in the given Command.
	 * @param id id of user story to update
	 * @param c command to pass to user story to update
	 */
	public void executeCommand(int id, Command c) {
		if (currentProject != null) {
			currentProject.executeCommand(id, c);
		}
	}
	
	/**
	 * Removes the UserStory with the given id from the list.
	 * @param id id of userstory to delete
	 */
	public void deleteUserStoryById(int id) {
		if (currentProject != null) {
			currentProject.deleteUserStoryById(id);
		}
	}
	
	/**
	 * Adds a user story to a project given the title, user, action, and value
	 * @param title title of user story
	 * @param user user of user story
	 * @param action action of user story
	 * @param value value of user story
	 */
	public void addUserStoryToProject(String title, String user, String action, String value) {
		UserStory userStory = new UserStory(title, user, action, value);
		currentProject.addUserStory(userStory);
	}
	
	/** 
	 * Find the Project with the given name in the list, makes it the active or currentProject, 
	 * and sets the user story id for that project so that any new UserStorys added to the project 
	 * are created with the next correct id.
	 * @param projectName project name to load
	 */
	public void loadProject(String projectName) {
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getProjectName().equals(projectName)) {
				currentProject = projects.get(i);
				UserStory.setCounter(projects.get(i).getUserStories().size() + 1);
				if (!currentProject.getUserStories().isEmpty()) {
					currentProject.setUserStoryId();
				}
			}
		}
	}
	
	/**
	 * Returns the project name for the currentProject. If the currentProject is null, then null is 
	 * returned.
	 * @return projectName if not null
	 */
	public String getProjectName() {
		
		String projectName;
		if (currentProject == null) {
			projectName = null;
		}
		else 
			projectName = currentProject.getProjectName();
		return projectName;
	}
	
	/** 
	 * Returns a String array of project names in the order they are listed in the projects list. 
	 * This is used by the GUI to populate the project drop down.
	 * @return String array of the project names
	 */
	public String[] getProjectList() {
		String[] projectList = new String[projects.size()];
		for (int i = 0; i < projects.size(); i++) {
			projectList[i] = projects.get(i).getProjectName();
		}
		return projectList;
	}
	
	/** 
	 * Used to set singleton to null and reset the RequirementsManager instance
	 */
	protected void resetManager() {
		singleton = null;
	}
}

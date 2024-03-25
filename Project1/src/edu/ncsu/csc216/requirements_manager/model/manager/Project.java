package edu.ncsu.csc216.requirements_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.requirements_manager.model.command.Command;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

/** 
 * Concrete class that maintains its name and a list of UserStorys in the project.
 * @author Maddie Moore
 *
 */
public class Project {
	/** Name of the project */
	private String projectName;
	/** List of user stories */
	private ArrayList<UserStory> stories;
	
	/** 
	 * Constructs a project object. Checks if the project name is a null or an empty String before 
	 * constructing the object.
	 * @param projectName name of project
	 * @throws IllegalArgumentException if the project name is null or an empty String
	 */
	public Project(String projectName) {
		if (projectName == null || "".equals(projectName)) {
			throw new IllegalArgumentException();
		}
		setProjectName(projectName);
		this.stories = new ArrayList<>();
	}

	/**
	 * set the counter for the UserStory instances to the value of the maximum id in the list of 
	 * UserStorys for the project + 1.
	 */
	public void setUserStoryId() {
		int max = -1;
		for (int i = 0; i < stories.size(); i++) {
			if (max < stories.get(i).getId()) {
				max = stories.get(i).getId();
			}
		}
		UserStory.setCounter(max);
	}
	
	/**
	 * Retrieves the project name
	 * @return the projectName name of project
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the name of the project 
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * adds the user story to the list in sorted order by id. The list will be maintained in sorted 
	 * order, so you will be able to add a new story in order. If a story already exists with the 
	 * given id, an IllegalArgumentException will be thrown.
	 * @param story user story object
	 * @throws IllegalArgumentException if a story already exists with the given id
	 */
	public void addUserStory(UserStory story) {
		int max = 0;
		if (stories.size() != 0) {
			for (int i = 0; i < stories.size(); i++) {
				if (story.getId() == stories.get(i).getId()) {
					throw new IllegalArgumentException();
				}
			}
			for (int i = 0; i < stories.size(); i++) {
				if (i == 0) {
					max = stories.get(i).getId();
					continue;
				}
			}
		}
		if (max > story.getId()) {
			max = story.getId();
		}
		stories.add(max, story);
	}
	
	
	/** 
	 *  creates a new UserStory in the submitted state, adds it to the list in sorted order, and 
	 *  returns the id. If a story already exists with the given id, an IllegalArgumentException 
	 *  will be thrown.
	 * @param title title of user story
	 * @param user user of user story 
	 * @param action action of user story
	 * @param value value of user story
	 * @throws IllegalArgumentException if a story already exists with the given id
	 * @return the id number of the user story
	 */
	public int addUserStory(String title, String user, String action, String value) {
		UserStory story = new UserStory(title, user, action, value);
		addUserStory(story);
		return story.getId();
	}
	
	/** 
	 * returns the List of UserStorys
	 * @return an ArrayList of UserStorys
	 */
	public ArrayList<UserStory> getUserStories() {
		return stories;
	}
	
	/**
	 * returns the UserStory in the list with the given id. If there is no UserStory with that id, 
	 * the method returns null.
	 * @param id id of the UserStory
	 * @return the UserStory retrieve by the id
	 */
	public UserStory getUserStoryById(int id) {
		UserStory userStory = null;
		for (int i = 0; i < stories.size(); i++) {
			if (id == stories.get(i).getId()) {
				userStory = stories.get(i);
			}
		}
		return userStory;
	}
	
	/**
	 * Removes the UserStory with the given id from the list.
	 * @param id id of the UserStory to delete
	 */
	public void deleteUserStoryById(int id) {
		for (int i = 0; i < stories.size(); i++) {
			if (id == stories.get(i).getId()) {
				stories.remove(i);
			}
		}
	}
	
	/**
	 * Finds the UserStory with the given id and update it by passing in the given Command.
	 * @param id id of UserStory
	 * @param c command to update UserStory
	 */
	public void executeCommand(int id, Command c) {
		stories.get(id).update(c);
	}
}
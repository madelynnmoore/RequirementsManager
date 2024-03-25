package edu.ncsu.csc216.requirements_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.requirements_manager.model.manager.Project;
import edu.ncsu.csc216.requirements_manager.model.user_story.UserStory;

/**
 * Processes a file containing project and user story information and creates a List of Projects and 
 * their associated UserStorys.
 * 
 * @author Maddie Moore
 *
 */
public class ProjectReader {
	
	/**
	 * readProjectFile receives a String with the file name to read from and converts the information
	 * into a readable ArrayList. If the file cannot be loaded because it doesn’t exist, the method 
	 * will throw an IllegalArgumentException with the message “Unable to load file.” Any invalid 
	 * user stories or projects (i.e., they cannot be constructed, information is missing, or there 
	 * is too much info for the item) are ignored.
	 * @param fileName name of the file to be read
	 * @throws IllegalArgumentException with message "Unable to load file" if the file cannot be loaded
	 * @return an array of the Projects with user stories
	 * @throws FileNotFoundException if there is an error in finding the file
	 */
	public static ArrayList<Project> readProjectFile(String fileName) {
		//create scanner
		Scanner fileReader;
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} 
		//Create string object
	    String fileString = "";
	    //Create projects array list
	    ArrayList<Project> projects = new ArrayList<Project>(); 
	    //initialize string object
	    while (fileReader.hasNextLine()) { 
	    	fileString = fileString + fileReader.nextLine() + "\n";
	    }
	    if (!fileString.contains("#")) {
	    	return projects;
	    }
	    //scanner to scan fileString string object
	    Scanner stringReader = new Scanner(fileString); 
	    //loop thru string object with # delimiter for projects
	    while (stringReader.hasNext()) {
	    	//set delimiter for String scanner as #
		    stringReader.useDelimiter("\\r?\\n?[#] ");
	    	//creates string for project name
	    	String nextLine = stringReader.next();
	    	//creates project with processproject
	    	Project project = processProject(nextLine);
	    	//creates scanner for scanning file after project name
	    	Scanner projectReader = new Scanner(fileString);
	    	//set delimiter for String scanner as *
	    	projectReader.useDelimiter("\\r?\\n?[*] ");
	    	//loop thru string object with * delimiter for projects
	    	projectReader.next();
	    	while (projectReader.hasNext()) {
	    		//creates string object for processUserStory
		    	String nextStory = projectReader.next();
		    	//adds story from processUserStory to project
		    	project.addUserStory(processUserStory(nextStory));
		    }
	    	//closes the projectReader used to scan the project
	    	projectReader.close();
	    	//adds the project to the projects arraylist
	    	projects.add(project);
	    }
	    //closes scanner used to scan the file
	    stringReader.close();
	    //returns the projects arraylist
	    return projects;
	}
	
	/**
	 * Processes a line of a file into a Project object
	 * @param nextLine the next line of the file
	 * @return the Project object
	 */
	private static Project processProject(String nextLine) {
		//creates scanner to scan an individual project from file
		Scanner projectReader = new Scanner(nextLine);
		//creates a string object for the name of the project
		String projectName = projectReader.nextLine();
		Project project = new Project(projectName);
		projectReader.close();
	    return project;
	}
	
	/**
	 * Processes a line of a file into a UserStory object
	 * @param nextLine the next line of the file
	 * @return the UserStory object
	 */
	private static UserStory processUserStory(String nextLine) {
		UserStory userStory = null;
		//creates scanner to scan an indivudal user story from project from file
		try {
			Scanner storyReader = new Scanner(nextLine);
			storyReader.useDelimiter("\\r?\\n?[-] ");
			String storyInfo = storyReader.nextLine();
			//user = storyReader.next()
			String user = storyReader.next().trim();
			//action = storyReader.next()
			String action = storyReader.next().trim();
			//value = storyReader.next()
			String value = storyReader.next().trim();
			storyReader.close();
			Scanner storyHeader = new Scanner(storyInfo);
			//sets delimiter as ","
			storyReader.useDelimiter(",");
			//creates a int to hold story id = storyReader.next()
			int storyId = Integer.parseInt(storyHeader.next());
			//creates a string to hold story state = storyReader.next()
			String state = storyHeader.next();
			//creates a string to hold story title = storyReader.next()
			String title = storyHeader.next();
			//creates a string to hold priority = null
			String priority = null;
			//creates a string to hold developerid = null;
			String developerId = null;
			//creates a string to hold rejectionreason = null;
			String rejectionReason = null;
			if ("Submitted".equals(state)) {
				priority = null;
				developerId = null;
				rejectionReason = null;
			}
			else if ("Backlog".equals(state)) {
				priority = storyHeader.next();;
				developerId = null;
				rejectionReason = null;
			}
			else if ("Working".equals(state)) {
				priority = storyHeader.next();
				developerId = storyHeader.next();
				rejectionReason = null;
			}
			else if ("Verifying".equals(state)) {
				priority = storyHeader.next();
				developerId = storyHeader.next();
				rejectionReason = null;
			}
			else if ("Completed".equals(state)) {
				priority = storyHeader.next();
				developerId = storyHeader.next();
				rejectionReason = null;
			}
			else if ("Rejected".equals(state)) {
				priority = null;
				developerId = null;
				rejectionReason = storyHeader.next();
			}
			//constructs user story with parameters above
			userStory = new UserStory(storyId, state, title, user, action, value, priority, developerId, 
					rejectionReason); 
			storyHeader.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Fail");
		}
		return userStory;
	}
}

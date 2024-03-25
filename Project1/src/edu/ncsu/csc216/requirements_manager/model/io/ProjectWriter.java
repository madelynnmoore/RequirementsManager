package edu.ncsu.csc216.requirements_manager.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import edu.ncsu.csc216.requirements_manager.model.manager.Project;

/**
 * Writes a single Project and its UserStorys to the file name provided.
 * @author Maddie Moore
 *
 */
public class ProjectWriter {
	
	/** 
	 * Writes a single Project and its UserStorys to the file name provided.
	 * @param fileName name of file for information to be written in
	 * @param project name of Project 
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public static void writeProjectToFile(String fileName, Project project) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (Exception e1) {
			throw new IllegalArgumentException();
		}
		try {
			fileWriter.println("# " + project.getProjectName().toString());
			for (int i = 0; i < project.getUserStories().size(); i++) {
				if (project.getUserStoryById(i) == null) {
					continue;
				}
				fileWriter.println(project.getUserStoryById(i).toString());
			}
			fileWriter.close();
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
}

package backEnd;

import java.util.Scanner;

/**
 * The Driver class contains all the code to take input from the user so that
 * they can create their personal task list. The runCalendar() method controls
 * all of the main options, which are then split into their own respective
 * methods.
 * 
 * @author Evan Nadelbach
 *
 */
public class Driver {

	/**
	 * The main method calls the runCalendar() method.
	 * 
	 * @param args is the main String array argument.
	 */
	public static void main(String[] args) {
		try {
			runCalendar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The runCalendar() method runs everything related to the user's task list.
	 * It creates the task list that stores the users events, takes input from
	 * the user, and will run through whatever option the user chooses.
	 * 
	 * This method contains all of the options for the user to select from,
	 * which include creating a new task, showing current tasks, setting the
	 * priority of an existing task, completing a task, showing completed tasks,
	 * and removing tasks.
	 * 
	 * @return true if the method ran successfully.
	 * @throws InterruptedException if the program was interrupted.
	 */
	public static boolean runCalendar() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		TaskList list = new TaskList();
		boolean isCompleted = false;

		System.out.println("Hello, and welcome to the automated "
				+ "task calendar system.\n\n");
		Thread.sleep(2000);

		while (!isCompleted) {

			System.out.println(
					"\n\nList of options: Show tasks, create new task, "
							+ "change priority, complete task, "
							+ "show completed tasks, remove a task");

			System.out.print("Type what you want to do, or type"
					+ " in \"quit\" to quit: ");
			String option = sc.nextLine();

			// Quits the program
			if (option.equalsIgnoreCase("quit")
					|| option.equalsIgnoreCase("q")) {
				System.out.println("Program ended.");
				isCompleted = true;

				// Shows all tasks in the system
			} else if (option.equalsIgnoreCase("show tasks")) {
				System.out.println(list);

				// Creates a new task
			} else if (option.equalsIgnoreCase("create new task")
					|| option.equalsIgnoreCase("create")) {
				createNewTask(list, sc);

				// Changes the priority of an existing task
			} else if (option.equalsIgnoreCase("change priority")) {
				if (!list.isEmpty()) {
					changePriority(list, sc);
				} else {
					System.out.println("\n\nThere are no tasks in the system.");
				}

				// Sets the priority of the inputed task to complete
			} else if (option.equalsIgnoreCase("complete task")) {
				if (!list.isEmpty()) {
					completeTask(list, sc);
				} else {
					System.out.println("\n\nThere are no tasks in the system.");
				}

				// Shows only the completed tasks
			} else if (option.equalsIgnoreCase("Show completed tasks")) {
				System.out.println(list.showCompletedTasks());

				// Removes a task from the system
			} else if (option.equalsIgnoreCase("remove a task")) {
				if (!list.isEmpty()) {
					removeTask(list, sc);
				} else {
					System.out.println("\n\nThere are no tasks in the system.");
				}

				// Handles all invalid input options
			} else {
				System.out.println("\n\nInvalid option, please try again.");
			}
		}

		sc.close();

		return isCompleted;
	}

	/**
	 * This method creates a new task for the user's task list.
	 * 
	 * @param list contains the user's list.
	 * @param sc   contains the user's scanner object.
	 */
	public static void createNewTask(TaskList list, Scanner sc) {
		boolean validName = false;
		boolean validPriority = false;

		System.out.print(
				"\n\nEnter the name of the task, or type quit to quit: ");
		String name = sc.nextLine();

		/*
		 * Loop runs until the user quits this menu or types in a task with a
		 * valid name
		 */
		while (!validName) {

			// This is run if the user cancels this option entirely
			if (name.equalsIgnoreCase("quit") || name.equalsIgnoreCase("q")) {
				validName = true;
				validPriority = true;

				/*
				 * Checks to make sure there isn't already a task with the same
				 * name in the system
				 */
			} else if (list.contains(name)) {
				System.out.print("Task is already in the system! Type a "
						+ "different name, or quit to exit this option. ");
				name = sc.nextLine();

				// The user typed in a valid name, and this ends the loop
			} else {
				validName = true;

			}
		}

		/*
		 * Loop runs until the user quits this menu or types in a task with a
		 * valid priority
		 */
		while (!validPriority) {

			System.out.print("Enter its priority "
					+ "(Completed, Optional, Soon, Immediate), "
					+ "or type quit to exit this option: ");
			String priority = sc.nextLine();

			// This is run if the user cancels this option entirely
			if (priority.equalsIgnoreCase("quit")
					|| priority.equalsIgnoreCase("q")) {
				validPriority = true;

				// Checks to see if the inputed priority is valid
			} else if (!Priorities.validPriority(priority)) {
				System.out.print("Invalid priority input! ");

				/*
				 * The user typed in a valid priority, and creates the task to
				 * then be put into the system.
				 */
			} else {
				Priorities p = Priorities.ConvertToEnum(priority);
				list.addToList(new Task(name, p));
				validPriority = true;

			}
		}
	}

	/**
	 * This method changes the priority of an existing task.
	 * 
	 * @param list contains the user's list.
	 * @param sc   contains the user's scanner object.
	 */
	public static void changePriority(TaskList list, Scanner sc) {
		boolean validName = false;
		boolean validPriority = false;

		System.out.print("\n\nEnter the name of the task you want to edit, "
				+ "or type quit to exit this option: ");
		String name = sc.nextLine();

		/*
		 * Loop runs until the user quits this menu or types in a valid task
		 * name that is in the system
		 */
		while (!validName) {

			// This is run if the user cancels this option entirely
			if (name.equalsIgnoreCase("quit") || name.equalsIgnoreCase("q")) {
				validName = true;
				validPriority = true;

				// Runs if the inputed name matches a task in the system
			} else if (list.contains(name)) {
				validName = true;

				// Runs if the inputed name does not match a task in the system
			} else {
				System.out.print("Task is not in the system! "
						+ "Type another option, or quit to exit this option. ");
				name = sc.nextLine();

			}
		}

		// Loop runs until the user quits this menu or types in a valid priority
		while (!validPriority) {

			System.out.print("\n\nEnter the priority you want to set this task"
					+ " to, or type quit to exit this option: ");
			String priority = sc.nextLine();

			// This is run if the user cancels this option entirely
			if (priority.equalsIgnoreCase("quit")
					|| priority.equalsIgnoreCase("q")) {
				validPriority = true;

				// This changes the priority of the task to the user's input
			} else if (Priorities.validPriority(priority)) {
				list.changePriority(name, priority);
				System.out.println("Priority has been successfully changed.");
				validPriority = true;

				// This is run if the user typed in an invalid input
			} else {
				System.out.print("Invalid priority!");

			}
		}
	}

	/**
	 * This method sets the priority of a task to completed.
	 * 
	 * @param list contains the user's list.
	 * @param sc   contains the user's scanner object.
	 */
	public static void completeTask(TaskList list, Scanner sc) {
		boolean validOption = false;

		System.out.print("\n\nWhich task do you want to complete? "
				+ "(or type quit to exit this option) ");
		String name = sc.nextLine();

		// Loop runs until the user quits this menu or types in a valid name
		while (!validOption) {

			// This is run if the user cancels this option entirely
			if (name.equalsIgnoreCase("quit") || name.equalsIgnoreCase("q")) {
				validOption = true;

				// Changes the priority of the inputed task name
			} else if (list.contains(name)) {
				list.changePriority(name, "completed");
				validOption = true;

				// This is run if the user typed in an invalid input
			} else {
				System.out.print("Task is not in the system! "
						+ "Type another option, or quit to exit this option. ");
				name = sc.nextLine();

			}
		}
	}

	/**
	 * This method removes a task from the user's task list.
	 * 
	 * @param list contains the user's list.
	 * @param sc   contains the user's scanner object.
	 */
	public static void removeTask(TaskList list, Scanner sc) {
		boolean validOption = false;

		System.out.print("\n\nWhich task do you want to remove? "
				+ "(or type quit to exit this option) ");
		String name = sc.nextLine();

		// Loop runs until the user quits this menu or types in a valid name
		while (!validOption) {

			// This is run if the user cancels this option entirely
			if (name.equalsIgnoreCase("quit") || name.equalsIgnoreCase("q")) {
				validOption = true;

				// Removes the task from the system
			} else if (list.contains(name)) {
				list.removeFromList(name);
				System.out.println("Task successfully removed.");
				validOption = true;

				// Runs if the user typed in a task that is not in the system
			} else {
				System.out.print("Task is not in the system! "
						+ "Type another option, or quit to exit this option. ");
				name = sc.nextLine();

			}
		}
	}

}

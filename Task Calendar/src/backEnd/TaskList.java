package backEnd;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class contains the code for running a task list. It stores its tasks
 * using an unordered set of Task objects. This class contains many utility
 * methods for the main Driver class to use.
 * 
 * @author Evan Nadelbach
 *
 */
public class TaskList {
	Set<Task> tasks;

	/**
	 * Initializes the tasks HashSet.
	 */
	public TaskList() {
		tasks = new HashSet<>();
	}

	/**
	 * Returns whether the tasks HashSet is empty.
	 * 
	 * @return true if the tasks HashSet is empty.
	 */
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	/**
	 * Returns whether a task with the parameter name is in the system.
	 * 
	 * @param name is the name of the task.
	 * @return true if the list contains the task with the inputed name.
	 */
	public boolean contains(String name) {
		Iterator<Task> iter = tasks.iterator();
		while (iter.hasNext()) {
			if (iter.next().compareNames(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new task to the list.
	 * 
	 * @param task is the task to be added.
	 */
	public void addToList(Task task) {
		tasks.add(task);
	}

	/**
	 * Removes a task from the list.
	 * 
	 * @param name is the task to remove from the list.
	 */
	public void removeFromList(String name) {
		Iterator<Task> iter = tasks.iterator();
		while (iter.hasNext()) {
			Task t = iter.next();
			if (t.compareNames(name)) {
				tasks.remove(t);
			}
		}
	}

	/**
	 * Changes the priority of a task in the system.
	 * 
	 * @param name     is the task to be edited.
	 * @param priority is the priority the task will be changed to.
	 */
	public void changePriority(String name, String priority) {
		Iterator<Task> iter = tasks.iterator();
		while (iter.hasNext()) {
			Task t = iter.next();
			if (t.compareNames(name)) {
				t.setPriority(Priorities.ConvertToEnum(priority));
			}
		}

	}

	/**
	 * Creates a string that contains all of the user's completed tasks.
	 * 
	 * @return a String representation of all the user's completed tasks.
	 */
	public String showCompletedTasks() {
		String toReturn = "\n\nCompleted tasks:\n";
		if (tasks.isEmpty()) {
			toReturn = "\n\nThere are no tasks in the system.";
		} else {
			for (Task t : tasks) {
				if (t.comparePriorities("completed")) {
					toReturn += t.getName() + "\n";
				}
			}
		}
		return toReturn;
	}

	/**
	 * Creates a string that contains all of the user's tasks and their
	 * priorities.
	 */
	public String toString() {
		String toReturn = "\n\nTasks\t\t\tPriority\n";
		if (tasks.isEmpty()) {
			toReturn = "\n\nThere are no tasks in the system.";
		} else {
			for (Task t : tasks) {
				toReturn += t + "\n";
			}
		}
		return toReturn;
	}
}

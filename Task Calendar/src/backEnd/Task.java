package backEnd;

/**
 * This Task class contains methods for storing individual tasks. Tasks
 * represent something the user wants to complete, which is then put on their
 * own task list.
 * 
 * Each task is given a String name and a priority stored as an enum. Priorities
 * have several levels of attention, ranging from complete to requiring
 * immediate action. This class represents just one single task.
 * 
 * @author Evan Nadelbach
 *
 */
public class Task {
	private String name;
	private Priorities priority;

	/**
	 * Sets the task's name and priority.
	 * 
	 * @param name     is the name of the task.
	 * @param priority is the priority of the task.
	 */
	public Task(String name, Priorities priority) {
		this.name = name;
		this.priority = priority;
	}

	/**
	 * Sets the priority of the task.
	 * 
	 * @param priority is the priority the task will be changed to.
	 */
	public void setPriority(Priorities priority) {
		this.priority = priority;
	}

	/**
	 * Returns the name of the task.
	 * 
	 * @return the name of the task.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Compares this current task's name to an inputed name.
	 * 
	 * @param name is the name to compare to the current task's name.
	 * @return true if the names are the same.
	 */
	public boolean compareNames(String name) {
		return this.name.equals(name);
	}

	/**
	 * Compares this current task's priority to an inputed priority.
	 * 
	 * @param priority is the priority to compare to the current task's
	 *                 priority.
	 * @return true if the priorities are the same.
	 */
	public boolean comparePriorities(String priority) {
		return this.priority.toString().toLowerCase().equals(priority);
	}

	/**
	 * Creates a string that contains the task's name and priority.
	 */
	public String toString() {
		return name + "\t\t"
				+ Priorities.convertToReadableString(priority.toString());
	}
}

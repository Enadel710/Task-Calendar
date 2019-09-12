package backEnd;

/**
 * This Priorities enum contains several options that the user can pick to
 * signify when they should get their task done. It also contains several helper
 * methods that mostly help convert string priorities to the enum type.
 * 
 * @author Evan Nadelbach
 *
 */
public enum Priorities {
	COMPLETED, OPTIONAL, SOON, IMMEDIATE, ERROR;

	/**
	 * Converts a string input to an enum of type Priorities, or returns error
	 * if none of the options match.
	 * 
	 * @param priority is the priority to convert to the enum.
	 * @return the enum version of the inputed priority.
	 */
	public static Priorities ConvertToEnum(String priority) {
		priority = priority.toLowerCase();

		switch (priority) {
		case "completed":
			return COMPLETED;

		case "optional":
			return OPTIONAL;

		case "soon":
			return SOON;

		case "immediate":
			return IMMEDIATE;
		}

		return ERROR;
	}

	/**
	 * Converts a string input to a different string output that looks nicer
	 * when printed, or returns error if none of the options match.
	 * 
	 * @param priority is the priority to convert to a better looking String.
	 * @return the better looking String version of the inputed priority.
	 */
	public static String convertToReadableString(String priority) {
		priority = priority.toLowerCase();
		switch (priority) {
		case "completed":
			return "Completed";

		case "optional":
			return "Optional";

		case "soon":
			return "Soon";

		case "immediate":
			return "Immediate";
		}

		return "Error getting priority (invalid input?)";
	}

	/**
	 * Checks if a string input matches any of the valid priorities.
	 * 
	 * @param priority is the priority to check its validity.
	 * @return true if the inputed priority is valid.
	 */
	public static boolean validPriority(String priority) {
		if (priority.equalsIgnoreCase("completed")
				|| priority.equalsIgnoreCase("optional")
				|| priority.equalsIgnoreCase("soon")
				|| priority.equalsIgnoreCase("immediate")) {
			return true;
		}
		return false;
	}
}

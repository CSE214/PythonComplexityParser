package complexityparser;

/**
* The <code>Line</code> class handles parsing of lines of code for <code>PythonTracer</code>.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class Line {
	private static final int SPACE_COUNT = 4; // Number of spaces per indent
	
	/**
	 * Checks if the line is empty.
	 * 
	 * @param line
	 * 	The line of code to check.
	 * 
	 * @return
	 * 	True if line is empty, false otherwise.
	 */
	public static boolean isEmpty(String line) {
		return line.trim().equals("");
	}
	
	/**
	 * Checks if the line is a comment.
	 * 
	 * @param line
	 * 	The line of code to check.
	 * 
	 * @return
	 * 	True if line is a comment, false otherwise.
	 */
	public static boolean isComment(String line) {
		return line.charAt(0) == '#';
	}
	
	/**
	 * Returns the number of indents in the line.
	 * 
	 * @param line
	 * 	The line to check.
	 * 
	 * @return
	 * 	The number of indents in the line.
	 */
	public static int getIndentNumber(String line) {
		int spaceCount = 0;
		while (line.charAt(0) == ' ') {
			line = line.substring(1);
			spaceCount++;
		}
		return spaceCount/SPACE_COUNT;
	}
	
	/**
	 * Checks for a keyword in the beginning of the line.
	 * 
	 * @param line
	 * 	The line to check
	 * 
	 * @return
	 * 	True if the line has a keyword at the beginning of the line, false otherwise
	 */
	public static boolean hasKeyword(String line) {
		String firstWord = line.trim().split(" ")[0];
		return firstWord.equals("def") ||
			   firstWord.equals("for") ||
			   firstWord.equals("while") ||
			   firstWord.equals("if") ||
			   firstWord.equals("elif") ||
			   firstWord.equals("else");
	}
	
	/**
	 * Returns the keyword at the start of the line.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd><code>hasKeyword(line)</code> is true.
	 * </dl>
	 * 
	 * @param line
	 * 	The line to retrieve the keyword from
	 * 
	 * @return
	 * 	The line's keyword
	 */
	public static String getKeyword(String line) {
		return line.trim().split(" ")[0];
	}
	
	/**
	 * Gets the loop count of the for loop.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd><code>getKeyword(line)</code> returns <code>"for"</code>.
	 * </dl>
	 * 
	 * @param line
	 * 	The line to retrieve the loop count from
	 * 
	 * @return
	 * 	The loop count of the for loop.
	 */
	public static String getForLoopCount(String line) {
		if (line.trim().split(" ")[3].equals("N:")) {
			return "N";
		} else if (line.trim().split(" ")[3].equals("log_N:")) {
			return "log_N";
		} else {
			return "";
		}
	}
	
	/**
	 * Gets the loop variable for the while loop.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd><code>getKeyword(line)</code> returns <code>"while"</code>.
	 * </dl>
	 * 
	 * @param line
	 * 	The line to retrieve the loop variable from
	 * 
	 * @return
	 * 	The loop variable of the while loop
	 */
	public static String getWhileLoopVariable(String line) {
		return line.trim().split(" ")[1];
	}
	
	/**
	 * Checks if while loop variable is being updated.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd><code></code> returns <code>"while"</code>.
	 * </dl>
	 * 
	 * @param line
	 * 	The line to retrieve the loop variable from
	 * 
	 * @return
	 * 	True if the loopVariable is updated, false otherwise
	 */
	public static boolean updatesLoopVariable(String line, String loopVariable) {
		return line.trim().equals(loopVariable + " -= 1") ||
			   line.trim().equals(loopVariable + " /= 2");
	}
}

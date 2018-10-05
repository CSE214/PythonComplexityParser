package complexityparser;

/**
* The <code>Line</code> class handles parsing of lines of code for <code>PythonTracer</code>.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class CodeLine {
	private String line; // String representation of code
	private final int SPACE_COUNT = 4; // Number of spaces per indent
	
	/**
	 * @return 
	 *	The line of this instance
	 */
	public String getLine() {
		return line;
	}
	/**
	 * @param line 
	 * 	The new line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * Checks if the line is empty.
	 * 
	 * @param line
	 * 	The line of code to check.
	 * 
	 * @return
	 * 	True if line is empty, false otherwise.
	 */
	public boolean isEmpty() {
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
	public boolean isComment() {
		return line.trim().charAt(0) == '#';
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
	public int getIndentCount() {
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
	public boolean hasKeyword() {
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
	public String getKeyword() {
		return line.trim().split(" ")[0];
	}
	
	/**
	 * Returns the name of the function being defined.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd><code>getKeyword(line)</code> returns "def".
	 * </dl>
	 * 
	 * @param line
	 * 	The line to retrieve the function name from
	 * 
	 * @return
	 * 	The function name
	 */
	public String getFunctionName() {
		return line.trim().split(" ")[1].split("\\(")[0];
	}
	
	/**
	 * Gets the complexity of the for loop, ignoring what statements are actually inside.
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
	 * 	The complexity of the for loop
	 */
	public Complexity getForLoopComplexity() {
		if (line.trim().split(" ")[3].equals("N:")) {
			return new Complexity(1, 0);
		} else if (line.trim().split(" ")[3].equals("log_N:")) {
			return new Complexity(0, 1);
		} else {
			return new Complexity(0,0);
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
	public String getWhileLoopVariable() {
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
	public boolean updatesLoopVariable(String loopVariable) {
		return line.trim().equals(loopVariable + " -= 1") ||
			   line.trim().equals(loopVariable + " /= 2");
	}
	
	/**
	 * Returns an instance of CodeLine
	 */
	public CodeLine() {
		this.line = "";
	}
	
	/**
	 * Returns an instance of CodeLine
	 * 
	 * @param line
	 * 	The line to initialize to
	 */
	public CodeLine(String line) {
		this.line = line;
	}
}

package complexityparser;

import java.io.File;
import java.util.Scanner;

/**
* The <code>PythonTracer</code> allows the user to give a Python file as input, and computes its Big-Oh complexity.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class PythonTracer {
	private static BlockStack stack = new BlockStack();
	private static final int SPACE_COUNT = 4; // Number of spaces per indent
	private static String name = null;
	private static Scanner in;
	
	/**
	 * Opens the specified file to read for the Scanner.
	 * 
	 * @param file
	 * 	The file to open
	 */
	private static void openFile(String file) {
		try {
			in = new Scanner(new File(file));
		} catch (Exception e) {
			System.out.println("Could not open file.");
		}
	}
	
	/**
	 * Handles code for leaving a block in the parser.
	 * 
	 * @param indents
	 * 	Number of indents in current line.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>Closes the file if indents is 0, or pops off the <code>BlockStack</code> otherwise.
	 * </dl>
	 * 
	 */
	private static void leaveBlock(int indents) {
		if (indents == 0) {
			in.close();
			CodeBlock global = stack.pop();
			printResult(global.getTotalComplexity());
		} else {
			stack.pop();
		}
	}
	
	/**
	 * Handles keywords in the line for the parser.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * Determines the keyword in the line and pushes the new appropriate <code>CodeBlock</code>
	 * to the stack. 
	 * </dd>
	 * </dl>
	 */
	private static void handleKeyword(String line) {
		String keyword = Line.getKeyword(line);
		if (keyword.equals("for")) {
			Complexity complexity = Line.getForLoopComplexity(line);
			stack.push(new CodeBlock(stack.peek(), complexity));
		} else if (keyword.equals("while")) {
			String loopVariable = Line.getWhileLoopVariable(line);
			CodeBlock newCodeBlock = new CodeBlock(stack.peek(), new Complexity());
			newCodeBlock.setLoopVariable(loopVariable);
			stack.push(newCodeBlock);
		} else {
			stack.push(new CodeBlock(stack.peek(), new Complexity()));
		}
	}
	
	/**
	 * Prints the complexity of the function after parsing is done.
	 * 
	 * @param complexity
	 * 	The complexity of the function
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * A message describing the complexity of the chosen file is shown to the user.
	 * </dd>
	 * </dl>
	 */
	private static void printResult(Complexity complexity) {
		System.out.println("\nOverall complexity of " + name + ": " + complexity.toString());
		System.exit(0);
	}
	
	/**
	 * Opens the indicated file and traces through the code of the Python function contained within the file, returning the 
	 * Big-Oh order of complexity of the function. During operation, the stack trace should be printed to the console as code 
	 * blocks are pushed to/popped from the stack.
	 * 
	 * <dl>
	 * <dt>Preconditions</dt>
	 * <dd><code>filename</code> is not null and the file it names contains a single Python function with valid syntax.
	 * </dl>
	 * 
	 * @param filename
	 * 	The file to open
	 * 
	 * @returns 
	 * 	A Complexity object representing the total order of complexity of the Python code contained within the file.
	 */
	public static void traceFile(String filename) {
		
	}
	
	/**
	 * Prompts the user for the name of a file containing a single Python function, 
	 * determines its order of complexity, and prints the result to the console.
	 */
	public static void main (String[] args) {
		String test = "def selectExpenses(map, filter)";
		System.out.println(Line.getFunctionName(test));
	}
}

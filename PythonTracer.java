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
	private static final int SPACE_COUNT = 4; // Number of spaces per indent
	private static Scanner in;
	
	/**
	 * Opens the specified file to read for the Scanner.
	 * 
	 * @param file
	 * 	The file to open
	 */
	public static void openFile(String file) {
		try {
			in = new Scanner(new File(file));
		} catch (Exception e) {
			System.out.println("Could not open file.");
		}
	}
	
	public static void handleKeyword(String line) {
		
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
		
		
	}
}

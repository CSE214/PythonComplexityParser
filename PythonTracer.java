package complexityparser;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * FIX INDENTING ISSUE
 * 
* The <code>PythonTracer</code> allows the user to give a Python file as input, and computes its Big-Oh complexity.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class PythonTracer {
	private static final int SPACE_COUNT = 4; // Number of spaces per indent
	private static BlockStack stack = new BlockStack(); //Specialized Stack of CodeBlocks
	private static String name = ""; // Name of function being defined
	private static CodeLine currentLine = new CodeLine(); // Current line of the tracer
	private static Scanner fileInput = new Scanner(System.in); // Take file input
	private static Scanner fileReader; // Reads the file
	
	/**
	 * Opens the specified file to read for the Scanner.
	 * 
	 * @param file
	 * 	The file to open
	 */
	private static void openFile() {
		System.out.print("Please enter the python file to scan (or 'quit' to quit): ");
		String fileName = fileInput.nextLine();
		if (fileName.equals("quit")) {
			System.out.println("Program terminating successfully...");
			System.exit(0);
		}
		try {
			fileReader = new Scanner(new File(fileName));
			try {
				traceFile();
			} catch (Exception e) {
				System.out.println("\nCould not properly parse file. Make sure the syntax is valid.\n");
				openFile();
			}
		} catch (Exception e) {
			System.out.println("Cannot read filepath. Please try again.");
			openFile();
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
	private static void handleKeyword() {
		String keyword = currentLine.getKeyword();
		if (keyword.equals("for")) {
			Complexity complexity = currentLine.getForLoopComplexity();
			stack.push(new CodeBlock(stack.peek(), complexity));
			enteringBlockMessage(keyword);
		} else if (keyword.equals("while")) {
			String loopVariable = currentLine.getWhileLoopVariable();
			CodeBlock newCodeBlock = new CodeBlock(stack.peek(), new Complexity());
			newCodeBlock.setLoopVariable(loopVariable);
			stack.push(newCodeBlock);
			enteringBlockMessage(keyword);
		} else {
			stack.push(new CodeBlock(stack.peek(), new Complexity()));
			enteringBlockMessage(keyword);
		}
	}
	
	/**
	 * Handles loop variables that update the while block.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * Updates the complexity of the while block.
	 * </dd>
	 * </dl>
	 */
	private static void handleLoopVariable() {
		System.out.println("\nFound update statement, updating block " + stack.peek().getName() + ":");
		if (currentLine.getLine().trim().split(" ")[1].equals("-=")) {
			stack.peek().setBlockComplexity(new Complexity(1,0));
		} else {
			stack.peek().setBlockComplexity(new Complexity(0,1));
		}
		System.out.println(stack.peek().toString());
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
		if (stack.getSize() == 0 && !fileReader.hasNextLine()) {
			CodeBlock global = stack.pop();
			printResult(global.getTotalComplexity());
		} else {
			CodeBlock oldBlock = stack.pop();
			leavingBlockMessage(oldBlock);
		}
	}
	
	/**
	 * Sends the currentLine to the first line with a def keyword.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * The currentLine should have a def keyword.
	 * </dd>
	 * </dl>
	 */
	private static void toDefKeyword() {
		do {
			currentLine.setLine(fileReader.nextLine());
		} while (currentLine.isEmpty() || currentLine.isComment());
	}
	
	/**
	 * Prints a feedback message to the user whenever the tracer
	 * enters a new block.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * A message displaying the block being entered is displayed to the user.
	 * </dd>
	 * </dl>
	 */
	private static void enteringBlockMessage(String keyword) {
		System.out.println("\nEntering block " + stack.peek().getName() + " '" + keyword + "':");
		System.out.println(stack.peek().toString());
	}
	
	/**
	 * Prints a feedback message to the user whenever the tracer
	 * enters a new block.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>
	 * A message displaying the block being entered is displayed to the user.
	 * </dd>
	 * </dl>
	 */
	private static void leavingBlockMessage(CodeBlock oldBlock) {
		if (oldBlock.getTotalComplexity().isLessThan(stack.peek().getHighestSubComplexity())) {
			System.out.println("\nLeaving block " + oldBlock.getName() + ", nothing to update.");
		} else {
			System.out.println("\nLeaving block " + oldBlock.getName() + ", updating block " + stack.peek().getName() + ":");
		}
		System.out.println(stack.peek().toString());
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
		System.out.println("\nOverall complexity of " + name + ": " + complexity.toString() + "\n");
		openFile();
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
	public static void traceFile() {
		stack.push(new CodeBlock()); //Push globalBlock
		toDefKeyword();
		name = currentLine.getFunctionName();
		enteringBlockMessage("def");
		while(fileReader.hasNextLine()) {
			currentLine.setLine(fileReader.nextLine());
			if (!currentLine.isEmpty() && !currentLine.isComment()) {
				while (currentLine.getIndentCount() < stack.getSize()) {
					leaveBlock(currentLine.getIndentCount());
				}
				if(currentLine.hasKeyword()) {
					handleKeyword();
				} else if (stack.peek().getLoopVariable() != null && currentLine.updatesLoopVariable(stack.peek().getLoopVariable())) {
					handleLoopVariable();
				}
			}
		}
		while(stack.getSize() > 0) {
			stack.pop();
		}
		printResult(stack.pop().getTotalComplexity());
	}
	
	/**
	 * Starts the program.
	 */
	public static void main (String[] args) {
		openFile();	
	}
}

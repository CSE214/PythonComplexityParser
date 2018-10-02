package complexityparser;

import java.util.Stack;

/**
* The <code>BlockStack</code> class is a stack with specialized behaviour to represent code.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class BlockStack {
	private Stack<CodeBlock> stack = new Stack<CodeBlock>(); // Stack of codeBlocks
	private int size = -1; // Set to -1 so globalBlock isn't counted in size
	
	/**
	 * Identical functionality to Stack.push()
	 */
	public void push(CodeBlock codeBlock) {
		stack.push(codeBlock);
	}
	
	/**
	 * Identical functionality to Stack.pop()
	 * 
	 * @return	
	 * 	The popped <code>CodeBlock</code>
	 */
	public CodeBlock pop() {
		return stack.pop();
	}
	
	/**
	 * Identical functionality to Stack.peek()
	 * 
	 * @return	
	 * 	The topmost <code>CodeBlock</code> of the stack.
	 */
	public CodeBlock peek() {
		return stack.peek();
	}
}

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
	private Stack<CodeBlock> stack; // Stack of codeBlocks
	private int size; // Size of stack
	
	/**
	 * @return
	 * 	The size of the stack
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Identical functionality to Stack.push()
	 */
	public void push(CodeBlock codeBlock) {
		size++;
		stack.push(codeBlock);
	}
	
	/**
	 * Pops the topmost code block off the stack, and updates the highest sub-complexity
	 * of the new topmost block if it exists.
	 * 
	 * <dl>
	 * <dt>Postconditions:</dt>
	 * <dd>The topmost block has been popped, and, if it exists, the new topmost block has its highest 
	 * sub-complexity updated.</dd>
	 * </dl>
	 * 
	 * @return	
	 * 	The popped <code>CodeBlock</code>
	 */
	public CodeBlock pop() {
		size--;
		CodeBlock oldTop = stack.pop();
		Complexity oldTopComplexity = oldTop.getTotalComplexity(); 
		
		if (size > -1) {
			Complexity highestSubComplexity = Complexity.addComplexities(stack.peek().getHighestSubComplexity(), oldTopComplexity);
			stack.peek().setHighestSubComplexity(highestSubComplexity);
		}
		return oldTop;
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
	
	public BlockStack() {
		this.stack = new Stack<CodeBlock>();
		this.size = -1; //Set to -1 so globalBlock isn't counted in size
	}
}

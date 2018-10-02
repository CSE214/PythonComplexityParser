package complexityparser;

/**
* The <code>CodeBlock</code> class describes a nested block of code.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class CodeBlock {
	@SuppressWarnings("unused")
	private static enum BLOCK_TYPES { // Types of blocks to parse
		DEF,
		FOR,
		WHILE,
		IF,
		ELSE,
		ELIF
	};
	private Complexity blockComplexity; // Order of block ignoring statements inside
	private Complexity highestSubComplexity; // Highest order of CodeBlock nested in this instance
	private String name; // Used to organize block structure of the code
	private String loopVariable; // Used to keep track of loop variable in WHILE block
	private int subBlockCount;
	/**
	 * @return 
	 *	The blockComplexity of this instance
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	/**
	 * @param blockComplexity 
	 * 	The new blockComplexity to set
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	/**
	 * @return 
	 *	The highestSubComplexity of this instance
	 */
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	/**
	 * @param highestSubComplexity 
	 * 	The new highestSubComplexity to set
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	/**
	 * @return 
	 *	The name of this instance
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 
	 * 	The new name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 
	 *	The loopVariable of this instance
	 */
	public String getLoopVariable() {
		return loopVariable;
	}
	/**
	 * @param loopVariable 
	 * 	The new loopVariable to set
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	/**
	 * @return 
	 *	The subBlockCount of this instance
	 */
	public int getSubBlockCount() {
		return subBlockCount;
	}
	/**
	 * Increment the subBlockCount by 1.
	 */
	public void incrementSubBlockCount() {
		this.subBlockCount++;
	}
	/**
	 * Used to initialize the global codeBlock. 
	 */
	public CodeBlock() {
		this.blockComplexity = new Complexity();
		this.highestSubComplexity = new Complexity();
		this.name = "";
		this.loopVariable = null;
		this.subBlockCount = 0;
	}
	
	/**
	 * Creates a new CodeBlock nested inside of topCodeBlock.
	 */
	public CodeBlock(CodeBlock topCodeBlock) {
		this.blockComplexity = new Complexity();
		this.highestSubComplexity = new Complexity();
		this.loopVariable = null;
		this.subBlockCount = 0;
		
		topCodeBlock.incrementSubBlockCount();
		if (topCodeBlock.getName().equals("")) {
			this.name = Integer.toString(topCodeBlock.getSubBlockCount());
		}
		else {
			this.name = topCodeBlock.getName() + "." + 
					    Integer.toString(topCodeBlock.getSubBlockCount());
		}
	}
	
}

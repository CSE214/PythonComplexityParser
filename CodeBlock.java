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
	private enum BLOCK_TYPES { // Types of blocks to parse
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
	 * Returns an instance of CodeBlock
	 * 
	 * @param name
	 * 	The name of the CodeBlock
	 */
	public CodeBlock(String name) {
		super();
		this.blockComplexity = new Complexity();
		this.highestSubComplexity = new Complexity();
		this.name = name;
		this.loopVariable = null;
	}
	
}

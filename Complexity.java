package complexityparser;

/**
* The <code>Complexity</code> class is used to represent the Big-Oh complexity of some 
* piece of code. <code>Complexity</code> only comes equipped to represent powers of n
* and log(n).
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class Complexity {
	private int nPower; // The power of the 'n' term
	private int logPower; // The power of the 'log(n)' term
	/**
	 * @return 
	 *	The nPower of this instance
	 */
	public int getNPower() {
		return nPower;
	}
	/**
	 * @param nPower 
	 * 	The new nPower to set
	 */
	public void setNPower(int nPower) {
		this.nPower = nPower;
	}
	/**
	 * @return 
	 *	The logPower of this instance
	 */
	public int getLogPower() {
		return logPower;
	}
	/**
	 * @param logPower 
	 * 	The new logPower to set
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	/**
	 * Returns an instance of Complexity with powers initialized to 0.
	 */
	public Complexity() {
		this.nPower = 0;
		this.logPower = 0;
	}
	
	/**
	 * Returns an instance of Complexity with the defined initializations.
	 * 
	 * @param nPower
	 * 	The power of n to initialize.
	 * @param logPower
	 * 	The power of log(n) to initialize.
	 */
	public Complexity(int nPower, int logPower) {
		this.nPower = nPower;
		this.logPower = logPower;
	}
	
	/**
	 * Returns a string representing the nPower component of the complexity.
	 * 
	 * @return
	 * 	a string representing the nPower component of the complexity.
	 */
	private String getNPowerString() {
		if (nPower == 0) {
			return "";
		} else if (nPower == 1) {
			return "n";
		} else {
			return "n^" + nPower;
		}
	}
	
	/**
	 * Returns a string representing the logPower component of the complexity.
	 * 
	 * @return
	 * 	a string representing the logPower component of the complexity.
	 */
	private String getLogPowerString() {
		if (logPower == 0) {
			return "";
		} else if (logPower == 1) {
			return "log(n)";
		} else {
			return "log(n)^" + logPower;
		}
	}
	@Override
	public String toString() {
		String nPowerString = getNPowerString();
		String logPowerString = getLogPowerString();
		
		if (nPowerString.equals("") && logPowerString.equals("")) {
			return "O(1)";
		} else if (logPowerString.equals("")) {
			return "O(" + nPowerString + ")";
		} else if (nPowerString.equals("")) {
			return "O(" + logPowerString + ")";
		} else {
			return "O(" + nPowerString + " * " + logPowerString + ")";
		}
	}
	
	/**
	 * Checks if <code>complexity</code> is strictly bigger than this instance of <code>Complexity</code>
	 * 
	 * @param complexity
	 * 	The complexity to compare with
	 * 
	 * @return
	 * 	True if <code>complexity</code> is strictly greater, false otherwise. 
	 */
	public boolean isLessThan(Complexity complexity) {
		if (nPower < complexity.getNPower()) {
			return true;
		} else {
			return logPower < complexity.getLogPower(); 
		}
		
	}
	
	/**
	 * Returns the sum of two complexities.
	 * 
	 * @param a
	 * 	The first complexity to add
	 * @param b
	 * 	The second complexity to add
	 * 
	 * @return
	 * 	The sum of Complexities <code>a</code> and <code>b</code>
	 */
	public static Complexity addComplexities(Complexity a, Complexity b) {
		return a.isLessThan(b) ? b : a;
	}
	
	/**
	 * Returns the product of two complexities.
	 * 
	 * @param a
	 * 	The first complexity to multiply
	 * @param b
	 * 	The second complexity to multiply
	 * 
	 * @return
	 * 	The product of Complexities <code>a</code> and <code>b</code>
	 */
	public static Complexity multiplyComplexities(Complexity a, Complexity b) {
		return new Complexity(a.getNPower() + b.getNPower(), a.getLogPower() + b.getLogPower());
	}
}

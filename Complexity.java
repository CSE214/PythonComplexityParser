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
	public int getnPower() {
		return nPower;
	}
	/**
	 * @param nPower 
	 * 	The new nPower to set
	 */
	public void setnPower(int nPower) {
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
	
	@Override
	public String toString() {
		if ( nPower == 0 && logPower == 0 ) {
			return "O(1)";
		} else if ( logPower == 0) {
			return "O(n^" + nPower + ")";
		} else if ( nPower == 0) {
			return "O(log(n)^" + logPower + ")";
		} else {
			return "O(n^" + nPower + " * log(n)^" + logPower + ")";
		}
	}
}

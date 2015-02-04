package neuralnetwork.util;
import java.lang.Math;

/**
 * A class that contains activation functions.
 *
 */
public class Activation {
	 /**
	 * Calculates a value under the step function.
	 * 
	 * @param val The value to apply the step function to.
	 * 
	 * @return
	 * Value after the step function is applied.
	 */
	public static int step(double val) {
		if (val >= 0) {
			return 1;
		}
		
		return 0;
		
	}
	
	/**
	 * Calculates a value under the sigmoid function.
	 * 
	 * @param val The value to apply the sigmoid function.
	 * 
	 * @return
	 * Value after the step function is applied.
	 * 
	 * 
	 */
	public static double sigmoid(double val) {
		return (1/(1+Math.exp(-val)));
	}
	
}

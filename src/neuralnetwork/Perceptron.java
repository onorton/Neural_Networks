package neuralnetwork;

import java.util.Random;
import java.lang.Math;
/**
 * 
 * Class for perception neuron
 *
 */
public class Perceptron implements Neuron {

	private double[] weights;
	private double threshold;
	private int inputs;
	private int combinations;
	private int[][] data;
	private Random r = new Random();
	
	 
	 // NB: Overload constructor when reading from hidden layer
	 /** 
	 * Constructor for Perceptron 
	 * 
	 * @param inputs The number of inputs to the perceptron.
	 * 
	 * @param threshold The threshold for the perceptron.
	 * 
	 * @param minV Minimum value for weights.
	 * 
	 * @param maxV Maximum value for weights.
	 * 
	 * @param data The data that is to be trained on.
	 */
	public Perceptron(int inputs, double threshold, 
			          double minV, double maxV, int[][] data) {

		this.threshold = threshold;
		this.inputs = inputs;
		this.combinations = (int)Math.pow((double)2, (double)inputs);
		this.data = data;
		
		weights = new double[inputs];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = minV +  (maxV-minV)* r.nextDouble();
		}
		
	}
	
	/**
	 * Prints the weights for the perception.
	 * 
	 */
	@Override
	public void getWeights() {
		for (int i = 0; i < weights.length; i++) {
			System.out.println("w" + i + ": " + weights[i]);
		}
		
	}

	/**
	 * 
	 * Calculates the output for a particular set of inputs.
	 * 
	 * @param input
	 * The set of inputs.
	 * 
	 * @return
	 * The value of the output
	 */
	@Override
	public int getOutput(int[] input) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			 sum += input[i]*weights[i];
		}
		
		
		return (step(sum - threshold));
	}
	
	
	/**
	 * Calculates a value under the step function.
	 * 
	 * @param val The value to apply step function to.
	 * 
	 * @return
	 * Value after the step function is applied.
	 */
	private int step(double val) {
		if (val >= 0) {
			return 1;
		}
		return 0;
		
	}

	
	/**
	 * Trains the particular neuron.
	 * 
	 * @param learningR The learning rate of the network.
	 */
	public void train(double learningR) {
		boolean error = true;
		while (error) {
			error = false;
			
			for (int i = 0; i < combinations; i++) {
				int e = data[i][2] - getOutput(data[i]);
				System.out.println(e);
				if (e != 0) {
					error = true;
				}
				
				for (int j = 0; j < inputs; j++) {
					weights[j] += delta(i, j, e, learningR);
				}
				
				
			}
		}
	}
	
	/**
	 * Calculates the change of the current weight. 
	 * 
	 * @param i
	 * The index for the particular input-output combination.
	 *  
	 * @param j
	 * The index for the particular weight.
	 * 
	 * @param e
	 * The error of the output.
	 * 
	 * @param learningR
	 * The learning rate of the network.
	 * 
	 * @return
	 * The value of the change to be applied to the weight.
	 */
	private double delta(int i, int j, int e, double learningR) {
		return learningR * data[i][j] * e;
	}
	
	
	
	
	

}

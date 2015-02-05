package neuralnetwork;

import java.util.Random;
import java.lang.Math;

import neuralnetwork.util.Activation;
/**
 * 
 * Class for perception neuron
 *
 */
public class Perceptron implements Neuron {

	protected double[] weights;
	protected double threshold;
	protected int inputs;
	protected int combinations;
	protected Random r = new Random();
	protected double inputData[][];
	protected double data[][];
	protected double errorGradient;
	
	 
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
	public Perceptron(int inputs, double minV, 
			           double maxV, double data[][]) {
		
		
        this.data = data;
		this.threshold = minV/inputs +  (maxV-minV)/inputs* r.nextDouble();
		this.inputs = inputs;
		this.combinations = (int)Math.pow((double)2, (double)inputs);
        inputData = new double[4][inputs];
		weights = new double[inputs];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = minV/inputs +  (maxV-minV)/inputs* r.nextDouble();
		}
		
	}
	
	/**
	 * Prints the weights for the perception.
	 * 
	 */
	@Override
	public void printWeights() {
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
	
	public double getOutput(double[] input) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			 sum += input[i]*weights[i];
		}
		
		
		return (Activation.sigmoid(sum - threshold));
	}
	

	
	public void setInputs(int i, int index, double b) {
		inputData[i][index] = b;
	}
	/**
	 * Trains the particular neuron.
	 * 
	 * @param learningR The learning rate of the network.
	 */
	
	public double train(double learningR) {
	    double sumSqsE = 0;

		for (int i = 0; i < combinations; i++) {
			double output = getOutput(inputData[i]);
			double e = data[i][2] - output;
			sumSqsE += e*e;
			errorGradient = output *(1-output) * e;
				 
				
			for (int j = 0; j < inputs; j++) {
				weights[j] += delta(learningR, inputData[i][j], errorGradient);
			}
			
		    threshold += delta(learningR, -1, errorGradient);
		
				
				
		}

		return sumSqsE;
	}

	public double[] getWeights() {
		return weights;
	}
	public double getErrorGradient() {
		return errorGradient;
	}
	
	/**
	 * Calculates the change of the current weight. 
	 * 
	 *
	 * @param learningR
	 * The learning rate of the network.
	 * 
	 * @param output
	 * The output of the previous hidden layer neurons
	 * 
	 * @param errorG
	 * The error gradient of the output and error.e
	 * 
	 * @return
	 * The value of the change to be applied to the weight.
	 */
	protected double delta(double learningR, double output, double errorG) {
		return learningR * output * errorG;
	}
	
	
	
	
	

}

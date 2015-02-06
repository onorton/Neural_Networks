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
	protected double intermediateData[][];
	protected double data[][];
	protected double errorGradient;
	
	 
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
        this.intermediateData = new double[4][inputs+1];
		this.weights = new double[inputs];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = minV/inputs + (maxV-minV)/inputs* r.nextDouble();
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
	@Override
	public double getOutput(double[] input) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			 sum += input[i]*weights[i];
		}
		
		
		return (Activation.sigmoid(sum - threshold));
	}
	

	/**
	 * Used for setting the inputs from the hidden layer.
	 * 
	 * @param i
	 * The particular input-output combination.
	 * 
	 * @param index
	 * The index of the particular input/hidden layer neuron.
	 * 
	 * @param value
	 * The value to be assigned to.
	 */
	public void setInputs(int i, int index, double value) {
		intermediateData[i][index] = value;
	}
	
	
	/**
	 * Trains the particular neuron.
	 * 
	 * @param learningR 
	 * The learning rate of the network.
	 * 
	 * @param i
	 * The index of the particular input-output combination.
	 * 
	 * @return
	 * The sum of squared error for this particular input-output combination.
	 */
	@Override
	public double train(double learningR, int i) {
	    double sumSqsE = 0;
   
	
        double output = intermediateData[i][2];
        
		double e = data[i][2] - output;
		sumSqsE += e*e;
		
		//Calculate error gradient
		errorGradient = output * (1-output) * e;
			
				
		//Adjust weights
		for (int j = 0; j < inputs; j++) {
		
			weights[j] += delta(learningR, intermediateData[i][j], errorGradient);
			
		}
			
		threshold += delta(learningR, -1, errorGradient);
			
		

		return sumSqsE;
	}

	
	/**
	 * Allows the passing of the weights for this neuron to the hidden layer.
	 * 
	 * @return
	 * The weights for this neuron.
	 */
	public double[] getWeights() {
		return weights;
	}
	
	/**
	 * Allows the passing of the error gradient to the hidden layer.
	 * 
	 * @return 
	 * The value of the error gradient for the particular epoch.
	 */
	public double getErrorGradient() {
		return errorGradient;
	}
	
	/**
	 * 
	 * @param i
	 * The particular combination of inputs and outputs in the training set.
	 * 
	 * @return 
	 * The input data from combination i.
	 */
	public double[] getIntermediateData(int i){
		return intermediateData[i];
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

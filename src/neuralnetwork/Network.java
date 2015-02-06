package neuralnetwork;

import java.lang.Math;
/**
 * 
 * The class for the network.
 *
 */
public class Network implements Neuron {

	private final double minV = -2.4;
	private final double maxV = 2.4;
	private HiddenLayer[] neurons;
	private Perceptron outputL;
	private double[][] data;
	private int inputs;

	
	
	/**
	 * Constructor for Network. Creates the neurons in the network (one at the moment).
	 * 
	 * @param inputs
	 * The number of inputs for a particular combination 
	 * 
	 * @param data
	 * The input-output combinations to train.
	 */
	public Network(int inputs, double [][] data) {
		this.inputs = inputs;
		this.data = data;
		outputL = new Perceptron(inputs, minV, maxV, data);
		neurons = new HiddenLayer[inputs];
		for (int i = 0; i < neurons.length; i++)
			neurons[i] = new HiddenLayer (inputs, minV, maxV, data, outputL);
	}
	
	/**
	 * 
	 * Prints all of the weights in the network.
	 */
	@Override
	public void printWeights() {
		for (Neuron n : neurons) {
			n.printWeights();
			System.out.println();
			
		}
		
	}


	/**
	 * Calculates the final output based on a set of inputs.
	 * 
	 * @param input
	 * The set of inputs to be entered into the network.
	 * 
	 * @param i
	 * The particular set of training data.
	 */
	public double getOutput(double[] input, int i) {
		for (int index = 0; index < neurons.length; index++) {
			 outputL.setInputs(i, index, neurons[index].getOutput(input));	
		}
		double output = outputL.getOutput(outputL.getIntermediateData(i));
		outputL.setInputs(i, 2, output);
		return output;
	}
	
	/**
	 * This outputs data supplied by user after training.
	 */
	public double getOutput(double[] input) {
		double inputH[] = new double[input.length];
		for (int index = 0; index < neurons.length; index++) {
			 inputH[index] = neurons[index].getOutput(input);
		}
		return outputL.getOutput(inputH);
	}
	
	

	/**
	 * Trains the network.
	 * 
	 * @param learningR
	 * The learning rate of the network.
	 * 
	 * @return
	 * The sum of squared errors.
	 */
	@Override
	public double train(double learningR, int k) {
		double sumSqE = 1;

	    while(sumSqE >= 0.001) {
	    	sumSqE = 0;
			for (int i = 0; i < (int)Math.pow(2, inputs); i++) {
				//Calculate the output for the initial values
			     getOutput(data[i], i);
				
			
			    //Adjust weights for output layer
		        sumSqE += outputL.train(learningR, i);
		         
		        //Adjust weights for hidden layer
		        for (int j = 0; j < neurons.length; j++) {
		    	    neurons[j].train(learningR, j, i);
		        }
		        		        
			}

		}
	    return sumSqE;
	}
	
	
}

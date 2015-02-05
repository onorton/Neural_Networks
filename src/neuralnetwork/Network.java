package neuralnetwork;

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
	 */
	
	public double getOutput(double[] input) {
		double inputH[] = new double[input.length];
		for (int i = 0; i < neurons.length; i++) {
			inputH[i] = neurons[i].getOutput(input);
		    System.out.println(inputH[i]);	
		}
		return outputL.getOutput(inputH);
	}
	

	/**(
	 * Trains the network.
	 * 
	 * @param learningR
	 * The learning rate of the network.
	 */
	@Override
	public double train(double learningR) {
		double sumSqE = 1;
		while(sumSqE >= 0.001) {
			for (int i = 0; i < neurons.length; i++) {
				sumSqE = neurons[i].train(learningR, i);
			}
			
			System.out.println(sumSqE);
		
		}
	    return sumSqE;
	}
	
	
}

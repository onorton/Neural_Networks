package neuralnetwork;

/**
 * 
 * The class for the network.
 *
 */
public class Network implements Neuron {

	private final double minV = -0.5;
	private final double maxV = 0.5;
	private Neuron[] neurons;
	
	/**
	 * Constructor for Network. Creates the neurons in the network (one at the moment).
	 * 
	 * @param inputs
	 * The number of inputs for a particular combination 
	 * 
	 * @param data
	 * The input-output combinations to train.
	 */
	public Network(int inputs, int [][] data) {
		
		neurons = new Neuron[1];
		for (int i = 0; i < neurons.length; i++)
			neurons[i] = new Perceptron(inputs, 0.2, minV, maxV, data);
	}
	
	/**
	 * 
	 * Prints all of the weights in the network.
	 */
	@Override
	public void getWeights() {
		for (Neuron n : neurons) {
			n.getWeights();
			System.out.println();
			
		}
		
	}


	/**
	 * Calculates the final output based on a set of inputs.
	 * 
	 * @param input
	 * The set of inputs to be entered into the network.
	 */
	@Override
	public int getOutput(int[] input) {
		return neurons[0].getOutput(input);
	}
	

	/**
	 * Trains the network.
	 * 
	 * @param learningR
	 * The learning rate of the network.
	 */
	@Override
	public void train(double learningR) {
		neurons[0].train(learningR);
		getWeights();
	}

	
	
}

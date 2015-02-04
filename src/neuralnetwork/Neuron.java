package neuralnetwork;

/**
 * The interface for neurons and the network
 *
 */
public interface Neuron {
	
	void getWeights();
	
	void train(double learningR);

	int getOutput(int[] input);
	
	
	

}

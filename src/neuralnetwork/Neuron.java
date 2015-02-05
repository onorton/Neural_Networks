package neuralnetwork;

/**
 * The interface for neurons and the network
 *
 */
public interface Neuron {
	
	void printWeights();
	
	double train(double learningR);
	
	double getOutput(double[] data);
	

}

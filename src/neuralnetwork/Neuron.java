package neuralnetwork;

public interface Neuron {
	
	void getWeights();
	
	void train(double learningR);

	int getOutput(int[] input);
	
	
	

}

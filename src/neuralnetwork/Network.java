package neuralnetwork;

public class Network implements Neuron {

	private final double minV = -0.5;
	private final double maxV = 0.5;
	private Neuron[] neurons;
	
	public Network(int inputs, int [][] data) {
		
		neurons = new Neuron[1];
		for (int i = 0; i < neurons.length; i++)
			neurons[i] = new Perceptron(inputs, 0.2, minV, maxV, data);
	}
	
	@Override
	public void getWeights() {
		for (Neuron n : neurons) {
			n.getWeights();
			System.out.println();
			
		}
		
	}


	public int getOutput(int[] input) {
		return neurons[0].getOutput(input);
	}
	

	@Override
	public void train(double learningR) {
		neurons[0].train(learningR);
		getWeights();
	}

	
	
}

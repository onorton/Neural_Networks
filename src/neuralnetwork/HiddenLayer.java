package neuralnetwork;

/**
 * 
 * Class for hidden layer neurons.
 * Similar to Perceptron but has multiple outputs.
 *
 */
public class HiddenLayer extends Perceptron {

	public HiddenLayer(int inputs, double threshold, double minV, double maxV,
			int[][] data) {
		super(inputs, threshold, minV, maxV, data);
		Neuron[] outputL = new Neuron[1];
		for (Neuron n : outputL) {
			n = new Perceptron(inputs, threshold, minV, maxV, data);
		}
	}

}

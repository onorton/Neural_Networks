package neuralnetwork;

import neuralnetwork.util.Activation;


/**
 * 
 * Class for hidden layer neurons.
 * Similar to Perceptron
 *
 */
public class HiddenLayer extends Perceptron {
	
    private Perceptron outputL;
    
	public HiddenLayer(int inputs, double minV, double maxV,
			int[][] data, Perceptron outputL ) {
		super(inputs, minV, maxV);
		this.outputL = outputL;
		}
	
	public double getOutput(int[] input) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			 sum += input[i]*weights[i];
		}
		
		
		return (Activation.step(sum - threshold));
	}
	

	public double train(double learningR, int[][] data, int index) {
		
		    double sumSqE = 0;
	    	for (int i = 0; i < combinations; i++) {
				double output = getOutput(data[i]);
				outputL.setInputs(i, index, output);
				sumSqE = outputL.train(learningR, data);
	    	}
	    	
	    	for (int i = 0; i < combinations; i++) {
	    		double output = getOutput(data[i]);
				errorGradient = output *(1-output) * outputL.getWeights()[index] ;
					 
				
				for (int j = 0; j < inputs; j++) {
					weights[j] += delta(learningR, data[i][j], errorGradient);
				}
				
	    	}
					
					
		
	    return sumSqE;
	}
	
	private double delta(double learningR, int x, double errorG) {
		return learningR * x * errorG;
	}
}


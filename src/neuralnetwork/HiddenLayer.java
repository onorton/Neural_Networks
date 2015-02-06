package neuralnetwork;



/**
 * 
 * Class for hidden layer neurons.
 * Similar to Perceptron
 *
 */
public class HiddenLayer extends Perceptron {
	
    private Perceptron outputL;
    
	public HiddenLayer(int inputs, double minV, double maxV,
			double[][] data, Perceptron outputL ) {
		super(inputs, minV, maxV, data);
		this.outputL = outputL;
		}
	


	/**
	 * Consider splitting to calculate outputs then training separately.
	 * Adjust weights for particular neuron.
	 * 
	 * 
	 * @param learningR
	 * The learning rate for training.
	 * 
	 * @param index
	 * The index of the particular neuron. (Used for passing output values to output layer).
	 *  
	 */
	public void train(double learningR, int index, int i){
		    
		double output = outputL.getIntermediateData(i)[2];
		errorGradient = output *(1-output) * outputL.getErrorGradient() * outputL.getWeights()[index];
				 
				
		for (int j = 0; j < inputs; j++) {
			weights[j] += delta(learningR, data[i][j], errorGradient);
		}
			
		threshold += delta(learningR, -1, errorGradient);
			
				
	}
		
	

}


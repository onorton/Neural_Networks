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
	 * 
	 * 
	 * @param learningR
	 * @param index
	 * @return
	 */
	public double train(double learningR, int index) {
		    /**
		     * outputL.train(learningR);
		     */
		    double sumSqE = 0;
	    	for (int i = 0; i < combinations; i++) {
				double output = getOutput(data[i]);
				outputL.setInputs(i, index, output);
				sumSqE = outputL.train(learningR);
	    	}
	    	
	    	for (int i = 0; i < combinations; i++) {
	    		double output = getOutput(data[i]);
				errorGradient = output *(1-output) * outputL.getWeights()[index] ;
					 
				
				for (int j = 0; j < inputs; j++) {
					weights[j] += delta(learningR, data[i][j], errorGradient);
				}
				
				// threshold += delta(learningR, -1, errorGradient);

				
	    	}
					
					
		
	    return sumSqE;
	}
	

}


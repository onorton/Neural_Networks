package neuralnetwork;

import java.util.Random;
import java.lang.Math;

public class Perceptron implements Neuron {

	private double[] weights;
	private double threshold;
	private int inputs;
	private int combinations;
	private int[][] data;
	private Random r = new Random();
	
	
	public Perceptron(int inputs, double threshold, 
			          double minV, double maxV, int[][] data) {

		this.threshold = threshold;
		this.inputs = inputs;
		this.combinations = (int)Math.pow((double)2, (double)inputs);
		this.data = data;
		
		weights = new double[inputs];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = minV +  (maxV-minV)* r.nextDouble();
		}
		
	}
	
	@Override
	public void getWeights() {
		for (int i = 0; i < weights.length; i++) {
			System.out.println("w" + i + ": " + weights[i]);
		}
		
	}

	
	public int getOutput(int[] input) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			 sum += input[i]*weights[i];
		}
		
		
		return (step(sum - threshold));
	}
	
	private int step(double val) {
		if (val >= 0) {
			return 1;
		}
		return 0;
		
	}

	
	public void train(double learningR) {
		boolean error = true;
		while (error) {
			error = false;
			
			for (int i = 0; i < combinations; i++) {
				int e = data[i][2] - getOutput(data[i]);
				System.out.println(e);
				if (e != 0) {
					error = true;
				}
				
				for (int j = 0; j < inputs; j++) {
					weights[j] += delta(i, j, e, learningR);
				}
				
				
			}
		}
	}
	
	private double delta(int i, int j, int e, double learningR) {
		return learningR * data[i][j] * e;
	}
	
	
	
	
	

}

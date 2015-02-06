package neuralnetwork;

import java.util.Scanner;

public class Main {
	private static final int inputs = 2;
	private static final double[][] and = {{0, 0, 0},
		                                   {0, 1, 0},
		                                   {1, 0, 0},
		                                   {1, 1, 1}};
	
	private static final double[][] or = {{0, 0, 0},
		                                 {0, 1, 1},
		                                 {1, 0, 1},
		                                 {1, 1, 1}};
	
	private static final double[][] xor = {{0, 0, 0},
		                                   {0, 1, 1},
		                                   {1, 0, 1},
		                                   {1, 1, 0}};
	private static final double learningR = 0.1;
	
	
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
	    
	    double[] userinputs = new double[2];
	    
	    Network n;
		
	    System.out.println("Which operation would you like to train? (and/or/xor)");
		String choice = sc.next();
	    switch(choice) {
	    case "and": n = new Network(inputs, and); break;
	    case "or" : n = new Network(inputs, or); break;
	    default: n = new Network(inputs, xor); break;
		}
		
		n.train(learningR, 0);
		
		
		System.out.println("Please enter two inputs");
		userinputs[0] = sc.nextDouble();
		userinputs[1] = sc.nextDouble();
		sc.close();
		
		System.out.println("Result is: " + n.getOutput(userinputs));
		
	}
}

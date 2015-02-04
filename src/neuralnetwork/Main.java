package neuralnetwork;

import java.util.Scanner;

public class Main {
	private static final int inputs = 2;
	private static final int[][] and = {{0, 0, 0},
		                                {0, 1, 0},
		                                {1, 0, 0},
		                                {1, 1, 1}};
	
	private static final int[][] or = {{0, 0, 0},
		                               {0, 1, 1},
		                               {1, 0, 1},
		                               {1, 1, 1}};
	private static final double learningR = 0.1;
	
	
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
	    
	    int[] userinputs = new int[2];
	    
	    Network n;
		
	    System.out.println("Which operation would you like to train? (and/or)");
		if (sc.next().equals("and"))
	    	  n = new Network(inputs, and);
		else
			  n = new Network(inputs, or);
		
		
	    
		n.train(learningR);
		
		System.out.println("Please enter two inputs");
		userinputs[0] = sc.nextInt();
		userinputs[1] = sc.nextInt();
		sc.close();
		
		System.out.println("Result is: " + n.getOutput(userinputs));
		
	}
}

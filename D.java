
package d;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author afnan
 */
public class D {

    public static int[] Dynamic(int[] values, int[] weights, int cap) {
        
	    int[][] Table = new int[values.length+1][]; //stores ideal solution for the given max capacity
	    boolean[][] contain = new boolean[values.length+1][]; // remembers if a particular object is put into the knapsack
	    int i, w, itemValue, itemWeight, newBest;

		
	    for (i = 0; i <= values.length; i++) { // create Dynamic tables
	        Table[i] = new int[cap+1];
	        contain[i] = new boolean[cap+1]; // two d array to keep track it item used or not 
	    }
             
	      //Each column in our table holds incrementally larger and larger knapsack capacity.  
             // each row brings in an additional item to be included in the knapsack. 
	     
	    for (i = 1; i <= values.length; i++) { //from 1 to n (row)
                
	        for (w = 0; w <= cap; w++) {
	            itemValue = values[i - 1];
	            itemWeight = weights[i - 1];
	            
	            if (w - itemWeight >= 0) {
	            	newBest = itemValue + Table[i - 1][w - itemWeight];
	            } else {
	            	newBest = Integer.MIN_VALUE;
	            }	            
                    
                    
	            if (weights[i - 1] <= w && Table[i - 1][w] < newBest) {
	                Table[i][w] = newBest;
	                contain[i][w] = true; // mark that a new value is put into the knapsack
	            } else {
	                Table[i][w] = Table[i - 1][w]; // use the solution from prevous row
	                contain[i][w] = false;
	            }
                   
	        }
	    }
           
	    int[] out = new int[values.length]; // 1 to keep the value, 0 to skip the value
	    
            // retrieve the path by walking up from bottom right corner
	    w = cap;
	    for (i = values.length; i > 0; i--) {
	        if (contain[i][w]) {
	            w = w - weights[i - 1];// up date cap
	            out[i-1] = 1;
	        }
	    }
	    
	    System.out.println("maximum profit : " + Table[values.length][cap]); 
	    return out;
	}

    public static void main(String[] args) throws IOException {
         Scanner input = new Scanner(Paths.get("project.txt"));

        int num = input.nextInt(); //number of items
        int cap = input.nextInt();//knapsack weight capacity
        int[] values =  new int[num];
        int[] weights=  new int[num];
        
        //read from file 
        int count = 0;
        while (count < num) {
            values[count] = input.nextInt();
            weights[count] = input.nextInt();
            count++;
        }
       
	System.out.println("output = " + Arrays.toString(Dynamic(values, weights, cap)));
    }
    
}

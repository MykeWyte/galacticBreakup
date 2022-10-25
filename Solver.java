package galacticBreakup;

import java.util.Scanner;
import java.util.Stack;
import java.util.Set;

//TODO: change header
/**
 * This class solves the "making change" problem
 * 
 * @author Matthew Jacobs, Michael White
 * @version 1.0.0
 * file: MakeChange.java
 * Created October 2022
 * Copyright Matthew Jacobs, Michael White
 *
 * Description: This class exists to solve the "making change" problem.
 * It takes parameters in the form of a list of coin denominations
 * and then a list of values to make change for. It returns how many of 
 * each coin denomination is needed to reach the desired value. It can be 
 * used to do the bottom-up approach and the recursive approach with and
 * without memoization. Each of these approaches fundamentally involves 
 * finding which of the values one denomination less than the current value
 * has the lowest coin count and adding one coin of the selected denomination
 * to that group to be added to the returned group of coins
 */
public class Solver {
    public static void main(String[] args) {
        // get information from command line
        
        // set up scanner to read from stdin
        Scanner scnr = new Scanner(System.in);
        
        // get denomination array
        System.out.println("Please enter the galactic dimmensions separated by "
                + "spaces (e.g. \"2 2 3\":\n");
        final int length = scnr.nextInt();
        final int width = scnr.nextInt();
        final int height = scnr.nextInt();
        
        // get number of seceding monarchies
        System.out.println("Please enter the number of seceding monarchies:\n");
        final int numMonarch = scnr.nextInt();
        
        // array of dominion values to store DJSetNodes
        DJSetNode[] dominions = new DJSetNode[length * width * height];        
        
        // set up and populate stack of seceding monarchies
        Stack<Stack<Integer>> secessions = new Stack();
        for(int i = 0; i < numMonarch; i++)
        {
            Stack<Integer> currMonarchy = new Stack();
            System.out.println("Please enter the number of dominions in the "
                    + "seceding monarchy:\n");
            int numDoms = scnr.nextInt();
            for (int j = 0; j < numDoms; j++)
            {
                System.out.println("Please enter the dominion number:\n");
                currMonarchy.push(scnr.nextInt());
            }
            secessions.push(currMonarchy);
        }
        
        // solve problem
        
        // storage variables
        Set<DJSetNode> DJSetForest;
        int numDividedMonths = 0;
        
        // main loop to see how sets combined
        while (!secessions.isEmpty())
        {
            // add back every dominion
            Stack<Integer> currMonarchy = secessions.pop();
            while (!currMonarchy.isEmpty())
            {
                DJSetNode temp = DJSetNode.makeNode();
                int nodeIndex = currMonarchy.pop();
                dominions[nodeIndex] = temp;
                // TODO: check for neighboring dominions using modulo functions
                // TODO: merge with nearby nodes, probably modify the union function to not include findset because that should be done beforehand
                // TODO: update forest and numDividedmonths (ask Michael for how he thought about the set)
            }
            
            // TODO: check number of available disjoint sets
        }
        
        System.out.print(numDividedMonths);
    }
}

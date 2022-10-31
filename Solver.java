package galacticBreakup;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Set;

/**
 * This class solves the "galactic breakup" problem
 * 
 * @author Matthew Jacobs, Michael White
 * @version 1.0.0
 * file: solver.java
 * Created October 2022
 * Copyright Matthew Jacobs, Michael White
 *
 * Description: This class exists to solve the "galactic breakup" problem.
 * It takes in the size of the galaxy followed by the number of seceding 
 * monarchies and then reads the dominions seceding for each monarchy
 * It uses disjoint sets to determine how many months the galaxy is divided as
 * monarchies secede
 */
public class Solver {
    
    // data structures needed in both main and neighborCheck
    static Set<DJSetNode> DJSetForest = new HashSet<>();
    static DJSetNode[] dominions;
    
    public static void main(String[] args) {
        // get information from command line
        
        // set up scanner to read from stdin
        Scanner scnr = new Scanner(System.in);
        
        // get denomination array
        //System.out.println("Please enter the galactic dimmensions separated by "
        //        + "spaces (e.g. \"2 2 3\":\n");
        final int length = scnr.nextInt();
        final int width = scnr.nextInt();
        final int height = scnr.nextInt();
        
        // get number of seceding monarchies
        //System.out.println("Please enter the number of seceding monarchies:\n");
        final int numMonarch = scnr.nextInt();
        
        // array of dominion values to store DJSetNodes
        dominions = new DJSetNode[length * width * height];        
        
        // set up and populate stack of seceding monarchies
        Stack<Stack<Integer>> secessions = new Stack();
        for(int i = 0; i < numMonarch; i++)
        {
            Stack<Integer> currMonarchy = new Stack();
            //System.out.println("Please enter the number of dominions in the "
            //        + "seceding monarchy:\n");
            int numDoms = scnr.nextInt();
            for (int j = 0; j < numDoms; j++)
            {
                //System.out.println("Please enter the dominion number:\n");
                currMonarchy.push(scnr.nextInt());
            }
            secessions.push(currMonarchy);
        }
        
        // solve problem
        
        // storage variables
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
                DJSetForest.add(temp);
                
                // initializing Base and converting 1D index to 3D coordinate
                DJSetNode Base = temp.findSet();
                int k = nodeIndex / (length * width);
                int m = (nodeIndex - (k * length * width)) / length;
                int n = nodeIndex - ((k * length * width) + (m * length));
                
                // checking neighboring dominions
                
                // checking below current location
                if ((k - 1) >= 0)
                {
                    neighborCheck(n, m, k-1, length, width, Base);
                }
                // checking above current location
                if ((k + 1) < height)
                {
                    neighborCheck(n, m, k+1, length, width, Base);
                }
                // checking to the "right" of current location
                if ((m - 1) >= 0)
                {
                    neighborCheck(n, m-1, k, length, width, Base);
                }
                // checking to the "left" of current location
                if ((m + 1) < width)
                {
                    neighborCheck(n, m+1, k, length, width, Base);
                }
                // cheching behind current location
                if ((n - 1) >= 0)
                {
                    neighborCheck(n-1, m, k, length, width, Base);
                }
                // checking in front of current location
                if ((n + 1) < length)
                {
                    neighborCheck(n+1, m, k, length, width, Base);
                }
            }
            // checkng if the empire is in multiple pieces
            if (DJSetForest.size() != 1)
            {
                numDividedMonths++;
            }
        }
        
        // output!
        System.out.print(numDividedMonths);
    }
    
    /**
     * @description checks if the neighbor of a given dominion (Base) exists,
     * and if so then checks to see if the representatives of the neighbor and 
     * Base dominions are different, if so it unions them together and updates 
     * the forest
     * 
     * @param n length component of 3D coordinate of neighbor dominion
     * @param m width component of 3D coordinate of neighbor dominion
     * @param k height component of 3D coordinate of neighbor dominion
     * @param length length of the galaxy
     * @param width width of the galaxy
     * @param Base the dominion being searched around
     */
    public static void neighborCheck(int n, int m, int k, int length, int width,
            DJSetNode Base) {
            
        // converting 3D coordinate back into 1D
        int index = (k * length * width) + (m * length) + n;
        
        // checking if the node exists
        if (dominions[index] != null)
        {
            // finding the representatives
            DJSetNode adjRep = (dominions[index]).findSet();
            DJSetNode BaseRep = Base.findSet();
            
            if (BaseRep != adjRep)
            {
                // unioning the reps and updating the forest
                DJSetNode newRep = BaseRep.union(adjRep);
                DJSetForest.remove(adjRep);
                DJSetForest.remove(BaseRep);
                DJSetForest.add(newRep);
            }
        }
    }
}
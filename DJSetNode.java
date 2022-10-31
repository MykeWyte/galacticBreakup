package galacticBreakup;

/**
 * This class serves as a node in a disjoint set. It has the ability to find 
 * its root node in the set as well as join sets when given another node 
 * 
 * @author Matthew Jacobs, Michael White
 * @version 1.0.0
 * file: DJSetNode.java
 * Created October 2022
 * Copyright Matthew Jacobs, Michael White
 *
 * Description: This class serves as a data structure to act as a node in a
 * disjoint set. It can be used as a reference in another sets unionSet()
 * method to join both sets
 */
public class DJSetNode {
    // member values
    private int rank;
    private DJSetNode par;
    
    // methods
    
    // constructors
    
    // functionally unnecessary, but follows powerpoint specifications
    /**
     * @description constructs default DJSetNode
     */
    public static DJSetNode makeNode()
    {
        return new DJSetNode(); 
    }
    
    private DJSetNode()
    {
        rank = 0;
        par = this;
    }
    
    // other methods
    /**
     * @description returns the root node in this nodes Disjoint set
     */
    public DJSetNode findSet()
    {
        if(this != this.par)
        {
            this.par = this.par.findSet();
        }
        return this.par;
    }
    
    /**
     * @description combines two disjoint sets into one set with a common head
     * node which is returned.
     * @param otherRep the other node to join sets with
     */
    public DJSetNode union(DJSetNode otherRep)
    {
        // ensure the sets are being merged at the root to keep accurate ranks
        DJSetNode thisRep = this;
        
        if(thisRep.rank < otherRep.rank)
        {
            thisRep.par = otherRep;
            
            if (otherRep.rank == thisRep.rank)
            {
                otherRep.rank++;
            }
            
            return otherRep;
        }
        else
        {
            otherRep.par = thisRep;
            
            if (otherRep.rank == thisRep.rank)
            {
                thisRep.rank++;
            }
            
            return thisRep;
        }
    }
}

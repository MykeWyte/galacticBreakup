package galacticBreakup;

/**
 *
 * @author Matthew Jacobs, Michael White
 */
public class DJSetNode {
    // member values
    private int rank;
    private DJSetNode par;
    
    // methods
    
    // constructors
    
    // functionally unnecessary, but follows powerpoint specifications
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
    public DJSetNode findSet()
    {
        if(this != this.par)
        {
            this.par = this.par.findSet();
        }
        return this.par;
    }
    
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
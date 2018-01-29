package mancala;

public class Pit
{
    
    private int stones;
    final private PitType type;
    
    Pit(int numberOfStones, PitType t)
    {
        stones = numberOfStones;
        type = t;
    }
    
    public void setStones(int s)
    {
        stones = s;
    }
    
    public int getStones()
    {
        return stones;
    }
    
    public PitType getType()
    {
        return type;
    }
}

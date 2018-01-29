package mancala;

/** Model for the Mancala boardgame. Has an array of pits to store stones 
 * in the pits. Has an int array to keep track of how many undoes are left
 * for each player. Has int lastPitMove and stonesInLastPitMove to keep 
 * track of the last move in order to be able to undo it later.
 * @author Janice Kim, Shlomo Nazarian, Justin Passanisi
 */
public class MancalaModel
{
    public static final int MAXIMUM_PITS = 14;
    public static final int MAXIMUM_UNDOS = 3;
    public static final int P1_MANCALA = 13; // array index
    public static final int P2_MANCALA = 6; // array index
    
    private Pit[] pits; // see mapping to board diagram at the bottom
    private int[] undoesLeft;
    private int lastPitMove;
    private int stonesInLastPitMove;
    
    /** constructs the board
     * @param stonesPerPit : number of stones to put in every pit, except 
     * for the Mancalas
     */
    public MancalaModel(int stonesPerPit)
    {
        pits = new Pit[MAXIMUM_PITS];
        
        PitType type;
        int stones;
        for (int i = 0; i < MAXIMUM_PITS; i++)
        {
            if (i < 6)
                type = PitType.Player2;
            else if (i == 6)
                type = PitType.Mancala2;
            else if (i < 13)
                type = PitType.Player1;
            else
                type = PitType.Mancala1;
            
            if (type == PitType.Mancala1 || type == PitType.Mancala2)
                stones = 0;
            else
                stones = stonesPerPit;
            
            pits[i] = new Pit(stones, type);
        }
        
        // set the undoes & last pit move info
        undoesLeft = new int[]{MAXIMUM_UNDOS, MAXIMUM_UNDOS};
        lastPitMove = -1; // no last move yet
        stonesInLastPitMove = -1; // no last move yet
    }
    
    /** performs a move
     * @param pitNumber : the pit to move the stones from
     * @param p : player's turn
     * @throws IllegalMoveException : either bc the pit selected is a 
     * mancala or the pit is empty or the pit is the opponent's pit
     * @return a code: 1 means p1 is next, 2 means p2 is next, 3 means p1 
     * won the game, 4 means p2 won the game, 5 means tie game over
     */
    public int move(int pitNumber, Player p) throws IllegalMoveException, EmptyPitException
    {
        if (pits[pitNumber].getType() == PitType.Mancala1 || 
            pits[pitNumber].getType() == PitType.Mancala2 || 
            (p == Player.Player1 && pits[pitNumber].getType() == PitType.Player2) ||
            (p == Player.Player2 && pits[pitNumber].getType() == PitType.Player1) )
            throw new IllegalMoveException();
        else if (pits[pitNumber].getStones() == 0)
            throw new EmptyPitException();
        
        int stones = pits[pitNumber].getStones();
        pits[pitNumber].setStones(0); // empty pit
        
        // keep track of move for later undo
        lastPitMove = pitNumber;
        stonesInLastPitMove = stones;
        
        // move the stones
        int i;
        for (i = pitNumber+1; stones > 0; i++)
        {
            i %= MAXIMUM_PITS;
            
            if ( !(pits[i].getType() == PitType.Mancala1 && (p == Player.Player2)) &&
                    !(pits[i].getType() == PitType.Mancala2 && (p == Player.Player1 )))
                {
                    pits[i].setStones(pits[i].getStones()+1);
                    stones--;
                }
        }
        
        i-=1;
        PitType pPit = PitType.Player1;
        if (p == Player.Player2) pPit = PitType.Player2;
        
        /* check for collect stones case: If the last stone dropped is in 
         * an empty pit on p's side, p gets to take that stone and all of 
         * the opponent's stones that are in the opposite pit. */ 
        
        if (pits[i].getType() == pPit && pits[i].getStones() == 1) 
        {
           if (p == Player.Player1)
               pits[P1_MANCALA].setStones(pits[P1_MANCALA].getStones() + 
                       pits[i].getStones() + pits[12-i].getStones());
           else
               pits[P2_MANCALA].setStones(pits[P2_MANCALA].getStones() + 
                       pits[i].getStones() + pits[12-i].getStones());
               
           pits[i].setStones(0);
           pits[12-i].setStones(0);
        }
        
        // check if game is over
        int winner = checkGameOver();
        if (winner != 0)
            return winner+2;
        
        /* check for free turn case : If the last stone p dropped is p's 
         * own Mancala, p gets a free turn.*/
        if (pits[i].getType() == PitType.Mancala1 || 
            pits[i].getType() == PitType.Mancala2 )
        {
            if (p == Player.Player1)
                return 1;
            else
                return 2;
        }
        
        // next player
        if (p == Player.Player1)
            return 2;
        return 1;
    }
    
    /**checks if someone won the game
     * The game ends when all six pits on one side of the Mancala board 
     * are empty. The player who still has stones on his side of the board
     * when the game ends captures all of those pieces and place them in 
     * his Mancala. The player who has the most stones in his Mancala wins
     * @return 0 for not over, or player who won (1 or 2), or 3 for tie
     */
    public int checkGameOver()
    {
        boolean empty = true;
        
        // check p1
        for (int i = 7; i < 13 && empty; i++)
            if (pits[i].getStones() != 0) empty = false;
        if (empty) 
        {
            for (int i = 0; i < 6 && empty; i++)
            {
                pits[P2_MANCALA].setStones(pits[P2_MANCALA].getStones() + 
                    pits[i].getStones());
                pits[i].setStones(0);
            }       
            
            if (pits[P1_MANCALA].getStones() > pits[P2_MANCALA].getStones())
                return 1;
            else if (pits[P1_MANCALA].getStones() == pits[P2_MANCALA].getStones())
                return 3;
            else
                return 2;
        }
        
        // check p2
        for (int i = 0; i < 6 && empty; i++)
            if (pits[i].getStones() != 0) empty = false;
        if (empty)
        {
            for (int i = 7; i < 13 && empty; i++)
            {
                pits[P1_MANCALA].setStones(pits[P1_MANCALA].getStones() + 
                    pits[i].getStones());
                pits[i].setStones(0);
            }       
            
            if (pits[P1_MANCALA].getStones() > pits[P2_MANCALA].getStones())
                return 1;
            else if (pits[P1_MANCALA].getStones() == pits[P2_MANCALA].getStones())
                return 3;
            else
                return 2;
        }
        
        return 0;
    }

    /** undoes the previous move
     * @param p : player making the undo
     * @throws NoMoreUndosException : player ran out of undoes
     */
    public void undo(Player p) throws NoMoreUndosException, NoMoveToUndoException
    { // fix case where last move collected
        if ( (p == Player.Player1 && undoesLeft[0] < 1) || 
             (p == Player.Player2 && undoesLeft[1] < 1) )
            throw new NoMoreUndosException();
        else if (lastPitMove == -1)
            throw new NoMoveToUndoException();
        
        int endPit = (lastPitMove + stonesInLastPitMove) % MAXIMUM_PITS;
        pits[lastPitMove].setStones(stonesInLastPitMove);
        
        // move the stones back
        for (int i = endPit; stonesInLastPitMove > 0; i--)
        {
            if (i < 0)
                i += MAXIMUM_PITS;
            
            if ( !(pits[i].getType() == PitType.Mancala1 && (p == Player.Player2)) &&
                    !(pits[i].getType() == PitType.Mancala2 && (p == Player.Player1 )))
                {
                    pits[i].setStones(pits[i].getStones()-1);
                    stonesInLastPitMove--;
                }
        }
        
        lastPitMove = -1; // to indicate no move to undo
    }
    
    /** String representation of board used for testing
     * @return board in string
     */
    public String toString()
    {
        String board = "  ";
        
        // top player
        for (int i = 12; i > 6; i--)
            board += pits[i].getStones() + "  ";
        
        int length = board.length();
        
        // left mancala
        board += "\n" + pits[13].getStones();
        
        // spaces
        for (int i = 0; i < length; i++)
            board += " ";
        
        // right mancala
        board += pits[6].getStones() + "\n  ";

        // bottom player
        for (int i = 0; i < 6; i++)
            board += pits[i].getStones() + "  ";        
        
        return board;
    }
}

/*
maping of gameboard to pits array
    12 11 10 9 8 7
13                  6
    0 1  2  3  4 5 
-------------------
0                   0
0   1 1  1  1  1  1 0
0   2 2  2  2  2  2 0
0                   0
Mancala1        Mancala2
*/
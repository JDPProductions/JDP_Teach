package mancala;

import java.util.Scanner;

/** Mancala GUI tester
 * @author Janice Kim, Shlomo Nazarian, Justin Passanisi
 */
public class MancalaTester {

    /** main()
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        DesignLayout[] designs = {new DesignOne(), new DesignTwo()};
//		MancalaComponent mancala = new MancalaComponent(designs);
		
		// model test
        Scanner input = new Scanner(System.in);
        MancalaModel m = new MancalaModel(4);
        System.out.println("initial board\n" + m.toString() + "\n\n");
        
        Player pl = Player.Player1;
        int winner = 0;
        int result, move;
        while (winner == 0)
        {
            try
            {
                System.out.print("Turn " + pl + " next move: ");
                move = input.nextInt();
                if (move != -1)
                {
                        result = m.move(move, pl);
                    if (result == 1)
                        pl = Player.Player1;
                    else if (result == 2)
                        pl = Player.Player2;
                    else 
                        winner = result;
                }
                else
                {
                    try
                    {
                        m.undo(Player.Player1);
                        System.out.println("after undo \n" + m.toString() + "\n\n");
                    } catch (NoMoreUndosException | NoMoveToUndoException e) 
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println(m.toString() + "\n\n");
            } catch (IllegalMoveException | EmptyPitException e)
            {
                System.out.println("invalid move ");
            }
        }
        
        System.out.println("done");
    }
    
}

package mancala;

import javax.swing.JFrame;

public class MancalaComponent extends JFrame
{
	private static final int WIDTH = 750;
	private static final int HEIGHT = 450;
	private MancalaBoard board;
	
	public MancalaComponent(DesignLayout[] designs)
	{
		setSize(WIDTH, HEIGHT);
//		displayBoard(designs[0]);
		setVisible(true);
		
		MancalaDialog selectScreen = new MancalaDialog(this, designs);
		selectScreen.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		selectScreen.display();
		this.
		start(selectScreen.getStoneNum(), selectScreen.getDesign());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
	}
	
	public void start(int stones, DesignLayout design)
	{
		System.out.println("stone number = " + stones + ", design number = " + design.getName());
		
		displayBoard(design);
	}

	public void displayBoard(DesignLayout design)
	{
		//removeAll();
		board = new MancalaBoard(design);
		add(board);
	}
	
	
}

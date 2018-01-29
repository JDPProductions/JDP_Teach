package mancala;

import java.awt.Graphics;

import javax.swing.JComponent;

public class MancalaBoard extends JComponent
{
	private DesignLayout layout;
	
	public MancalaBoard(DesignLayout layout)
	{
		this.layout = layout;
	}
	
	public void paintComponent(Graphics g)
	{
		//System.out.println("design # = " + layout.getName());
		layout.redraw(g);
	}
}

package mancala;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class DesignOne extends DesignLayout
{

	@Override
	public void redraw(Graphics g)
	{
		System.out.println("Design One!");
		Graphics2D graphics2g = (Graphics2D) g;
	    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(150, 160, 300, 200, 30, 30);
	    graphics2g.draw(roundedRectangle);
		
	}

	@Override
	public String getName()
	{
		return "Design One";
	}

}

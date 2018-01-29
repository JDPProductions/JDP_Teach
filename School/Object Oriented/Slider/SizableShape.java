//package slidertester2;

import java.awt.*;

/**
   A shape that can be resized
*/
public interface SizableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   
   /**
      Resizes the shape by a given amount.
      @param width
   */
   void resize(int width);
}

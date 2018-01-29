//package observertester;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
  A class that implements an Observer object that displays a barchart view of
  a data model.
*/
public class BarFrame extends JFrame implements ChangeListener, MouseListener
{

   private ArrayList<Double> a;
   private DataModel dataModel;

   private static final int ICON_WIDTH = 200;
   private static final int ICON_HEIGHT = 200;
    
   /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
   */
   public BarFrame(DataModel dataModel)
   {
      this.dataModel = dataModel;
      a = dataModel.getData();

      setLocation(0,200);
      setLayout(new BorderLayout());

      Icon barIcon = new Icon()
      {
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }
         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.red);

            double max =  (a.get(0)).doubleValue();
            for (Double v : a)
            {
               double val = v.doubleValue();
               if (val > max)
                  max = val;
            }

            double barHeight = getIconHeight() / a.size();
            
            int i = 0;
            for (Double v : a)
            {
               double value = v.doubleValue();

               double barLength = getIconWidth() * value / max;

               Rectangle2D.Double rectangle = new Rectangle2D.Double
                  (0, barHeight * i, barLength, barHeight);
               i++;
               g2.fill(rectangle);
            }
         }
      };

      add(new JLabel(barIcon));
      getContentPane().addMouseListener(this);
      

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }
   
   
   /**
    * Method to be called when data in the model is changed
    * @param e: Event that causes the change
   */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
      repaint();
   }
   
   /**
    * 
    * @param me 
    */
   @Override
    public void mousePressed(MouseEvent me) 
    {
        System.out.println("Mouse was clicked.");
        
        double y = me.getY();
        double x = me.getX();
        
        
        
        System.out.println("y : " + y);
        System.out.println("x : " + x);
        
        if(y >= 0 && y < 50)
        {
            a.set(0, x);
            dataModel.update(0, x);
        }
        else if(y >= 50 && y < 100)
        {
            a.set(1, x);
            dataModel.update(1, x);
        }
        else if(y >= 100 && y < 150)
        {
            a.set(2, x);
            dataModel.update(2, x);
        }
        else if(y >= 150 && y < 200)
        {
            a.set(3, x);
            dataModel.update(3, x);
        }
    }
   
    /**
     * Empty Mouse Event
     * @param me 
     */
   @Override
    public void mouseClicked(MouseEvent me) {
        }

    /**
     * Empty Mouse Event
     * @param me 
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        }

    /**
     * Empty Mouse Event
     * @param me 
     */
    @Override
    public void mouseEntered(MouseEvent me) {
        }

    /**
     * Empty Mouse Event
     * @param me 
     */
    @Override
    public void mouseExited(MouseEvent me) {
       }
}
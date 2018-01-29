//package observertester;

/**
 * This project is to test an observer model of a bar graph and mouse clicker
 * @author Justin Passanisi
 * Used some classes, logic and methods from hortsmann's code website
 */

import java.util.ArrayList;

/**
   A class for testing an implementation of the Observer pattern.
*/
public class ObserverTester
{
   public static void main(String[] args)
   {
        ArrayList<Double> data = new ArrayList<>();

        data.add(33.0);
        data.add(44.0);
        data.add(22.0);
        data.add(12.0);

        DataModel model = new DataModel(data);
        TextFrame frame = new TextFrame(model);
        BarFrame barFrame = new BarFrame(model);
      
        model.attach(frame);
        model.attach(barFrame);
      
   }
}




/*
Exercise 5.2 

make graph view edible
attach mouse listener to the panel that paints the graph

user clicks on point - moves bar to nearest point to the mouse click
update model
verify that both number view and graph view are both notified of the change


look up API of mouseListener

need action mousePressed, rest of the methods make do nothing

*/
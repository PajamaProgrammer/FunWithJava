/*
*   Introduction to Programming Using Java, Seventh Edition
*   http://math.hws.edu/javanotes/c3/s9.html
*   Playing with Grahpics in Java - see above link for a great introduction to creating simple graphics
*
*   Name: Pajama Programmer
*   Date: 5-Mar-2016
*   Discription: This program is a simply car graphic, the car will drives continuously on/off/on... 
*/

package movingwheel;

//Various and assorted imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  When run as a program, this class opens a window on the screen that
 *  shows a car driving from left to right, on screen-off screen-and on again...
 */
public class MovingWheel extends JPanel implements ActionListener {

    /**
     * Draws a frame of a car graphic. This subroutine is called 50 times per
     * second and is responsible for redrawing the entire drawing area.  The
     * parameter g is used for drawing. The frameNumber starts at zero and
     * increases by 1 each time this subroutine is called. The positions of the 
     * car, wheels, and spokes that are drawn depend on the frame number, 
     * giving the illusion of motion.
     */
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        
        int inset = 25;

        int centerX, centerY, diameter;
        int cornerX, cornerY, radius, carSize;
        
        diameter = 175;
        radius = diameter/2;
        carSize = diameter*3;
        
        centerX = frameNumber%(width+carSize+diameter) - carSize/2;
        centerY = height*2/3;
        cornerX = centerX - radius;
        cornerY = centerY - radius;
        

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,width,height);  // Fill drawing area with light gray.

        drawCar(g, cornerX - carSize/2 - radius/2, cornerY+radius-inset, diameter, carSize, frameNumber);
        drawWheel(g, centerX, centerY, diameter, inset, frameNumber);
        drawWheel(g, centerX - carSize/2, centerY, diameter, inset, frameNumber);    
    }
    //Helper function will create the car graphics
    private void drawCar(Graphics g, int cornerX, int cornerY, int diameter, int carSize, int frameNumber)
    {
        g.setColor(Color.ORANGE); 
        g.fillRect(cornerX, cornerY, carSize, 25);
        g.fillArc(cornerX, cornerY-diameter/2, diameter, diameter, 90, 90);
        g.fillArc(cornerX+carSize-diameter, cornerY-diameter/2, diameter, diameter, 90, -90);
        g.fillArc(cornerX+diameter/4, cornerY-diameter, carSize-diameter/2, 2*diameter, 0, 180);
        //g.fill
    }
    //Helper function will create the wheel
    private void drawWheel(Graphics g, int centerX, int centerY, int diameter, int width, int frameNumber)
    {
        int inset = width; 

        int cornerX, cornerY, radius;
        
        radius = diameter/2;

        cornerX = centerX - radius;
        cornerY = centerY - radius;
        

        g.setColor(Color.BLACK);  // Draw the wheel in black.
        
        g.drawOval(cornerX, cornerY, diameter, diameter);
        g.fillOval(cornerX, cornerY, diameter, diameter);
        
        g.setColor(Color.CYAN);
        g.drawOval(cornerX + inset/2, cornerY + inset/2, diameter - inset, diameter - inset);
        g.fillOval(cornerX + inset/2, cornerY + inset/2, diameter - inset, diameter - inset);
        
        inset = diameter - width;
        g.setColor(Color.BLACK);
        g.drawOval(cornerX + inset/2, cornerY + inset/2, diameter - inset, diameter - inset);
        g.fillOval(cornerX + inset/2, cornerY + inset/2, diameter - inset, diameter - inset);
        
        drawSpokes(g, centerX, centerY, 10, radius, frameNumber % 360);  
    }
    //Helper function draws the wheel spokes
    private void drawSpokes(Graphics g, int centerX, int centerY, int width, int radius, int angle)
    {
        //6 spokes 60 deg apart. 
        double rad;
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 3; i++)
        {
            rad = angle*Math.PI/180;
            x[0] = centerX + (int)(radius*Math.cos(rad));
            y[0] = centerY + (int)(radius*Math.sin(rad));

            x[1] = centerX + (int)(radius*Math.cos(rad));
            y[1] = centerY + (int)(radius*Math.sin(rad));

            x[2] = centerX - (int)(radius*Math.cos(rad));
            y[2] = centerY - (int)(radius*Math.sin(rad));

            x[3] = centerX - (int)(radius*Math.cos(rad));
            y[3] = centerY - (int)(radius*Math.sin(rad));

            //System.out.println(rad + " " + centerX + " " + centerY + " " + x[0] + " " + y[0] + " " + x[1] + " " + y[1]);
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, 4);
            
            angle += 60;
        }
    }
    
     
    public static void main(String[] args) {
        

        JFrame window = new JFrame("Just Rolling Along");

        MovingWheel drawingArea = new MovingWheel();
        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);

        drawingArea.setPreferredSize(new Dimension(600,450));

        window.pack();
        window.setLocation(100,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setResizable(true);
        
        Timer frameTimer = new Timer(20,drawingArea);

        window.setVisible(true);
        frameTimer.start();

    } // end main

    private int frameNum;
    
    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclock2;

import java.awt.*;
import javax.swing.*;

/**
 * This is the view, its only job is to display what the user sees.
 * It does not perform any calculations, just passes the information 
 * to the user in a graphical format.
 * @author RKOwen
 */
public class MyClockView2  extends JFrame{
    
    //Variables that control the clock size and text
    private int clockWidth = 515;
    private int clockHeight = 650;
    private JTextField timeField;
    private AnalogClock clockFace;
    
    MyClockView2()
    {
        //Give Window Frame a Title...
        super("Pajama Programmer's Clock");
        
        //Closing the window will end the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(clockWidth, clockHeight);
        this.setResizable(false);   
        this.setLocation(100, 100);
  
        //timeField is used for displaying the time
        timeField = new JTextField(10);
        timeField.setBorder(null);  //there was an ever so small gray border, this gets rid of it
        timeField.setBackground(Color.BLACK);   
        timeField.setForeground(Color.GREEN);
        timeField.setHorizontalAlignment(JTextField.CENTER);
        timeField.getCaret().setVisible(false);
        timeField.setEditable(false);
        timeField.setFont(new Font("Comic Sans MS", Font.BOLD, 75));    //The Clock Text...
        
        clockFace = new AnalogClock();
        
        //Content pane for the clock face
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new BorderLayout());
        clockPanel.setBackground(Color.GREEN);
        clockPanel.add(timeField, BorderLayout.SOUTH);
        clockPanel.add(clockFace,BorderLayout.CENTER);
            

        this.setContentPane(clockPanel);
        
       // this.pack();   //Just pack it to the size needed...   
        this.setVisible(true);
    }
    
    //This method simply updates the textField to display the time it is given.
    public void updateDisplay(int h, int m, int s, boolean isAM)
    {
        clockFace.drawHands(clockFace.getGraphics(), h, m, s);
        
        String AM_PM;
        if(isAM)
            AM_PM = "AM";
        else
            AM_PM = "PM";
        
        //String.format("%02d"... forces the numbers to be padded on the left with a zero
        timeField.setText("" + String.format("%02d", h) + ":" + String.format("%02d", m) + ":" 
                + String.format("%02d", s) + " " + AM_PM);
    }   
    
    private class AnalogClock extends JPanel
    {
        private final int clockRadius = 250;
        
        @Override
        public void paintComponent(Graphics g)
        {
            //Cast Graphics to Graphics 2D - this will improve the rendering
            Graphics2D g2 = (Graphics2D) g;
            //Turn on antialialsing - will produce a smoother clock face
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            //Draw Clock Face - there is a small offset added to center the clock properly
            g2.setColor(Color.BLACK);
            g2.fillOval(5, 5, 2*clockRadius, 2*clockRadius);
            g2.setColor(Color.GREEN);
            g2.fillOval(clockRadius-5, clockRadius-5, 20, 20);
            
            //Draw the tick marks
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            for (int i = 0; i<360; i+=6)
            {
                //There will be a tick mark every 6 degrees
                double rad = i*Math.PI/180.0 - Math.PI/2.0;
                double x1, y1, x2, y2;
                
                //Every 30 degrees, draw a bigger tick mark to indicate the hour
                if(i%30==0)
                {
                    x1 = clockRadius+5 + (clockRadius -30)*Math.cos(rad);
                    y1 = clockRadius+5 + (clockRadius -30)*Math.sin(rad);
                }
                else    //else just a small tick mark to inticate the minute
                {
                    x1 = clockRadius+5 + (clockRadius -10)*Math.cos(rad);
                    y1 = clockRadius+5 + (clockRadius -10)*Math.sin(rad);
                }
                
                x2 = clockRadius+5 + (clockRadius+5)*Math.cos(rad);
                y2 = clockRadius+5 + (clockRadius+5)*Math.sin(rad);
                g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
                
                //Adding the hour time
                if(i%30==0)
                {
                    x1 = clockRadius + (clockRadius -50)*Math.cos(rad+Math.PI/6);
                    y1 = clockRadius+10 + (clockRadius -50)*Math.sin(rad+Math.PI/6);
                    g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                    g2.drawString(Integer.toString(i/30+1), (int)x1, (int)y1);
                }
            }
             
        }
        
        public void drawHands(Graphics g, int h, int m, int s)
        {
            this.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GREEN);
            
            double mAngle = (6.0*m + 0.1*s)*Math.PI/180.0 - Math.PI/2.0;
            double hAngle = (30.0*h + 0.5*m)*Math.PI/180.0 - Math.PI/2.0;
            //System.out.println((6.0*m + 0.5*s)+ " " + (30.0*h+.5*m));
            
            double mX = clockRadius+5 + (clockRadius - 10)*Math.cos(mAngle);
            double mY = clockRadius+5 + (clockRadius - 10)*Math.sin(mAngle);
            
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(5 + clockRadius, 5 + clockRadius, (int)mX, (int)mY);
            
            double hX = clockRadius+5 + (clockRadius - 50)*Math.cos(hAngle);
            double hY = clockRadius+5 + (clockRadius - 50)*Math.sin(hAngle);
            
            g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(5 + clockRadius, 5 + clockRadius, (int)hX, (int)hY);
     
        }
    }  
}

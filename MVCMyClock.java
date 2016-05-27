package myclock;

/**
 * A simple digital clock using the MVC Model. 
 * Main method only instantiates the objects representing each component of the MVC model.
 * @author PajamaProgrammer
 */
public class MVCMyClock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        //theView object represents the graphical clock view, it does not calculate the time
        //It only contains the graphical interface and displays the time that it is given.
        //MyClockView theView = new MyClockView();    
        
        //theModel object contains the data, it is unaware of the graphical interface. 
        //It simply stores the time, updates the time, and will return the time
        //MyClockModel theModel = new MyClockModel(); 
        
        //theController object coordinates the actions between theView and theModel
        MyClockController theController = new MyClockController(new MyClockView(), new MyClockModel());
        
        //theView.setVisible(true);
    } 
}

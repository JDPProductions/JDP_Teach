/**
 * Project #1: Airline Reservation System
 * @copyright
 * @Version 1.5
 * @Justin Passanisi
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/**
 * This class represents the general passenger and the information associated with the passenger.
 */
class Passenger 
{
    //private int seatNumber;
    private String  seatNumber, reservationType, name, groupName;

    /**
     * Empty constructor
     */
    public Passenger(){}
    
    /**
     * Passenger information constructor
     * @param rt: Signifies the reservation type (Individual or User)
     * @param n: Signifies the name of the passenger
     * @param gn: Signifies the group name of the passenger (null if no group)
     * @param sn: signifies the seat number of the passenger
     */
    public Passenger(String rt, String n, String gn, String sn)
    {
        reservationType = rt;
        name = n;
        groupName = gn;
        seatNumber = sn;
    }
    
    /**
     * toString method to print Passenger information
     * @return: String containing object Passenger's information
     */
    public String toString()
    {
        return "Name: " + name + "\nGroup name: " + groupName + "\nSeat number: " + seatNumber + "\n Reservation Type: " + reservationType + "\n";
    }
    
    /**
     * Method to cancel individualsReservation
     */
    public void cancelIndividualReservation()
    {
        //Currently no remove logic has worked/been completed
    }

    //Accessor and Mutator methods not currently in use
    /*
    public String getReservationType()
    {
        return reservationType;
    }
    public String getName()
    {
        return name;
    }
    public String getGroupName()
    {
        return groupName;
    }
    public String getSeatNumber()
    {
        return seatNumber;
    }
    public void setReservationType(String rt)
    {
        reservationType = rt;
    }
    public void setName(String n)
    {
        name = n;
    }
    public void setGroupName(String gn)
    {
        groupName = gn;
    }
    public void setSeatNumber(String sn)
    {
        seatNumber=sn;
    }
    */
}

/**
* This class represents the seats on a plane, and assigns seats according to occupancy.
**/

class Plane
{
    //private int seatRow, seatColumn;
    private boolean occupied = false;
    private String seatNumber, seatClass, seatPreference;

    String[][] seatAvailability = new String[6][22];
    
    /**
    * Empty Constructor
    **/
    
    public Plane(){}
    
    /**
    * Individual seating Constructor 
    * @param sc: takes in a seatClass choice determined by user
    * @param sp: takes in a seatPrefence choice determined by user
    **/
    public Plane(String sc, String sp)
    {
        seatClass = sc;
        seatPreference = sp;
    }
    

    /**
     * Method that fills a double array of seats with "unoccupied" markers : "O"
     * Precondition: double array has been initialized
     * Postcondition: double array is now filled with "unoccupied" markers
     */
    public void fillSeats()
    {
        for(int i=0; i < seatAvailability.length ; i++)
        {
            for (int j=0; j < seatAvailability[i].length ; j++)
            {
                if(((i == 0) || (i == 5)) && j < 2)
                    seatAvailability[i][j]= " ";
                else
                    seatAvailability[i][j] = "O";
            }
        }
    }
    
    /**
     * Method that finds the nearest seat that matches the user's choices
     * @param seatClass: User determined seatClass choice (first or economy)
     * @param seatPreference: User determined seatPreference choice (window, center, or aisle)
     * @return seatNumber: Seat that is assigned to that User
     * Precondition: User has inputted class and preference according to ReservationSystem
     * Postcondition: Method returns a unoccupied seat that is closest to the front of the plane and matches user criteria
     */
    public String findSingleSeat(String seatClass, String seatPreference)
    {
        int i;
        int j;
        
        switch (seatClass) 
        {
            case "First":
                switch (seatPreference) 
                {
                    case "Window":
                        for(i = 1; i < 5; i++)
                        {
                            if((i == 1) || (i ==4))
                            {
                                j = 0;
                                while(j < 2)
                                {
                                    if(seatAvailability[i][j].equals("O"))
                                    {
                                        seatAvailability[i][j] = "X";
                                        seatNumber = Integer.toString(i) + Integer.toString(j);
                                        return seatNumber;
                                    }
                                    else
                                    {
                                        seatNumber = "empty";
                                        j++;
                                    }
                                    
                                }
                            }
                            else if(i>5)
                            {
                                seatNumber = "empty";
                            }
                        }
                        break;
                    case "Center":
                        case "Aisle":
                            for(i = 1; i < 4; i++)
                            {
                                if((i == 2)||(i == 3))
                                {
                                    j = 0;
                                    while(j < 2)
                                    {
                                        if(seatAvailability[i][j].equals("O"))
                                        {
                                            seatAvailability[i][j] = "X";
                                            seatNumber = Integer.toString(i) + Integer.toString(j); 
                                            return seatNumber;
                                        }
                                        else
                                        {
                                        seatNumber = "empty";   
                                        j++;
                                        }
                                    }
                                }
                                else if(i>4)
                                {
                                    seatNumber = "empty";
                                }
                            }
                    break;
            default:
                seatNumber = "empty";
                break;
        }
    break;
    case "Economy":
        switch (seatPreference) {
            case "Window":
                for(i = 0; i < seatAvailability.length; i++)
                {
                    if((i == 0) || (i==5))
                    {
                        j = 2;
                        while(j < 21)
                        {
                            if(seatAvailability[i][j].equals("O"))
                            {
                                seatAvailability[i][j] = "X";
                                seatNumber = Integer.toString(i) + Integer.toString(j);
                                return seatNumber;
                            }
                            else
                            {
                                seatNumber = "empty";
                                j++;
                            }
                        }
                    }
                    else if(i>5)
                    {
                        seatNumber = "empty";
                    }
                }
                break;
            case "Center":
                for(i = 0; i < seatAvailability.length; i++)
                    {
                        if((i == 1) || (i==4))
                        {
                            j = 2;
                            while(j < 21)
                            {
                                if(seatAvailability[i][j].equals("O"))
                                {
                                    seatAvailability[i][j] = "X";
                                    seatNumber = Integer.toString(i) + Integer.toString(j); 
                                    return seatNumber;
                                }
                                else
                                {
                                    seatNumber = "empty";
                                    j++;
                                }
                            }
                        }
                        else if(i>5)
                        {
                            seatNumber = "empty";
                        }
                    }
                break;
            case "Aisle":
                for(i = 0; i < seatAvailability.length; i++)
                {
                    if((i == 2)||(i ==3))
                        {
                            j = 2;
                            while(j < 21)
                            {
                                if(seatAvailability[i][j].equals("O"))
                                {
                                    seatAvailability[i][j] = "X";
                                    seatNumber = Integer.toString(i) + Integer.toString(j); 
                                    return seatNumber;
                                }
                                else
                                {
                                    seatNumber = "empty";
                                    j++;
                                }
                            }
                        }
                    else if(i>5)
                    {
                        seatNumber = "empty";
                    }
                }
                break;
        default:
            seatNumber = "empty";
            break;
        }
    break;
    default:
            seatNumber = "empty";
            break;
        }
        return seatNumber;
    }
    
    
    /**
     * Method that finds a column of unoccupied seats large enough for the group reservation
     * @param seatClass: User determined seatClass preference (first or economy)
     * @param groupSize: Size of group trying to add a reservation
     * @return j: Seat row that holds enough space for the group
     * Precondition: User has filled out seatClass and groupSize information as depicted in ReservationSystem
     * Postcondition: Usable column is returned to the reservation system to be used for seating
     */
    public int findGroupSeats(String seatClass, int groupSize)
    {
        int i;
        int j = 11111;
        int availableSeatCount = 0;
        
        switch(seatClass)
        {
            case "First":
                
                if(groupSize <= 4)
                {
                    for(j = 0; j <= 1; j++)
                    {
                        availableSeatCount = 0;
                        for(i = 1; i <= 4; i++)
                        {
                            if(seatAvailability[i][j].equals("O"))
                            {
                                availableSeatCount++;
                                System.out.println("i " + i + " j " + j + " seat count " + availableSeatCount);
                                if(availableSeatCount == groupSize)
                                {
                                    return j;
                                }
                            }
                        }
                    }
                }
                break;
            
            case "Economy":
                
                if(groupSize <= 6)
                {
                    for(j = 2; j < 23; j++)
                    {
                        availableSeatCount = 0;
                        for(i = 0; i < 5; i++)
                        {
                            if(seatAvailability[i][j].equals("O"))
                            {
                                availableSeatCount++;
                                if(availableSeatCount >= groupSize)
                                {
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        return j;
    }
    
    
    /**
     * Method that takes the seat row from method findGroupSeats and uses it to assign seats to a group
     * @param seatRow: The integer value of the first row that can seat all group members
     * @return seatNumber: Assigned seat to specific group member
     * Precondition: Method findGroupSeats was able to find a row or rows accommodations for group
     * Postcondition: Method assigns seat in row to specific group member 
     */
    public String findGroupIndividualSeat(int seatRow)
    {
        int j = seatRow;
        for(int i = 0; i <= 5; i++)
        {
            if(seatAvailability[i][j].equals("O"))
            {
                seatAvailability[i][j] = "X";
                seatNumber = Integer.toString(i) + Integer.toString(j);
                return seatNumber;
            }
        }
        return seatNumber;
    }
    
    
    
    /**
     * Method to print seat availability chart 2D Array
     * Precondition: 2D array of seats has been initialized and filled with "occupied" and "unoccupied" markers
     */
    public void printSeatAvailabilityChart()
    {
        for (int i=0; i < seatAvailability.length ; i++)
        {
            for (int j=0; j < seatAvailability[i].length ; j++)
            {
                System.out.print(seatAvailability[i][j] + " ");
                if(j == 21)
                    System.out.println("");
            }
        }
    }
    
    /**
     * Method to print strings of seatAvailability
     * Precondition: 2D array of seats has been initialized and filled with "occupied" and "unoccupied" markers
     */
    public void seatAvailable()
    {
        for (int i=0; i < seatAvailability.length ; i++)
        {
            for (int j=0; j < seatAvailability[i].length ; j++)
            {
                if(!(((i == 0) || (i == 5)) && j < 2))
                {
                    if(seatAvailability[i][j].equals("O"))
                        occupied = false;
                    else
                        occupied = true;
                    System.out.println("Seat Number : " + Integer.toString(i) + Integer.toString(j) + "    Occupied : " + occupied);
                }
            }
        }
    }
    
    
    /**
     * Method to remove an occupants marker on a seat
     * @param seatNumber: The number of the seat to be freed of occupants
     */
    public void removeSeatOccupance(String seatNumber)
    {
        //Currently no remove logic has worked/been completed
    }
    
    /**
     * toString method to print to console
     * @return: String of Plane seat data
     */
    public String toString()
    {
        return "Seat Number : " + seatNumber + "    Occupied: " + occupied;
    }
    
    /**
     * Mutator method to setSeatClass choice of user
     * @param sc: Takes in a seatClass choice determined by user
     * Precondition: User initializes a Plane object
     * Postcondition: The seatClass of the Plane object created is changed to the String value passed in
     */
    public void setSeatClass(String sc)
    {
        seatClass = sc;
    }
    
    /**
     * 
     * @param sp: takes in a seatPrefence choice determined by user
     * Precondition: User initializes a Plane object
     * Postcondition: The seatPreference of the Plane object created is changed to the String value passed in
     */
    public void setSeatPreference(String sp)
    {
        seatPreference = sp;
    }
    
    //Mutator and Accessor methods that are not currently being used
    /*
    public String getSeatClass()
    {
        return seatClass;
    }
    public String getSeatPreference()
    {
        return seatPreference;
    }
    public boolean getOccupied()
    {
        return occupied;
    }
    public String getSeatNumber()
    {
        return seatNumber;
    }
    public void setOccupied(boolean occ)
    {
        occupied = occ;
    }
    public void setSeatNumber(String sn)
    {
        seatNumber=sn;
    }
    */
}

/**
* This class controls the user input and relays information to and from the plane and passenger classes.
**/
public class ReservationSystem 
{
    public static void main(String[] args) 
    {
        Plane test = new Plane();
        test.fillSeats();
        List<Passenger> passengerManifest = new ArrayList<>();
        
        Scanner listener = new Scanner(System.in);
        System.out.println("Please Select From The Following List : \n" + "             Add [S]ingle Passenger \n" + "             Add [G]roup Passengers\n" + "                 [C]ancel Reservations\n" + "   Print Seating [A]vailability Chart\n" + "           Print [M]anifest\n" + "                 [Q]uit\n");
        String response = listener.next();
        
        while(!response.equals("Q"))
        {
            if(response.equals("S"))
            {
                String individualResponse = "";
                
                while(!individualResponse.equals("Q"))
                {
                    System.out.println("Single Passenger");
                    System.out.println("Name:");
                    String firstName = listener.next();
                    String lastName = listener.next();
                
                    System.out.println("Enter desired Seat Class or 'Q' to quit:");
                    String seatClass = listener.next();
                
                    while(!(seatClass.equals("First") || seatClass.equals("Economy") || seatClass.equals("Q")))
                    {
                        System.out.println("Invalid Seat Class. Please Type correct class or 'Q' to quit: ");
                        seatClass = listener.next();
                    }
                    if(!seatClass.equals("Q"))
                    {
                        test.setSeatClass(seatClass);
                        
                        System.out.println("Enter desired Seat Preference or 'Q' to quit:");
                        String seatPreference = listener.next();
                    
                        while(!(seatPreference.equals("Window") || seatPreference.equals("Center") || seatPreference.equals("Aisle") || seatPreference.equals("Q")))
                        {
                            System.out.println("Invalid Seat Preference. Please Type correct preference or 'Q' to quit: ");
                            seatPreference = listener.next();
                        }
                        if(!seatPreference.equals("Q"))
                        {
                            test.setSeatPreference(seatPreference);
                        
                            String seat = test.findSingleSeat(seatClass, seatPreference);
                            
                            if(!seat.equals("empty"))
                            {
                                Passenger info = new Passenger("Individual", firstName + " " + lastName, null, seat);
                                passengerManifest.add(info);   
                            }
                            else
                                System.out.println("We could not identify a available seat. Please refer to the seat availability chart and try again.");
                        }
                    }
                    individualResponse = "Q";
                }
            }

            else if(response.equals("G"))
            {
                String groupResponse = "";
                
                while(!groupResponse.equals("Q"))
                {
                    System.out.println("Group Passengers");
                    System.out.println("Group Name: ");
                    String groupName = listener.next();
                    
                    System.out.println("Number of persons in group: ");
                    String groupNum = listener.next();
                    int groupSize = Integer.parseInt(groupNum);
                    
                    System.out.println("Enter desired Seat Class or 'Q' to quit:");
                    String seatClass = listener.next();
                
                    while(!(seatClass.equals("First") || seatClass.equals("Economy") || seatClass.equals("Q")))
                    {
                        System.out.println("Invalid Seat Class. Please Type correct class or 'Q' to quit: ");
                        seatClass = listener.next();
                    }
                    
                    test.setSeatClass(seatClass);
                    int seatRow = test.findGroupSeats(seatClass, groupSize);
                    
                    for(int i = 0; i <=(groupSize-1); i++)
                    {
                        String seat = test.findGroupIndividualSeat(seatRow);
                    
                        System.out.println("Please Enter the Names of each individual:");
                        String firstName = listener.next();
                        String lastName = listener.next();
                        
                        Passenger info = new Passenger("Group", firstName + " " + lastName, groupName, seat);
                        passengerManifest.add(info);
                    }
                    groupResponse = "Q";
                }
            }
            
            else if(response.equals("C"))
            {
                //CURRENTLY CANCEL RESERVATION IS NOT OPERATIONAL
                
                System.out.println("Cancel Reservation");
                System.out.println("Please indicate whether it is an individual cancellation or group cancellation: ");
                String cancellationType = listener.next();
                
                if(cancellationType.equals("Individual"))
                {
                    System.out.println("Name the reservation is under: ");
                    String firstName = listener.next();
                    String lastName = listener.next();
                }    
                    /*
                    Iterator<Passenger> removalIterator = passengerManifest.iterator();
                    while (removalIterator.hasNext()) 
                    {
                        Passenger infoRemove = removalIterator.next();
                        if (infoRemove.getName().equals(firstName + " " + lastName)) 
                        {
                            passengerManifest.remove(infoRemove);
                        }
                    }
                    System.out.println(passengerManifest);
                    System.out.println("Seat Number: ");
                    String seat = listener.next();
                    
                    Passenger removal = new Passenger("Individual", firstName + " " + lastName, null, seat);
                    
                    passengerManifest.remove(removal);
                    
                    for(int i = 0; i<passengerManifest.size();i++)
                    {
                        if(passengerManifest.equals(removal))
                            passengerManifest.remove(i);
                    }
                    */
                
                else if(cancellationType.equals("Group"))
                {
                    System.out.println("Group name the reservation is under: ");
                    String groupName = listener.next();
                }
            }
            
            else if(response.equals("A"))
            {
                System.out.println("Seating Avaiability Chart");
                test.printSeatAvailabilityChart();
                test.seatAvailable();
            }
            
            else if(response.equals("M"))
            {
                System.out.println("Print Manifesto");
                for(Passenger pm:passengerManifest)
                {
                    System.out.println(pm);
                }
            }   
            System.out.println("\nPlease Select From The Following List : \n" + "             Add [S]ingle Passenger \n" + "             Add [G]roup Passengers\n" + "                 [C]ancel Reservations\n" + "   Print Seating [A]vailability Chart\n" + "           Print [M]anifest\n" + "                 [Q]uit\n");
            response = listener.next();
        }
        if(response.equals("Q"))
            System.out.println("Thank you for using our system. Have a nice flight.");  
    }
}

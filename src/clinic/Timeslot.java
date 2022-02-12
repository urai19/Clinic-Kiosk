/**
 Timeslot class creates an object that holds a timeslot's date and time.
 Timeslot includes the date and time of the appointment.
 @author Garvit Gupta, Udayan Rai
 */
package clinic;

public class Timeslot implements Comparable<Timeslot> {

    private Date date;
    private Time time;
    /**
     Constructor for the Timeslot class.
     @param date the date of timeslot of the appointment.
     @param time the time of timeslot of the appointment.
     */
    public Timeslot(Date date, Time time) {
        this.date = date;
        this.time = time;
    }
    /**
     Get method which returns the time of the Timeslot.
     @return time.
     */
    public Time getTime() {
        return time;
    }
    /**
     Get method which returns the Date of the Timeslot.
     @return date.
     */
    public Date getDate() {
        return date;
    }
    /**
     Converts the information of the timeslot into a String which is readable by Kiosk user.
     @return output
     */
    @Override
    public String toString() {
        String output = date.toString() + ", " + time.toString();
        return output;
    }
    /**
     Compares two timeslots and decide if they are equal or rank one timeslot larger than the other.
     @param slot the timeslot to be compared to the initialized timeslot.
     @return 0 if equal, 1 or -1 otherwise
     */
    @Override
    public int compareTo(Timeslot slot) {

        int output = 0;
        if (this.date.compareTo(slot.date) == 0 && this.time.compareTo(slot.time) == 0) return 0;

        if (this.date.compareTo(slot.date) == 1) {
            output = 1;
        } else if (this.date.compareTo(slot.date) == 0) {
            if (this.time.compareTo(slot.time) == 1) {
                output = 1;
            } else {
                output = -1;
            }
        } else {
            output = -1;
        }
        return output;
    }

    //Testbed main for the Timeslot class
    public static void main(String[] args) {
        //test case #1, testing the toString and the format of how the user sees the timeslot.
        Date date = new Date("2/19/22");
        Time time = new Time(12, 45);
        Timeslot firstTime = new Timeslot(date, time);
        System.out.println(firstTime.toString());


        //test #2 compareTo method
        Date date2_1 = new Date("2/19/2008");
        Date date2_2 = new Date("2/19/2008");
        Time time2_1 = new Time(12, 45);
        Time time2_2 = new Time(12, 45);
        Timeslot timeSlot2_1 = new Timeslot(date2_1, time2_1);
        Timeslot timeSlot2_2 = new Timeslot(date2_2, time2_2);
        System.out.println(timeSlot2_1.compareTo(timeSlot2_2)); //expected = 0

        //test #3 compareTo method. Same time but diff date
        Timeslot timeSlot3_1 = new Timeslot(new Date("2/19/2008"), new Time(12, 45));
        Timeslot timeSlot3_2 = new Timeslot(new Date("2/19/2009"), new Time(12, 45));
        System.out.println(timeSlot3_1.compareTo(timeSlot3_2)); //expected = 1

        //test #4 compareTo method. Same time but diff date
        Timeslot timeSlot4_1 = new Timeslot(new Date("2/19/2009"), new Time(12, 45));
        Timeslot timeSlot4_2 = new Timeslot(new Date("2/19/2008"), new Time(12, 45));
        System.out.println(timeSlot4_1.compareTo(timeSlot4_2)); //expected = -1

        //test #5 compareTo method. Same date but diff time
        Timeslot timeSlot5_1 = new Timeslot(new Date("2/19/2009"), new Time(12, 45));
        Timeslot timeSlot5_2 = new Timeslot(new Date("2/19/2009"), new Time(13, 45));
        System.out.println(timeSlot5_1.compareTo(timeSlot5_2)); //expected = 1

        //test #6 compareTo method. Same date but diff time
        Timeslot timeSlot6_1 = new Timeslot(new Date("2/19/2009"), new Time(13, 45));
        Timeslot timeSlot6_2 = new Timeslot(new Date("2/19/2009"), new Time(12, 45));
        System.out.println(timeSlot6_1.compareTo(timeSlot6_2)); //expected = -1

    }
}

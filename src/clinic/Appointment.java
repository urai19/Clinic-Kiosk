/**
 Appointment class creates an object that holds a patient's info, their appointment timeslot and location.
 @author Garvit Gupta, Udayan Rai
 */
package clinic;

public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    /**
     Constructor for the Appointment class.
     @param patient the patient who is booking the appointment.
     @param slot the timeslot of their appointment.
     @param location the location of clinic where the appointment is going to be.
     */
    public Appointment(Patient patient, Timeslot slot, Location location) {
        this.patient = patient;
        this.slot = slot;
        this.location = location;
    }
    /**
     Get method which returns the patient
     @return patient.
     */
    public Patient getPatient() {
        return patient;
    }
    /**
     Get method which returns the timeslot of the appointment
     @return slot.
     */
    public Timeslot getTimeslot() {
        return slot;
    }
    /**
     Get method which returns the location of the appointment
     @return location.
     */
    public Location getLocation() {
        return location;
    }
    /**
     Checks if two appointments are exactly equal.
     @param obj the name of the person to delete.
     @return true if appointments are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appointment) {
            Appointment checkAppt = (Appointment) obj;
            return checkAppt.patient.compareTo(this.patient) == 0 &&
                    checkAppt.slot.compareTo(this.slot) == 0 &&
                    checkAppt.location.compareTo(this.location) == 0;
        }
        return false;
    }
    /**
     Converts the information of the appointment into a String which is readable by Kiosk user.
     Lists the patient info first, then timeslot, and then location of the appointment.
     @return output
     */
    @Override
    public String toString() {
        String output = this.patient.toString() + ", Appointment Detail: " + this.slot.toString() + ", " + this.location.toString() +
                ", " + this.location.name();
        return output;

    }

    public static void main(String[] args) {
        //Test Case#1, testing the toString() method.
        Appointment apt = new Appointment(new Patient("Udayan", "Rai", new Date("02/19/2002")),
                new Timeslot(new Date("02/10/2022"), new Time(12, 20)),
                Location.MERCER);
        System.out.println(apt.toString());

        //Test Case#2, testing the equals method
        Appointment test1 = new Appointment(new Patient("Garvit", "Gupta", new Date("03/12/2002")),
                new Timeslot(new Date("02/10/2022"), new Time(12, 20)), Location.MERCER);
        System.out.println(apt.equals(test1));

        Appointment test2 = new Appointment(new Patient("Udayan", "Rai", new Date("02/19/2002")),
                new Timeslot(new Date("02/10/2022"), new Time(12, 20)), Location.MERCER);
        System.out.println(apt.equals(test2));
    }
}

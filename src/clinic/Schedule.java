/**
 Schedule encompasses an array container to hold, add, remove, and sort appointments.
 Schedule class prints out the appointments in order of patients, location, and the order of the array.
 @author Garvit Gupta, Udayan Rai
 */
package clinic;

public class Schedule {

    public static final int NOT_FOUND = -1;
    private Appointment[] appointments;
    private int numAppts;

    /**
     Constructor for the Schedule class.
     @param appointments the array of appointments.
     @param numAppts the number of appointments in the array.
     */
    public Schedule(Appointment[] appointments, int numAppts) {
        this.appointments = appointments;
        this.numAppts = numAppts;
    }
    /**
     Get method which returns the array of appointments
     @return appointments array.
     */
    public Appointment[] getAppointments() {
        return appointments;
    }

    /**
     Creates a copy of the appointments array and then sorts it in order of timeslots.
     Helper method for the Zipcodes method.
     Does nothing if name doesn't appear in the list.
     @param inputArray the name of the person to delete.
     @return Appointment[] that is sorted by timeslot .
     */
    private Appointment[] sortTimeslot(Appointment[] inputArray) { //first sort by timeslot
        Appointment[] sortedAppts = new Appointment[numAppts];
        for (int i = 0; i < sortedAppts.length; i++) {
            if (inputArray[i] != null) {
                sortedAppts[i] = inputArray[i];
            }
        }
        for (int i = 0; i < sortedAppts.length - 1; i++) {

            int index = i;
            for (int j = i + 1; j < sortedAppts.length; j++) {
                if (sortedAppts[j].getTimeslot().compareTo(sortedAppts[index].getTimeslot()) == 1) {
                    index = j;
                }
            }
            Appointment smallerTimeslot = sortedAppts[index];
            sortedAppts[index] = sortedAppts[i];
            sortedAppts[i] = smallerTimeslot;
        }
        return sortedAppts;
    }
    /**
     Finds the indicated appointment in the array and returns its index
     Does nothing if appointment doesn't appear in the list.
     @param appt the appointment that needs to be found.
     @return int if appointment was found, NOT_FOUND otherwise.
     */
    private int find(Appointment appt) {//return the index, or NOT_FOUND
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null && appointments[i].equals(appt)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    /**
     Increases the length of the appointments array by 4
     */
    private void grow() {//increase the capacity of the container by 4
        if (appointments.length == 0) {
            appointments = new Appointment[4];
        }
        Appointment[] newAppointmentArray = new Appointment[appointments.length + 4];
        for (int i = 0; i < appointments.length; i++) {
            newAppointmentArray[i] = appointments[i];
        }
        appointments = newAppointmentArray;
    }
    /**
     Adds the appointment with the given info to the list
     @param appt the appointment to add to the list.
     @return true if appointment was added, false otherwise.
     */
    public boolean add(Appointment appt) {
        boolean returner = false;
        if (find(appt) != -1) return false;
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] == null) {
                appointments[i] = appt;
                numAppts++;
                returner = true;
                break;
            } else {
                grow();
            }
        }
        return returner;
    }
    /**
     Deletes the appointment with the given info from the list.
     Does nothing if name doesn't appear in the list.
     @param appt the name of the person to delete.
     @return true if appointment was deleted, false otherwise.
     */
    public boolean remove(Appointment appt) {
        int index = find(appt);
        if (index == -1) return false;
        Appointment[] arr = new Appointment[numAppts];
        int count = 0;
        for (int i = 0; i < appointments.length; i++) {
            if (count < numAppts && i != index) {
                arr[count] = appointments[i];
                count++;
            }
        }
        numAppts--;
        appointments = arr;
        return true;
    }
    /**
     Prints the appointments in the current order of the array
     */
    public void print() {
        for (Appointment appt : appointments) {
            if (appt != null) {
                System.out.println(appt.toString());
            }
        }
    } //print all the appointments in current order
    /**
     Creates and returns a copy of the appointments array.
     Does not copy any null values into the new array.
     @return Appointment[] array which is a copy of the appointments array.
     */
    private Appointment[] arrayCopy(){
        Appointment[] sortedAppts = new Appointment[numAppts];
        int index = 0;
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null) {
                sortedAppts[index] = appointments[i];
                index++;
            }
        }
        return sortedAppts;
    }
    /**
     Prints the appointments in order of zipcodes via selection sort.
     If zipcodes are the same, then sort by timeslots.
     */
    public void printByZip() {//sort by zip codes and print
        Appointment[] sortedAppts= arrayCopy();
        Appointment[] sortedTimeSlots = sortTimeslot(sortedAppts);
        Appointment[] sortedZips = new Appointment[sortedTimeSlots.length];
        int counter = 0;
        for (int i = 0; i < sortedTimeSlots.length; i++) {
            if (sortedTimeSlots[i] != null) {
                if (sortedTimeSlots[i].getLocation().equals(Location.UNION)) {
                    sortedZips[counter] = sortedTimeSlots[i];
                    counter++;
                }
            }
        }
        for (int i = 0; i < sortedTimeSlots.length; i++) {
            if (sortedTimeSlots[i] != null) {
                if (sortedTimeSlots[i].getLocation().equals(Location.MORRIS)) {
                    sortedZips[counter] = sortedTimeSlots[i];
                    counter++;
                }
            }
        }
        for (int i = 0; i < sortedTimeSlots.length; i++) {
            if (sortedTimeSlots[i] != null) {
                if (sortedTimeSlots[i].getLocation().equals(Location.MERCER)) {
                    sortedZips[counter] = sortedTimeSlots[i];
                    counter++;
                }
            }
        }
        for (int i = 0; i < sortedTimeSlots.length; i++) {
            if (sortedTimeSlots[i] != null) {
                if (sortedTimeSlots[i].getLocation().equals(Location.SOMERSET)) {
                    sortedZips[counter] = sortedTimeSlots[i];
                    counter++;
                }
            }
        }
        for (int i = 0; i < sortedTimeSlots.length; i++) {
            if (sortedTimeSlots[i] != null) {
                if (sortedTimeSlots[i].getLocation().equals(Location.MIDDLESEX)) {
                    sortedZips[counter] = sortedTimeSlots[i];
                    counter++;
                }
            }
        }
        for (Appointment appt : sortedZips) {
            System.out.println(appt.toString());
        }
    }
    /**
     Prints the appointments in order of patients via selection sort.
     First sort by last names, first names, and then DOBs.
     */
    public void printByPatient() {
        Appointment[] sortedAppts = new Appointment[numAppts];
        int counter = 0;
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null) {
                sortedAppts[counter] = appointments[i];
                counter++;
            }
        }
        for (int i = 0; i < sortedAppts.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < sortedAppts.length; j++) {
                if (sortedAppts[j].getPatient().compareTo(sortedAppts[index].getPatient()) == 1) {
                    index = j;
                }
            }
            Appointment smallerTimeslot = sortedAppts[index];
            sortedAppts[index] = sortedAppts[i];
            sortedAppts[i] = smallerTimeslot;
        }
        for (Appointment appt : sortedAppts) {
            System.out.println(appt.toString());
        }
    } //sort by patient and print

    public static void main(String[] args) {
        //Test Case#1, testing print()
        Appointment testAppt_1 = new Appointment(new Patient("Garvit", "Gupta", new Date("03/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.SOMERSET);
        Appointment testAppt_2 = new Appointment(new Patient("Garvit", "jj", new Date("01/19/2002")),
                new Timeslot(new Date("02/10/2000"), new Time(12, 20)),
                Location.MERCER);
        Appointment testAppt_3 = new Appointment(new Patient("Garvit", "Gupta", new Date("02/19/2002")),
                new Timeslot(new Date("02/10/2000"), new Time(12, 20)),
                Location.SOMERSET);

        //Test Case#2
        Appointment testAppt_4 = new Appointment(new Patient("Garvit", "Apple", new Date("03/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.SOMERSET);
        Appointment testAppt_5 = new Appointment(new Patient("Garvit", "Zyla", new Date("01/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.MERCER);
        Appointment testAppt_6 = new Appointment(new Patient("Garvit", "Gupta", new Date("02/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.SOMERSET);

        Appointment testAppt_7 = new Appointment(new Patient("Garvit", "Gupta", new Date("02/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.SOMERSET);

        Appointment[] apptArray = {testAppt_4, testAppt_5, testAppt_6};
        Schedule testSchedule = new Schedule(apptArray, 3);
//        testSchedule.print();
//        System.out.println("*********ADDING Smith********");
        //testSchedule.printByZip();
        // testSchedule.printByPatient();
        Appointment testAppt_8 = new Appointment(new Patient("Garvit", "Smith", new Date("02/19/2002")),
                new Timeslot(new Date("01/10/2000"), new Time(12, 20)),
                Location.UNION);
        testSchedule.add(testAppt_8);
        //testSchedule.print();
        //System.out.println("*********PrintZip********");
        //testSchedule.printByZip();
        //System.out.println("*********PrintPatients********");
        testSchedule.printByPatient();
        System.out.println("*********Removing Apple********");
        testSchedule.remove(testAppt_4);
        testSchedule.print();
        System.out.println("*********PrintZip********");
        testSchedule.printByZip();
        System.out.println("*********PrintPatients********");
        testSchedule.printByPatient();


        //testSchedule.printByZip();

    }
}

package clinic;

public class Schedule {

    public static final int NOT_FOUND= -1;
    private Appointment [] appointments;
    private int numAppts;

    public Schedule(Appointment[] appointments, int numAppts){
        this.appointments=appointments;
        this.numAppts=numAppts;
    }

    private static int getMin(int[] inputArray){
        int minValueIndex = inputArray[0];
        for(int i=1; i<inputArray.length;i++){
            if(inputArray[i] < minValueIndex){
                minValueIndex = i;
            }
        }
        return minValueIndex;
    }

    private static Appointment[] sortTimeslot(Appointment[] inputArray) { //first sort by timeslot
        Appointment[] sortedAppts = new Appointment[inputArray.length];
        for (int i = 0; i < sortedAppts.length; i++) {
            sortedAppts[i] = inputArray[i];
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

    private int find(Appointment appt) {//return the index, or NOT_FOUND
        for (int i = 0; i<appointments.length; i++){
            if (appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {//increase the capacity of the container by 4

       Appointment[] newAppointmentArray = new Appointment[appointments.length + 4];
       for (int i=0; i<appointments.length; i++){
           newAppointmentArray[i] = appointments[i];
       }
        appointments = newAppointmentArray;
    }

    public boolean add(Appointment appt){

        boolean returner = false;

        if (appt.getPatient().getDob().isValid() && appt.getTimeslot().getTime().isValid()){
            returner = false;
        }

        for (int i=0; i < appointments.length; i++){
            if (appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == 0) returner = false;
        }

        if (appointments[appointments.length-1] != null){
            grow();
        }

        for (int i=0; i<appointments.length;i++){
            if (appointments[i]==null){
                appointments[i] = appt;
                returner = true;
            }
        }
        return returner;
    }

    public boolean remove(Appointment appt) {
        boolean returner  = false;
        for (int i = 0; i< appointments.length; i++){
            if (appointments[i].equals(appt)){
                appointments[i] = null;
                returner = true;
            }
        }
        return returner;
    }

    public void print() {
        for(Appointment appt:appointments){
            System.out.println(appt.toString());
        }
    } //print all the appointments in current order

    public void printByZip() {//sort by zip codes and print

    Appointment[] sortedAppts  = new Appointment[appointments.length];
    Appointment[] sortedTimeSlots  = sortTimeslot(sortedAppts);
    Appointment[] sortedZips  = new Appointment[sortedTimeSlots.length];
    int counter = 0;

         for (int i =0; i < sortedTimeSlots.length; i++){
          if (sortedTimeSlots[i].getLocation().equals(Location.UNION)){
            sortedZips[counter] = sortedTimeSlots[i];
            counter++;
              }
         }

         for (int i =0; i < sortedTimeSlots.length; i++){
            if (sortedTimeSlots[i].getLocation().equals(Location.MORRIS)){
                sortedZips[counter] = sortedTimeSlots[i];
                counter++;
             }
         }

        for (int i =0; i < sortedTimeSlots.length; i++){
            if (sortedTimeSlots[i].getLocation().equals(Location.MERCER)){
                sortedZips[counter] = sortedTimeSlots[i];
                counter++;
            }
        }
        for (int i =0; i < sortedTimeSlots.length; i++){
            if (sortedTimeSlots[i].getLocation().equals(Location.SOMERSET)){
                sortedZips[counter] = sortedTimeSlots[i];
                counter++;
            }
        }

        for (int i =0; i < sortedTimeSlots.length; i++){
            if (sortedTimeSlots[i].getLocation().equals(Location.MIDDLESEX)){
                sortedZips[counter] = sortedTimeSlots[i];
                counter++;
            }
        }
        for(Appointment appt:sortedZips){
            System.out.println(appt.toString());
        }
    }

    public void printByPatient() {

        Appointment[] sortedAppts = new Appointment[appointments.length];
        for (int i = 0; i < sortedAppts.length; i++) {
            sortedAppts[i] = appointments[i];
        }

        for (int i = 0; i < sortedAppts.length - 1; i++) {

            int index = i;
            for (int j = i + 1; j < sortedAppts.length; j++) {
                if (sortedAppts[j].getPatient().compareTo(sortedAppts[index].getPatient()) == -1) {
                    index = j;
                }
            }
            Appointment smallerTimeslot = sortedAppts[index];
            sortedAppts[index] = sortedAppts[i];
            sortedAppts[i] = smallerTimeslot;
        }

        for(Appointment appt:sortedAppts){
            System.out.println(appt.toString());
        }

    } //sort by patient and print
}

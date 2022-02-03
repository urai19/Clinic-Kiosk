package clinic;

public class Schedule {

    private Appointment [] appointments;
    private int numAppts;

    public static final int NOT_FOUND= -1;

    public Schedule(Appointment[] appointments, int numAppts){
        this.appointments=appointments;
        this.numAppts=numAppts;
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



    }
    public boolean add(Appointment appt) {
return false;
    }
    public boolean remove(Appointment appt) {
return false;
    }
    public void print() {

    } //print all the appointments in current order
    public void printByZip() {

    } //sort by zip codes and print
    public void printByPatient() {

    } //sort by patient and print
}

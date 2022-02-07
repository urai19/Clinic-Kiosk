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
    public void printByZip() {
        Appointment[] sortedAppts= new Appointment[appointments.length];
        for(int i=0; i< sortedAppts.length; i++){
            for(Appointment appt:appointments){
                if(appt.getLocation().equals(Location.UNION)){
                    sortedAppts[i]=appt;
                    i++;
                }
            }
            for(Appointment appt:appointments){
                if(appt.getLocation().equals(Location.MORRIS)){
                    sortedAppts[i]=appt;
                    i++;
                }
            }
        }
    } //sort by zip codes and print
    public void printByPatient() {

    } //sort by patient and print
}

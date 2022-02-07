package clinic;

public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    public Appointment(Patient patient, Timeslot slot, Location location){
        this.patient=patient;
        this.slot=slot;
        this.location=location;
    }

    public Patient getPatient(){
        return patient;
    }
    public Timeslot getTimeslot(){
        return slot;
    }
    public Location getLocation(){
        return location;
    }



    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment checkAppt=(Appointment) obj;
            return checkAppt.patient.compareTo(this.patient) == 0 &&
                    checkAppt.slot.compareTo(this.slot) == 0 &&
                    checkAppt.location.compareTo(this.location) == 0;
        }
        return false;
    }

    @Override
    public String toString(){
        String output=this.patient.toString()+", Appointment Detail: "+ this.slot.toString()+", "+this.location.toString() +
                        ", "+ this.location.name();
        return output;

    }



    public static void main(String[] args) {
        //Test Case#1, testing the toString() method.
        Appointment apt=new Appointment(new Patient("Udayan","Rai",new Date("02/19/2002")),
                                        new Timeslot(new Date("02/10/2022"), new Time(12,20)),
                                        Location.MERCER);
        System.out.println(apt.toString());

        //Test Case#2, testing the equals method
        Appointment test1= new Appointment(new Patient("Garvit", "Gupta", new Date("03/12/2002")),
                          new Timeslot(new Date("02/10/2022"),new Time(12,20)), Location.MERCER);
        System.out.println(apt.equals(test1));

        Appointment test2= new Appointment(new Patient("Udayan", "Rai", new Date("02/19/2002")),
                new Timeslot(new Date("02/10/2022"),new Time(12,20)), Location.MERCER);
        System.out.println(apt.equals(test2));
    }
}

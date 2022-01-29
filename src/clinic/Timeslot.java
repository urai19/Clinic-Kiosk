package clinic;

public class Timeslot implements Comparable<Timeslot> {

    private Date date;
    private Time time;

    public Timeslot(Date date, Time time){
        this.date= date;
        this.time=time;
    }

    @Override
    public String toString() {
    String output= date.toString() +" "+ time.toString();
    return output;
    }

    @Override
    public int compareTo(Timeslot slot) {
        if(this.date.compareTo(slot.date)==1 && this.time.compareTo(slot.time) ==1){
            return 1;
        }
        else return 0;
    }

    //Testbed main for the Timeslot class
    public static void main(String[] args) {
        //test case #1, testing the toString and the format of how the user sees the timeslot.
        Date date= new Date("2/19/22");
        Time time= new Time(12,45);
        Timeslot firstTime= new Timeslot(date, time);
        System.out.println(firstTime.toString());

    }
}

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
    public int compareTo(Timeslot slot) { }
}

package clinic;

public class Time implements Comparable<Time> {

    private int hour;
    private int minute;

    public boolean isValid() {
        if (hour < 9 || hour >= 17 || minute < 0 || minute > 60) {
            return false;
        }

        if (minute != 00 || minute != 15 || minute != 30 || minute != 45) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public String toString() {
        String output = hour + ":" + minute;
        return output;
    }

    @Override
    public int compareTo(Time time) {
        if (this.hour == time.hour && this.minute == time.minute) {
            return 1;
        } else return 0;
    }
}

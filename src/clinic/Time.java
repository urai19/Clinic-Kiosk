package clinic;

public class Time implements Comparable<Time> {

    private int hour;
    private int minute;

    public Time(int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }

    public boolean isValid() {
        if (hour < 9 || hour >= 17 || minute < 0 || minute >59) {
            return false;
        } else if (minute%15 !=0) {
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
        int output = 0;
        if(this.hour==time.hour && this.minute==time.minute){   //when the times are the same
            return 0;
        }
        if(time.hour < this.hour){
            output=-1;
        }
        else if(time.hour >this.hour){
            output = 1;
        }
        else{
            if(time.minute<this.minute) output=-1;
            else output=1;
        }
        return output;
    }


    public static void main(String[] args) {
        //Test Case #1, testing isValid with an impossible time(when hour>23 and minute>59)
        Time time1_1 = new Time(82,15);
        System.out.println(time1_1.isValid());//Expected: false

        //Test Case #2, testing isValid with an impossible time(when hour>23 and minute>59)
        Time time2_1 = new Time(11,88);
        System.out.println(time2_1.isValid());//Expected: false

        //Test Case #3, testing isValid with a time that is out of hours(9:00 to 16:45).
        Time time3_1 = new Time(8,15);
        System.out.println(time3_1.isValid());//Expected: false

        //Test Case #4, testing isValid with a time that is out of hours(9:00 to 16:45).
        Time time4_1 = new Time(17,00);
        System.out.println(time4_1.isValid());//Expected: false

        //Test Case #5, testing isValid with a time that is during the clinic hours but not a multiple of 15.
        Time time5_1 = new Time(10,16);
        System.out.println(time5_1.isValid());//Expected: false

        //Test Case #6, testing isValid with a time that is during the clinic hours and correct interval.
        Time time6_1 = new Time(13,45);
        System.out.println(time6_1.isValid());//Expected: True
        
        System.out.println("Valid Now: ");

        //Test Case #7, testing isValid with edge cases
        Time time7_1 = new Time(9,00);
        Time time7_2 = new Time(16,45);
        System.out.println(time7_1.isValid());//Expected: True
        System.out.println(time7_2.isValid());//Expected: True

        System.out.println("Testing toString");

        //Test Case #8, testing toString
        Time time8_1 = new Time(13,45);
        System.out.println(time8_1.toString());

        System.out.println("Testing compareTo");

        //Test Case #9, testing compareTo with same times
        Time time9_1 = new Time(13,45);
        Time time9_2 = new Time(13,45);
        System.out.println(time9_1.compareTo(time9_2)); //expected:0

        //Test Case #10, testing compareTo with same hour but diff minutes
        Time time10_1 = new Time(13,45);
        Time time10_2 = new Time(14,45);
        System.out.println(time10_1.compareTo(time10_2)); //expected:1

        //Test Case #11, testing compareTo with same hour but diff minutes
        Time time11_1 = new Time(14,45);
        Time time11_2 = new Time(13,45);
        System.out.println(time11_1.compareTo(time11_2)); //expected:-1

        //Test Case #12, testing compareTo with different hour but same minutes
        Time time12_1 = new Time(13,45);
        Time time12_2 = new Time(13,46);
        System.out.println(time12_1.compareTo(time12_2)); //expected:1

        //Test Case #13, testing compareTo with diff hour but same minutes
        Time time13_1 = new Time(13,46);
        Time time13_2 = new Time(13,45);
        System.out.println(time13_1.compareTo(time13_2)); //expected:-1
    }
}

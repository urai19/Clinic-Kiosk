package clinic;
import java.util.Calendar;

public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    public static final int JAN=1;
    public static final int FEB=2;
    public static final int MAR=3;
    public static final int APR=4;
    public static final int MAY=5;
    public static final int JUN=6;
    public static final int JUL=7;
    public static final int AUG=8;
    public static final int SEP=9;
    public static final int OCT=10;
    public static final int NOV=11;
    public static final int DEC=12;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    public Date(String date) {
        int[] backslashIndex = new int[10];
        int counter = 0;
        for(int i = 0; i < date.length();i++){
            if (date.charAt(i) ==  '/'){
                backslashIndex[counter]=i;
                counter++;
            }
        }
        String yearString = "";
        String monthString = "";
        String dayString = "";
            for(int i = 0; i<date.length();i++){
                if (i < backslashIndex[0]) {
                    monthString = monthString + date.charAt(i);
                }
                if ( i < backslashIndex[1] && i> backslashIndex[0]){
                    dayString = dayString + date.charAt(i);
                }
                if (i > backslashIndex[1]){
                    yearString = yearString + date.charAt(i);
                }
        }
            this.year = Integer.parseInt(yearString);
            this.month = Integer.parseInt(monthString);
            this.day = Integer.parseInt(dayString);
    }


    public Date() { //create an object with today’s date (see Calendar class)
            Calendar c=Calendar.getInstance();
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH)+1;
            day=c.get(Calendar.DATE);
    }
    public boolean isValid() {
        if(month == FEB){
            if(isLeapYear(this.year)){
                if(day > 29) return false;
            }
            else if(day > 28) return false;
        }
        if(month == JAN || month==MAR || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC){
            if(day > 31) return false;
        }
        if(month == APR || month==JUN || month == SEP || month == NOV){
            if(day > 30) return false;
        }

        return true;
    }
    private boolean isLeapYear(int year){       //this helper method checks if the year is a leap year of not
        if((this.year % QUADRENNIAL) == 0){
            if((this.year % CENTENNIAL) == 0){
                if((this.year % QUATERCENTENNIAL) == 0) {
                    return true;
                }
            }
            else return true;
        }
        return false;
    }

    @Override

    public int compareTo(Date date) {
        int out=-1;
        if(date.day == this.day && date.month == this.month && date.year == this.year) out = 0;

        if (this.year < date.year){
            out=1;
        }
        else{
            if(this.month < date.month){
                out=1;
            }
            else{
                if(this.day < date.day){
                    out=1;
                }
            }
        }
        return out;
    }

    @Override
    public String toString() {
        String output= this.month+"/"+this.day+"/"+this.year;
        return output;
    }

    public static void main(String[] args) {
        //Test Case#1, testing isValid with April 31
        Date date1_1= new Date("4/31/1995");
        System.out.println(date1_1.isValid()); //Expected:false

        //Test Case#2, testing isValid with Jan 60
        Date date2_1= new Date("1/60/1995");
        System.out.println(date2_1.isValid()); //Expected:false

        //Test Case#3, testing isValid with Feb 29 on non-leap year
        Date date3_1= new Date("2/29/2015");
        System.out.println(date3_1.isValid()); //Expected:false

        //Test Case#4, testing isValid with Feb 29 on leap year
        Date date4_1= new Date("2/29/2016");
        System.out.println(date4_1.isValid()); //Expected:true

        //Test Case#5, testing compareTo with same dates
        Date date5_1= new Date("1/8/2008");
        Date date5_2= new Date("1/8/2008");
        System.out.println(date5_1.compareTo(date5_2)); //expected = 0

        //Test Case#6, testing compareTo with same month and day
        Date date6_1= new Date("1/8/2008");
        Date date6_2= new Date("1/8/2009");
        System.out.println(date6_1.compareTo(date6_2)); //expected = 1

        //Test Case#7, testing compareTo with same month and day
        Date date7_1= new Date("1/8/2009");
        Date date7_2= new Date("1/8/2008");
        System.out.println(date7_1.compareTo(date7_2)); //expected = -1

        //Test Case#8, testing compareTo with same month and day
        Date date8_1= new Date("1/8/2008");
        Date date8_2= new Date("1/9/2008");
        System.out.println(date8_1.compareTo(date8_2)); //expected = 1

        //Test Case#9, testing compareTo with same month and day
        Date date9_1= new Date("1/9/2008");
        Date date9_2= new Date("1/8/2008");
        System.out.println(date9_1.compareTo(date9_2)); //expected = -1

        //Test Case#10, testing compareTo with same month and day
        Date date10_1= new Date("1/9/2008");
        Date date10_2= new Date("2/9/2008");
        System.out.println(date10_1.compareTo(date10_2)); //expected = 1

        //Test Case#11, testing compareTo with same month and day
        Date date11_1= new Date("2/9/2008");
        Date date11_2= new Date("1/9/2008");
        System.out.println(date11_1.compareTo(date11_2)); //expected = -1

    }

}

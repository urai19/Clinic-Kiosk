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
        if(day == date.day && month == date.month && year == date.year) return 1;
        else return 0;
    }


}

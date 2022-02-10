package clinic;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

public class Kiosk {
    private Schedule clinicSchedule;


    public void run(){
        Scanner reader= new Scanner(System.in);
        while(reader.nextLine().equals("Q")){
            String command= reader.nextLine();
            String delim= " ";
            StringTokenizer line= new StringTokenizer(command,delim);
            String code= line.nextToken();
            if(!(code.equals("B")) && !(code.equals("C")) && !(code.equals("CP")) && !(code.equals("P")) && !(code.equals("PZ")) && !(code.equals("PP")))
            {
                System.out.println("Invalid Command!");
            }
            else{ commandReader(command);}

        }
    }

    private void commandReader(String command){
        String delim= " ";
        StringTokenizer line= new StringTokenizer(command,delim);
        String code= line.nextToken();
        if(code.equals("B")) {
            String dob=line.nextToken();
            String fname= line.nextToken();
            String lname= line.nextToken();
            String date= line.nextToken();
            String delim_time=":";
            StringTokenizer timeTokens= new StringTokenizer(line.nextToken(),delim_time);
            int hours=Integer.parseInt(timeTokens.nextToken());
            int minutes=Integer.parseInt(timeTokens.nextToken());
            String location= line.nextToken();
            bookAppt(dob,fname,lname,date,hours,minutes,location);
        }
        if(code.equals("C")){
            String dob=line.nextToken();
            String fname= line.nextToken();
            String lname= line.nextToken();
            String date= line.nextToken();
            String delim_time=":";
            StringTokenizer timeTokens= new StringTokenizer(line.nextToken(),delim_time);
            int hours=Integer.parseInt(timeTokens.nextToken());
            int minutes=Integer.parseInt(timeTokens.nextToken());
            String location= line.nextToken();
        }

    }

    private void bookAppt(String dob, String fname, String lname, String date, int hours, int minutes, String location){
        Date birth= new Date(dob);
        Patient person= new Patient(fname,lname,birth);
        Date aptDate= new Date(date);
        Time time= new Time(hours,minutes);
        Location loc= getLocation(location);
        Date today= new Date();
        if(birth.compareTo(today)<=0) {
            System.out.println("Invalid date of birth -> it is a future date");
            return;
        }
        if(loc==null){
            System.out.println("Invalid location!");
            return;
        }
        if(aptDate.compareTo(today)>=0){
            System.out.println("Appointment Date invalid -> must be a future date");
            return;
        }
        if(!aptDate.isValid()){
            System.out.println("Invalid Appointment Date");
            return;
        }
        if(!time.isValid()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval");
            return;
        }
        Timeslot aptSlot= new Timeslot(aptDate,time);
        Appointment book_appointment= new Appointment(person,aptSlot,loc);
        for(Appointment a: clinicSchedule.getAppointments()){
            if(a.equals(book_appointment)){
                System.out.println();
                return;
            }
            if(a.getTimeslot().compareTo(aptSlot)==0 && a.getLocation().equals(loc)){
                System.out.println();
                return;
            }
            if(a.getPatient().compareTo(person)==0 && a.getTimeslot().getDate().compareTo(aptDate)==0){
                System.out.println();
                return;
            }
        }
        boolean add= clinicSchedule.add(book_appointment);
    }
    private void cancelAppt(String dob, String fname, String lname, String date, int hours, int minutes, String location){
        Date birth= new Date(dob);
        Patient person= new Patient(fname,lname,birth);
        Date aptDate= new Date(date);
        Time time= new Time(hours,minutes);
        Location loc= getLocation(location);
        Timeslot aptSlot= new Timeslot(aptDate,time);
        Appointment cancel= new Appointment(person,aptSlot,loc);
        boolean isRemove=true;
        for(Appointment a: clinicSchedule.getAppointments()){
            if(a.equals(cancel)){
                isRemove= clinicSchedule.remove(a);
            }
        }
        if(!isRemove) System.out.println("Not cancelled");
    }
    private Location getLocation(String location){
        if(location.equalsIgnoreCase("Union")) return Location.UNION;
        if(location.equalsIgnoreCase("Morris")) return Location.MORRIS;
        if(location.equalsIgnoreCase("Mercer")) return Location.MERCER;
        if(location.equalsIgnoreCase("Middlesex")) return Location.MIDDLESEX;
        if(location.equalsIgnoreCase("Somerset")) return Location.SOMERSET;
        else return null;
    }


}

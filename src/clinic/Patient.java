/**
 Patient class creates an object that holds the first name, last name, and date of Birth (DOB) of a patient.
 @author Garvit Gupta, Udayan Rai
 */
package clinic;
import java.util.Calendar;

public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     Constructor for the Schedule class.
     @param fname the first name of the patient.
     @param lname the last name of the patient.
     @param dob the date of birth of the patient.
     */
    public Patient(String fname, String lname, Date dob){
        this.fname=fname;
        this.lname=lname;
        this.dob=dob;
    }
    /**
     Get method which returns the date of birth of the patient
     @return dob.
     */
    public Date getDob(){
        return dob;
    }

    /**
     Converts the information of the patient into a String which is readable by Kiosk user.
     Lists the first name first, then last name, and then DOB of the patient.
     @return output
     */
    @Override
    public String toString(){
        String output = fname + " "+ lname + ", DOB: "+dob;
        return output;
    }
    /**
     Compares two patients and decide if they are equal or rank that one patient is greater than another.
     Rank by last names, then first names, and then DOBs.
     @param patient the name of the person to delete.
     @return 0 if equal, 1 or -1 otherwise
     */
    @Override
    public int compareTo(Patient patient){
        int output=0;
        if (this.fname.equals(patient.fname)  && this.lname.equals(patient.lname) && (this.dob).compareTo(patient.dob)==0){
            return 0;
        }

        if(this.lname.compareTo(patient.lname)>0){ //if patient.lname comes before
            output=-1; //return -1 becuase patient.lname comes before this.lname
        }
        else if(this.lname.equals(patient.lname)){ //last names equal
            if(this.fname.compareTo(patient.fname)>0){ //this.fname comes after patient.fname
                output=-1;
            }
            else if(this.fname.compareTo(patient.fname)<0){ //this.fname comes before
                output=1;
            }
            else{   //comparing dob's because first and last names are same
                if((this.dob).compareTo(patient.dob)==1) output=1;
                else output=-1;
            }
        }
        else if (this.lname.compareTo(patient.lname) < 0){ //if patient.lname comes after this.lname
            output=1;
        }
        return output;
    }

    public static void main(String[] args) {
        //test case #1, testing the toString and the format of how the user sees the patient info
        String fname= "Neel";
        String lname= "Shah";
        Date dob= new Date("2/14/2002");
        Patient patient1_1= new Patient(fname,lname,dob);
        System.out.println(patient1_1.toString());

        //test case #2, testing the compareto with different last names.
        Patient patient2_1 = new Patient("Garvit", "Gupta", new Date("9/19/2002"));
        Patient patient2_2 = new Patient("Garvit", "Rai", new Date("9/19/2002"));
        int result_2 = patient2_1.compareTo(patient2_2);
        System.out.println(result_2); //Expected =1

        //test case #3, testing the compareTo with different last names.
        Patient patient3_1 = new Patient("Garvit", "Rai", new Date("9/19/2002"));
        Patient patient3_2 = new Patient("Garvit", "Gupta", new Date("9/19/2002"));
        int result_3 = patient3_1.compareTo(patient3_2);
        System.out.println(result_3); //Expected =-1

        //test case #4, testing the compareTo with same last names but different first names.
        Patient patient4_1 = new Patient("Garvit", "Rai", new Date("9/19/2002"));
        Patient patient4_2 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        int result_4 = patient3_1.compareTo(patient4_2);
        System.out.println(result_4); //Expected =1

        //test case #5, testing the compareTo with same last names but different first names.
        Patient patient5_1 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        Patient patient5_2 = new Patient("Garvit", "Rai", new Date("9/19/2002"));
        int result_5 = patient5_1.compareTo(patient5_2);
        System.out.println(result_5); //Expected =-1

        //test case #6, testing the compareTo with same first and last names but different dob.
        Patient patient6_1 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        Patient patient6_2 = new Patient("Udayan", "Rai", new Date("9/20/2002"));
        int result_6 = patient6_1.compareTo(patient6_2);
        System.out.println(result_6); //Expected =-1

        //test case #7, testing the compareTo with same first and last names but different dob.
        Patient patient7_1 = new Patient("Udayan", "Rai", new Date("9/20/2002"));
        Patient patient7_2 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        int result_7 = patient7_1.compareTo(patient7_2);
        System.out.println(result_7); //Expected =1

        //test case #8, testing the compareTo with same patient records(fname,lname and dob).
        Patient patient8_1 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        Patient patient8_2 = new Patient("Udayan", "Rai", new Date("9/19/2002"));
        int result_8 = patient8_1.compareTo(patient8_2);
        System.out.println(result_8); //Expected =0


    }
}

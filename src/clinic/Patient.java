package clinic;

public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    public Patient(String fname, String lname, Date dob){
        this.fname=fname;
        this.lname=lname;
        this.dob=dob;
    }

    @Override
    public String toString(){
        String output = fname + " "+ lname + " " + dob;
        return output;
    }

    @Override
    public int compareTo(Patient patient){
        if (this.fname == patient.fname  && this.lname == patient.lname && this.dob == patient.dob){
            return 1;
        }else{
            return 0;
        }
    }

    public static void main(String[] args) {
        //test case #1, testing the toString and the format of how the user sees the patient info
        String fname= "Dhruv";
        String lname= "Patel";
        Date dob= new Date("2/14/2002");
        Patient patient= new Patient(fname,lname,dob);
        System.out.println(patient.toString());

    }
}

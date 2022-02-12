/**
 Kiosk class builds the user interface where one can process appointment transactions.
 Interactive program where the user enters commands through the console and the output is displayed on the console.
 @author Garvit Gupta, Udayan Rai
 */
package clinic;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

public class Kiosk {
    private Schedule clinicSchedule;

    /**
     Constructor for the Kiosk class.
     An instance of schedule class is initialized with an array of appointments of size 1 and
     number of appointments is set to 0.
     */
    public Kiosk() {
        Appointment[] appointments = new Appointment[1];
        int num = 0;
        clinicSchedule = new Schedule(appointments, num);
    }
    /**
     Method to start a kiosk session.
     Includes a while loop to read user commands from the console.
     */
    public void run() {
        System.out.println("Kiosk running. Ready to process transactions.");
        System.out.println();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        while (!input.equals("Q")) {
            if (input.equals("P")) clinicSchedule.print();
            if (input.equals("PZ")) clinicSchedule.printByZip();
            if (input.equals("PP")) clinicSchedule.printByPatient();
            String delim = " ";
            StringTokenizer line = new StringTokenizer(input, delim);
            String code = line.nextToken();
            if (!(code.equals("B")) && !(code.equals("C")) && !(code.equals("CP")) && !(code.equals("P")) && !(code.equals("PZ")) && !(code.equals("PP"))) {
                System.out.println("Invalid Command!");
            } else {
                commandReader(input);
            }
            input = reader.nextLine();
        }
        System.out.println("Kiosk session ended.");
    }
    /**
     Method to process all the possible valid commands given by the user.
     @param command The command given by the user.
     */
    private void commandReader(String command) {
        String delim = " ";
        StringTokenizer line = new StringTokenizer(command, delim);
        String code = line.nextToken();
        if (code.equals("B") || (code.equals("C"))) {
            String dob = line.nextToken();
            String fname = line.nextToken();
            String lname = line.nextToken();
            String date = line.nextToken();
            String delim_time = ":";
            StringTokenizer timeTokens = new StringTokenizer(line.nextToken(), delim_time);
            int hours = Integer.parseInt(timeTokens.nextToken());
            int minutes = Integer.parseInt(timeTokens.nextToken());
            String location = line.nextToken();
            if (code.equals("B")) bookAppt(dob, fname, lname, date, hours, minutes, location);
            if (code.equals("C")) cancelAppt(dob, fname, lname, date, hours, minutes, location);
        }
        if (code.equals("CP")) {
            String dob = line.nextToken();
            String fname = line.nextToken();
            String lname = line.nextToken();
            cancelPatient(dob, fname, lname);
        }
        if (code.equals("P")) {
            System.out.println();
            System.out.println("*list of appointments in the schedule.");
            clinicSchedule.print();
            System.out.println("*end of schedule*");
            System.out.println();
        }
        if (code.equals("PZ")) {
            System.out.println();
            System.out.println("*list of appointments by zip and time slot.");
            clinicSchedule.printByZip();
            System.out.println("*end of schedule*");
            System.out.println();
        }
        if (code.equals("PP")) {
            System.out.println();
            System.out.println("*list of appointments by patient.");
            clinicSchedule.printByPatient();
            System.out.println("*end of schedule*");
            System.out.println();
        }

    }
    /**
      Helper method to add the given appointment to the schedule.
     Processes the "B" command.
     @param dob Date of birth of the patient
     @param fname First name of the patient
     @param lname Last name of the patient
     @param date Date of the appointment
     @param hours Hour of the appointment
     @param minutes Minutes of the appointment
     @param location Location of the appointment
     */
    private void bookAppt(String dob, String fname, String lname, String date, int hours, int minutes, String location) {
        Date birth = new Date(dob);
        Patient person = new Patient(fname, lname, birth);
        Date aptDate = new Date(date);
        Time time = new Time(hours, minutes);
        Location loc = getLocation(location);
        Date today = new Date();
        Date one_year_later = new Date(1);
        if (birth.compareTo(today) <= 0) {
            System.out.println("Date of birth invalid -> it is a future date.");
            return;
        }
        if (!birth.isValid()) {
            System.out.println("Invalid date of birth!");
            return;
        }
        if (loc == null) {
            System.out.println("Invalid location!");
            return;
        }
        if (aptDate.compareTo(today) >= 0) {
            System.out.println("Appointment Date invalid -> must be a future date.");
            return;
        }
        if (!aptDate.isValid() || aptDate.compareTo(one_year_later) <= 0) {
            System.out.println("Invalid Appointment date!");
            return;
        }
        if (!time.isValid()) {
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
            return;
        }
        Timeslot aptSlot = new Timeslot(aptDate, time);
        Appointment book_appointment = new Appointment(person, aptSlot, loc);
        boolean isValid = isValidAppt(book_appointment);
        if (isValid) {
            clinicSchedule.add(book_appointment);
            System.out.println("Appointment booked and added to schedule.");
        }

    }
    /**
     Helper method that makes sure that the appointment
     that is to be booked is valid and does not clash with any other appointments.
     @param book_appointment Appointment given that is to be checked and potentially booked.
     @return true if the given appointment is valid.
     */
    private boolean isValidAppt(Appointment book_appointment) {
        for (Appointment a : clinicSchedule.getAppointments()) {
            if (a != null && a.equals(book_appointment)) {
                System.out.println("Same appointment exists in the schedule.");
                return false;
            }
            if (a != null && a.getTimeslot().compareTo(book_appointment.getTimeslot()) == 0 && a.getLocation().equals(book_appointment.getLocation())) {
                System.out.println("Timeslot has been taken at this location.");
                return false;
            }
            if (a != null && a.getPatient().compareTo(book_appointment.getPatient()) == 0 && a.getTimeslot().getDate().compareTo(book_appointment.getTimeslot().getDate()) == 0) {
                System.out.println("Same patient cannot book an appointment with the same date.");
                return false;
            }
        }
        return true;
    }
    /**
     Method that processes the "C" command.
     Cancels a given appointment.
     @param dob Date of birth of the patient
     @param fname First name of the patient
     @param lname Last name of the patient
     @param date Date of the appointment
     @param hours Hour of the appointment
     @param minutes Minutes of the appointment
     @param location Location of the appointment
     */
    private void cancelAppt(String dob, String fname, String lname, String date, int hours, int minutes, String location) {
        Date birth = new Date(dob);
        Patient person = new Patient(fname, lname, birth);
        Date aptDate = new Date(date);
        Time time = new Time(hours, minutes);
        Location loc = getLocation(location);
        Timeslot aptSlot = new Timeslot(aptDate, time);
        Appointment cancel = new Appointment(person, aptSlot, loc);
        boolean isRemove = false;
        for (Appointment a : clinicSchedule.getAppointments()) {
            if (a != null && a.equals(cancel)) {
                isRemove = clinicSchedule.remove(a);
            }
        }
        if (!isRemove) System.out.println("Not cancelled, appointment does not exist.");
        else System.out.println("Appointment cancelled.");
        ;
    }
    /**
     Method that cancels all appointments of a given patient.
     Processes the "CP" command.
     @param dob Date of birth of the patient.
     @param fname First name of the patient.
     @param lname Last name of the patient.
     @return true if person was deleted, false otherwise.
     */
    private void cancelPatient(String dob, String fname, String lname) {
        Patient person = new Patient(fname, lname, new Date(dob));
        boolean isRemove = true;
        Appointment[] apptArr = clinicSchedule.getAppointments();
        for (int i = 0; i < apptArr.length; i++) {
            if (apptArr[i] != null && apptArr[i].getPatient().compareTo(person) == 0)
                isRemove = clinicSchedule.remove(apptArr[i]);
        }
        if (!isRemove) System.out.println("Not cancelled.");
        else
            System.out.println("All appointments for " + fname + " " + lname + ", DOB: " + dob + " have been cancelled.");
    }
    /**
     Helper method that returns the correct enum Location.
     @param location the name of the location(case-insensitive).
     @return Location regardless of case-insensitive input.
     */
    private Location getLocation(String location) {
        if (location.equalsIgnoreCase("Union")) return Location.UNION;
        if (location.equalsIgnoreCase("Morris")) return Location.MORRIS;
        if (location.equalsIgnoreCase("Mercer")) return Location.MERCER;
        if (location.equalsIgnoreCase("Middlesex")) return Location.MIDDLESEX;
        if (location.equalsIgnoreCase("Somerset")) return Location.SOMERSET;
        else return null;
    }

}

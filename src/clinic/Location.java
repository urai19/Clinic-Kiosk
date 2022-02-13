/**
 Location class creates an object that holds the zipcode and the city of the clinic's location.
 There are only five possible values for the location (SOMERSET, MIDDLESEX, MERCER, MORRIS, UNION).
 @author Garvit Gupta, Udayan Rai
 */
package clinic;

public enum Location {
    SOMERSET("08807", "Bridgewater"),
    MIDDLESEX("08854", "Piscataway"),
    MERCER("08542", "Princeton"),
    MORRIS("07960", "Morristown"),
    UNION("07083", "Union");

    private final String zipcode;
    private final String city;

    /**
     Constructor for the location class.
     @param zipcode the zipcode of the clinic's location.
     @param city the city of the clinic's location.
     */
    Location(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }
    /**
     Converts the information of the location into a String which is readable by Kiosk user.
     @return output
     */
    @Override
    public String toString() {
        String output = this.city + " " + this.zipcode;
        return output;
    }
}

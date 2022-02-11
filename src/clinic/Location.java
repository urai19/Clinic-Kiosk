package clinic;

public enum Location {
    SOMERSET("08807", "Bridgewater"),
    MIDDLESEX("08854", "Piscataway"),
    MERCER("08542", "Princeton"),
    MORRIS("07960", "Morristown"),
    UNION("07083", "Union");

    private final String zipcode;
    private final String city;

    Location(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }


    @Override
    public String toString() {
        String output = this.city + " " + this.zipcode;
        return output;
    }

}

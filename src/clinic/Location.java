package clinic;

public enum Location {
    BRIDGEWATER ("08807", "Somerset County"),
    PISCATAWAY ("08854", "Middlesex County"),
    PRINCETON ("08542", "Mercer County"),
    MORRISTOWN ("07960", "Morris County"),
    UNION ("07083", "Union County");

    private final String zipcode;
    private final String county;

    Location(String zipcode, String county) {
        this.zipcode = zipcode;
        this.county = county;
    }
}

package quiz.com.player;

public class Address {
    private int houseNumber;
    private String street;
    private String town;
    private String county;
    private String postcode;


    public Address(int houseNumber, String street, String town, String county, String postcode) {
        this.postcode = postcode;
        this.town = town;
        this.county = county;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return houseNumber + " "
                + street + ",\n" + town +
                ",\n" + county
                + ",\n" + postcode + ".";
    }
}

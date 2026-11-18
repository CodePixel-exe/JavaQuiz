package quiz.com.player;

import java.time.LocalDate;
import java.time.Period;

public class Person {


    private String firstName;
    private String surname;


    private LocalDate dateOfBirth;
    private Address address;

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }


    public void setDateOfBirth(String dateOfBirth) throws Exception{
        if (!dateOfBirth.isEmpty()){
            String [] data = dateOfBirth.split("-");
            if (data.length == 3){
                this.dateOfBirth = LocalDate.of(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Integer.parseInt((data[2])));
            } else {
                throw new Exception("Date of birth was supplied in the wrong format.");
            }
        }else{
            this.dateOfBirth = LocalDate.now();
        }

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(int houseNumber, String street, String town, String county, String postcode) {
        this.address = new Address(houseNumber,street, town, county, postcode);
    }

    public int getAge(){
        Period difference = Period.between(dateOfBirth, LocalDate.now());
        return difference.getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return firstName + " " + surname;
    }

    public String getFirstName() {
        return firstName;
    }
}

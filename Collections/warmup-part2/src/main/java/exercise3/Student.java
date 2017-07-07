package exercise3;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class Student {
    private String lastName;
    private String firstName;


    @Override
    public String toString() {
        return lastName + " " +
                firstName;
    }

    public Student() {
        this.lastName = "";
        this.firstName = "";
    }

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

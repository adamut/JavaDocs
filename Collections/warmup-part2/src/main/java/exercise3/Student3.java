package exercise3;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class Student3 extends Student {
    public Student3() {
    }

    public Student3(String lastName, String firstName) {
        super(lastName, firstName);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (obj.getClass() != getClass())
            return false;

        Student3 student3 = (Student3) obj;
        if (!student3.getFirstName().equals(getFirstName())) return false;
        return true;
    }
}

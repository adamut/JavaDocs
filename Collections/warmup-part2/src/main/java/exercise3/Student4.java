package exercise3;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class Student4 extends Student{
    public Student4() {
    }

    public Student4(String lastName, String firstName) {
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

        Student4 student4 = (Student4) obj;
        if (!student4.getFirstName().equals(getFirstName())) return false;
        if (!student4.getLastName().equals(getLastName())) return false;
        return true;
    }
}

package exercise3;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class Student1 extends Student {

    public Student1() {
    }

    public Student1(String lastName, String firstName) {
        super(lastName, firstName);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.getFirstName().hashCode();
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

        Student1 student1 = (Student1) obj;
        if (!student1.getFirstName().equals(getFirstName())) return false;
        return true;
    }
}

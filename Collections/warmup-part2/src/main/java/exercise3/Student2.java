package exercise3;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class Student2 extends Student {
    public Student2() {
    }

    public Student2(String lastName, String firstName) {
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

        Student2 student2 = (Student2) obj;
        if (!student2.getFirstName().equals(getFirstName())) return false;
        if (!student2.getLastName().equals(getLastName())) return false;
        return true;
    }
}

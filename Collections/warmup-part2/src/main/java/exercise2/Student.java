package exercise2;

/**
 * Created by Radu.Hoaghe on 20.04.2015.
 */
public class Student {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Double averageGrade;

    public Student(Integer id, String firstName, String lastName, Double averageGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageGrade = averageGrade;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    // TOD Exercise 2 a) Override the toString() method
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    // TOD Exercise 2 c) Override the equals() method
    @Override
    public boolean equals(Object o) {
        // TOD Exercise 2 c1) Check if the current instance is the same instance as the one from Object o
        if (this == o)
            return true;
        // TO Exercise 2 c2) Check if Object o is null
        if (o == null)
            return false;
        // TOD Exercise 2 c3) Check if Object o class type is the same as the current instance's type
        if (o.getClass() != this.getClass())
            return false;
        // TO Exercise 2 c4) Now you know for sure that the Object o is of type Student so you
        // TO need to cast it to a Student type object
        Student student = (Student) o;
        // TOD Exercise 2 c5) Check if all the fields from Student class are equal to the ones from
        // TOD Exercise 2 c5) Object o (id, lastName, firstName, averageGrade)
        if (!getLastName().equals(student.getLastName())) return false;
        if (!getFirstName().equals(student.getFirstName())) return false;
        if (!this.getId().equals(student.getId())) return false;
        if (!this.getAverageGrade().equals(student.getAverageGrade())) return false;

        return true;

        // TO Exercise 2 d) After you finished implementing equals method go to TO Exercise 2 e) from Exercise2 class
    }

    // TOD Exercise 2 g) Override the hashCode() method
    // TOD Exercise 2 g) Hint: Don't forget to include in the hashCode result all the fields from
    // TOD Exercise 2 g) the Student class
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getId();
        result = 31 * result + getAverageGrade().hashCode();
        return result;
        // TO Exercise 2 h) After you finished implementing hashCode go to TO Exercise 2 i) from Exercise2 class
    }

}

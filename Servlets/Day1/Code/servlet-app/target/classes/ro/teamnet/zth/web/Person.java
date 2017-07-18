package ro.teamnet.zth.web;

/**
 * Created by Alexandru.Negoita on 7/18/2017.
 */
public class Person implements Comparable {
    public String firtName;
    public String lastName;
    public long age;
    public boolean married;

    public Person(String s, String s1, String s2) {
    }

    public Person(String firtName, String lastName, long age, boolean married) {
        this.firtName = firtName;
        this.lastName = lastName;
        this.age = age;
        this.married = married;
    }

    @Override
    public int compareTo(Object o) {
        Person x = (Person) o;
        if (this.age <= x.age)
            return -1;
        else if (this.age >= x.age)
            return 1;
        return 0;
    }
}

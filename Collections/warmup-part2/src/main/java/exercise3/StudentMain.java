package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Cosmin.Adamut on 7/7/2017.
 */
public class StudentMain {
    public static void main(String[] args) {
        Map<Student1, BigDecimal> stud1 = new HashMap<Student1, BigDecimal>();
        Map<Student2, BigDecimal> stud2 = new HashMap<Student2, BigDecimal>();
        Map<Student3, BigDecimal> stud3 = new HashMap<Student3, BigDecimal>();
        Map<Student4, BigDecimal> stud4 = new HashMap<Student4, BigDecimal>();

        stud1.put(new Student1("Ana","Maria"), BigDecimal.valueOf(9));
        stud1.put(new Student1("Nicu","Maria"), BigDecimal.valueOf(10));
        stud1.put(new Student1("Petre","Nicu"), BigDecimal.valueOf(6));
        stud1.put(new Student1("Ion","Alin"), BigDecimal.valueOf(8));

        stud2.put(new Student2("Ana","Maria"), BigDecimal.valueOf(9));
        stud2.put(new Student2("Ana","Maria"), BigDecimal.valueOf(10));
        stud2.put(new Student2("Petre","Nicu"), BigDecimal.valueOf(6));
        stud2.put(new Student2("Ion","Alin"), BigDecimal.valueOf(8));

        stud3.put(new Student3("Ana","Maria"), BigDecimal.valueOf(9));
        stud3.put(new Student3("Nicu","Pusca"), BigDecimal.valueOf(10));
        stud3.put(new Student3("Petre","Nicu"), BigDecimal.valueOf(6));
        stud3.put(new Student3("Ion","Alin"), BigDecimal.valueOf(8));

        stud4.put(new Student4("Ana","Maria"), BigDecimal.valueOf(9));
        stud4.put(new Student4("Nicu","Pusca"), BigDecimal.valueOf(10));
        stud4.put(new Student4("Petre","Nicu"), BigDecimal.valueOf(6));
        stud4.put(new Student4("Ion","Alin"), BigDecimal.valueOf(8));

        Iterator<Map.Entry<Student1, BigDecimal>> entries = stud1.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Student1, BigDecimal> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println("");
        Iterator<Map.Entry<Student2, BigDecimal>> entries2 = stud2.entrySet().iterator();
        while (entries2.hasNext()) {
            Map.Entry<Student2, BigDecimal> entry2 = entries2.next();
            System.out.println("Key = " + entry2.getKey() + ", Value = " + entry2.getValue());
        }
        //TODO aici ar mai trebui sa testam pentru fiecare map cum inseram pentru ca avem equals si hashCode diferite, iar rezultatele pot avea un impact

    }
}

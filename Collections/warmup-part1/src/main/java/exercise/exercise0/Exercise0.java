package exercise.exercise0;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TOD Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        List<String> list = new ArrayList<String>();
                list.add("Ana");
                list.add("Nicu");
                list.add("Costel");
                list.add("Iancu");
        // TOD Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)
        ListIterator<String> iterator = list.listIterator();

        // TOD Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        System.out.println("\nex #0b");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("\nex #0c");

        // TOD Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for(int i=0; i<list.size();i++)
            System.out.println(list.get(i));
        // TOD Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        System.out.println("\nex #0d");
        for(String i:list){
            System.out.println(i);
        }

    }

    public static void main(String[] args) {
        // TO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 ex0= new Exercise0();
        ex0.iterateThroughList();
    }
}

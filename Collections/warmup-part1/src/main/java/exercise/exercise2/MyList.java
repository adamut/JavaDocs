package exercise.exercise2;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 20.04.2015.
 * <p>
 * Exercise 2: Create a class that inherits ArrayList<Integer> (class MyList<Integer>).
 * <p>
 * This list (MyList) should have the following functionality, besides the functionality
 * that ArrayList already offers: it should retain in every moment how many different
 * elements exist in the list.
 * <p>
 * Examples: 1. If you have a List that contains: 7 5 3 2 4 1, there are 6 different elements.
 * 2. If you have a List that contains: 5 6 1 2 5 6, there are 4 different elements.
 * <p>
 * A variable that retains the number of different elements that exist in the list in
 * every moment was already defined (differentElements).
 * <p>
 * First of all, you will need to override the add methods so that every time a different
 * element is added the counter will be updated.
 * Hint : check out the List documentation to see the methods signatures.
 * <p>
 * Secondly, you will also need to override the remove methods (Hint: of course, the List
 * documentation) because the number of different elements in the list could change if
 * the last element of its kind in the list is removed and by not overrriding it the
 * counter will remain unchanged.
 * <p>
 * Finally, you will need to override the clear method and create a getter method for the
 * counter (in order to access it outside the class).
 * <p>
 * In order to add/remove/clear the elements into/from the list you will need to use the
 * add/remove/clear methods inherited from ArrayList.
 * <p>
 * To test your implementation run the Exercise2Test class.
 */
public class MyList<Integer> extends ArrayList<Integer> {

    // A counter to hold the number of adds that were made on the list
    private int differentElements;

    public MyList() {
        differentElements = 0;
    }

    @Override
    public boolean add(Integer integer) {
        ListIterator<Integer> listIterator = this.listIterator();
        Integer value;
        List<Integer> newList = new ArrayList<Integer>();
        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        if (!newList.contains(integer))
            newList.add(integer);
        this.differentElements = newList.size();


        return super.add((Integer) integer);
    }

    @Override
    public void add(int index, Integer element) {

        ListIterator<Integer> listIterator = this.listIterator();
        Integer value;
        List<Integer> newList = new ArrayList<Integer>();
        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        if (!newList.contains(element))
            newList.add(element);
        this.differentElements = newList.size();
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        ListIterator<Integer> listIterator = this.listIterator();
        Integer value;
        List<Integer> newList = new ArrayList<Integer>();
        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        Iterator<? extends Integer> iterator = c.iterator();

        while (iterator.hasNext()) {
            value = iterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        this.differentElements = newList.size();
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        ListIterator<Integer> listIterator = this.listIterator();
        Integer value;
        List<Integer> newList = new ArrayList<Integer>();
        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        Iterator<? extends Integer> iterator = c.iterator();

        while (iterator.hasNext()) {
            value = iterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }
        this.differentElements = newList.size();

        return super.addAll(index, c);
    }
// TO Exercise #2 a) Override add() and addAll() methods so that the list should retain the number of
    // TO Exercise #2 a) different elements (Hint: check out the methods signatures on the List documentation)

    // TOD Exercise #2 b) Override the remove methods so that the number of different elements is updated when
    // TOD Exercise #2 b) an element is removed
    // TOD Exercise #2 b) hint: you need to update the number of different elements only when
    // TOD Exercise #2 b) the element that needs to be removed is the last element of its kind in the list

    @Override
    public Integer remove(int index) {
        Integer oldValue = super.remove(index);
        ListIterator<Integer> listIterator = this.listIterator();

        Integer value;
        List<Integer> newList = new ArrayList<Integer>();

        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }

        this.differentElements = newList.size();

        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        boolean oldValue = super.remove(o);
        ListIterator<Integer> listIterator = this.listIterator();

        Integer value;
        List<Integer> newList = new ArrayList<Integer>();

        while (listIterator.hasNext()) {
            value = listIterator.next();
            if (!newList.contains(value))
                newList.add(value);
        }

        this.differentElements = newList.size();

        return oldValue;

    }

// TO Exercise #2 c) Override the clear method and reset the number of different elements


    @Override
    public void clear() {
        this.differentElements = 0;
        super.clear();
    }

    // TO Exercise #2 d) Return the number of different elements that exist into the list
    public int getDifferentElements() {
        return differentElements;
    }
}

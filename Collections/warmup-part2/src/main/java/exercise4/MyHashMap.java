package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 * <p>
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {
        buckets = new ArrayList<LinkedList<MyEntry>>(BUCKET_ARRAY_SIZE);
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> linkedList = new LinkedList<MyEntry>();
            buckets.add(linkedList);
        }
        // TO Initialize buckets list

    }

    public String get(String key) {
        // TO
        int index;
        if (key == null)
            index = Math.abs(0 % BUCKET_ARRAY_SIZE);
        else
            index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);

        for (int j = 0; j < buckets.get(index).size(); j++) {
            MyEntry currentValue = buckets.get(index).get(j);
            if (currentValue != null && currentValue.getKey().equals(key)) {
                return currentValue.getValue();
            }
        }

        return null;
    }

    public void put(String key, String value) {
        // TO
        int index;
        if (key == null)
            index = Math.abs(0 % BUCKET_ARRAY_SIZE);
        else
            index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        boolean keyChanged = false;
        for (int i = 0; i < buckets.get(index).size(); i++) {
            String currentKey = buckets.get(index).get(i).getKey();
            if (currentKey != null && currentKey.equals(key)) {
                buckets.get(index).get(i).setValue(value);
                keyChanged = true;
            }
        }
        if (keyChanged == false)
            buckets.get(index).add(new MyEntry(key, value));
    }

    public Set<String> keySet() {
        // TO
        Set<String> keys = new HashSet<String>();
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++)
                keys.add(buckets.get(i).get(j).getKey());
        return keys;
    }

    public Collection<String> values() {
        // TO
        Collection<String> collection = new LinkedList<String>();
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++)
                collection.add(buckets.get(i).get(j).getValue());

        return collection;
    }

    public String remove(String key) {

        Iterator<LinkedList<MyEntry>> iterator = buckets.iterator();
        String removedKeyValue=null;
        if (key == null) {
            removedKeyValue=  buckets.get(0).iterator().next().getValue();
            buckets.get(0).iterator().remove();


        }
        while (iterator.hasNext()) {
            Iterator<MyEntry> myEntryIterator = iterator.next().iterator();
            while (myEntryIterator.hasNext()) {
                MyEntry currentEntry = myEntryIterator.next();
                if (currentEntry.getKey().equals(key)) {
                    removedKeyValue = currentEntry.getValue();
                    myEntryIterator.remove();
                }
            }
        }

        // TO Returns the value associated with the key removed from the map or null if the key wasn't found
        return removedKeyValue;
    }

    public boolean containsKey(String key) {
        // TO

        int index;
        if (key == null)
            index = Math.abs(0 % BUCKET_ARRAY_SIZE);
        else
            index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        boolean keyFound = false;

        for (int i = 0; i < buckets.get(index).size(); i++) {
            if (buckets.get(index).get(i).getKey().equals(key)) {
                keyFound = true;
            }
        }
        return keyFound;
    }

    public boolean containsValue(String value) {
        // TO
        boolean valueFound = false;
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++)
                if (buckets.get(i).get(j).getValue().equals(value)) {
                    valueFound = true;
                }
        return valueFound;
    }

    public int size() {
        // TO Return the number of the Entry objects stored in all the buckets
        int listSize = 0;
        for (int i = 0; i < buckets.size(); i++)
            listSize += buckets.get(i).size();

        return listSize;
    }

    public void clear() {

        buckets.clear();

        // TO Remove all the Entry objects from the bucket list
    }

    public Set<MyEntry> entrySet() {
            Set<MyEntry> myEntries = new HashSet<MyEntry>();
            for(int i=0;i<buckets.size();i++)
                for(int j =0 ; j< buckets.get(i).size();j++)
                    myEntries.add(new MyEntry(buckets.get(i).get(j).getKey(),buckets.get(i).get(j).getValue()));
        // TODO Return a Set containing all the Entry objects
        return myEntries;
    }

    public boolean isEmpty() {
        // TO
        if (buckets.size() == 0)
            return true;
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

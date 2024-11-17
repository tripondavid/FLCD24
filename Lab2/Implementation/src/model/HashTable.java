package model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class HashTable {
    private final List<HashNode> array;
    private int capacity;
    private int size;

    public HashTable() {
        array = new ArrayList<>();
        capacity = 10;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Integer put(Object key, Integer value) {
        int index = hashCode(key);
        HashNode head = null;
        try {
            head = array.get(index);
        } catch (IndexOutOfBoundsException ignored) {
            while (capacity <= index)
                capacity *= 2;
            for (int i = array.size(); i < capacity; i++) {
                array.add(null);
            }
        }

        if (head == null) {
            array.set(index, new HashNode(key, value));
            return value;
        }

        while (head.next != null) {
            if (head.key.equals(key))
                return head.value;

            head = head.next;
        }

        if (head.key.equals(key))
            return head.value;


        head.next = new HashNode(key, value);
        size++;

        return value;
    }

    public Integer get(Object key) {
        int index = hashCode(key);

        HashNode head = array.get(index);

        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;
    }

    public SortedMap<Integer, String> getSortedNodes() {
        SortedMap<Integer, String> elements = new TreeMap<>();
        for (HashNode head : array) {
            if (head == null)
                continue;

            while (head != null) {
                elements.put(head.value, head.key.toString());
                head = head.next;
            }
        }

        return elements;
    }

    private Integer hashCode(Object key) {
        long sum = 0;
        String string = key.toString();
        for (int i = 0; i < string.length(); i++) {
            sum += string.charAt(i);
        }

        return (int) (sum % capacity);
    }

    @Override
    public String toString() {
        return "HashTable{" +
                ", array=" + array +
                ", size=" + size +
                '}';
    }
}

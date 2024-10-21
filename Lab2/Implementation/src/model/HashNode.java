package model;

public class HashNode {
    Object key;
    Integer value;
    HashNode next;

    public HashNode(Object key, Integer value) {
        this.key = key; // hashcode
        this.value = value;
    }

    @Override
    public String toString() {
        return "HashNode{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}
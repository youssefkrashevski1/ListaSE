package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class Node {
    public boolean id;
    private Node prev;
    private int value;
    private Kid data;
    private Node next;

    public Node(Kid data) {
        this.data = data;
    }

    public void remove() {
    }

    private static class KidNode {


        // Constructor
        public KidNode(String name, int age) {
            KidNode next = null;

        }
    }

}


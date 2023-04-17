package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class Node {
    private Kid data;
    private Node next;

    public Node(Kid data) {
        this.data = data;
    }

    private static class KidNode {
        private String name;
        private int age;
        private KidNode next;

        // Constructor
        public KidNode(String name, int age) {
            this.name = name;
            this.age = age;
            this.next = null;
        }
    }

}


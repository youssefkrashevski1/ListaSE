package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodeDE {
    public NodeDE prev;
    public int value;
    public Pet pet;
    public NodeDE head;
    private Pet data;
    private NodeDE next;
    private NodeDE previous;
    public NodeDE (Pet data) {this.data = data;}

    public NodeDE getHead() {
        NodeDE head = null;
        return null;
    }

}

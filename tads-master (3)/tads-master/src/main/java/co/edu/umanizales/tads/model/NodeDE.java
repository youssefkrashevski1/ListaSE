package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NodeDE {
    private Pet data;
    private NodeDE nextDE;
    private NodeDE previousDE;

    public NodeDE (Pet data){ this.data = data; }
}

package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE pets;
    public ListDEService(){
        pets = new ListDE();
    }

    public static void Concat() {
        ListDEService Pets = null;
        Pets.invert();
    }

    private void invert() {
    }


    public NodeDE getHead() {
        return null;
    }
}

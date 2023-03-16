package co.edu.umanizales.manage_store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class sale {
    private co.edu.umanizales.manage_store.model.store store;
    private co.edu.umanizales.manage_store.model.seller seller;
    private int quantity;
}

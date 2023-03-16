package co.edu.umanizales.manage_store.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BestStoreDTO {
    private co.edu.umanizales.manage_store.model.store store;
    private int quantity;
}

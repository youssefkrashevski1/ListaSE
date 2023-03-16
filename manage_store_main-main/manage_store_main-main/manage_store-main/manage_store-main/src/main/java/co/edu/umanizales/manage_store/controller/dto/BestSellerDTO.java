package co.edu.umanizales.manage_store.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BestSellerDTO {
    private co.edu.umanizales.manage_store.model.seller seller;
    private int quantity;
}

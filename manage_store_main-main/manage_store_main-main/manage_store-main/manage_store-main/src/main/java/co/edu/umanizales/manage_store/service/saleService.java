package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.BestSellerDTO;
import co.edu.umanizales.manage_store.controller.dto.BestStoreDTO;
import co.edu.umanizales.manage_store.model.sale;
import co.edu.umanizales.manage_store.model.seller;
import co.edu.umanizales.manage_store.model.store;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class saleService {
    private List<sale> sales;

    public saleService() {
        this.sales = new ArrayList<>();
    }

    public void addSale(sale sale){
        this.sales.add(sale);
    }

    public int getTotalSales(){
        int sum = 0;
        for (co.edu.umanizales.manage_store.model.sale sale:sales) {
            sum = sum + sale.getQuantity();
        }
        return sum;

    }
    public int getTotalSalesBySeller(String codeSeller){
            int sum = 0;
            for (co.edu.umanizales.manage_store.model.sale sale:sales) {
                if(sale.getSeller().getCode().equals(codeSeller)){
                sum = sum + sale.getQuantity();
                }
            }
            return sum;
    }
    public BestSellerDTO getBestSeller(List<seller> sellers){
        //Referencia como mayor
        BestSellerDTO bestSellerDTO = new BestSellerDTO(new seller(),0);
        //Recorremos todas las ventas
        for(co.edu.umanizales.manage_store.model.seller seller:sellers){
            int quant = getTotalSalesBySeller(seller.getCode());
            if (quant >= bestSellerDTO.getQuantity()){
                bestSellerDTO = new BestSellerDTO(seller, quant);
            }
        }
        return bestSellerDTO;
    }
    public int getTotalSalesByStore(String codeStore){
        int sum = 0;
        for (co.edu.umanizales.manage_store.model.sale sale:sales){
            if (sale.getStore().getCode().equals(codeStore)){
                sum = sum + sale.getQuantity();
            }
        }
        return sum;
    }
    public BestStoreDTO getBestStore(List<store> stores){
        //Referencia como mayor
        BestStoreDTO bestStoreDTO = new BestStoreDTO(new store("1","Armenia"),0);
        //Recorremos todas las ventas
        for(co.edu.umanizales.manage_store.model.store store:stores){
            int quant = getTotalSalesByStore(store.getCode());
            if (quant >= bestStoreDTO.getQuantity()){
                bestStoreDTO = new BestStoreDTO(store, quant);
            }
        }
        return bestStoreDTO;
    }
}

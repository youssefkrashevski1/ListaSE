package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.seller;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class sellerService {
    private List<seller> sellers;

    public sellerService() {this.sellers = new ArrayList<>();
    }

    public void addSeller(seller seller) {
        sellers.add(seller);
    }

    public seller getSellerById(String code)
    {
        for(co.edu.umanizales.manage_store.model.seller seller:sellers)
        {
            if(seller.getCode().equalsIgnoreCase(code))
            {
                return seller;
            }
        }
        return null;
    }
}

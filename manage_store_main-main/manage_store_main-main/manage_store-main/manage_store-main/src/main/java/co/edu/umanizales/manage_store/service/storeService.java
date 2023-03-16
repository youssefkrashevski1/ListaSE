package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.sale;
import co.edu.umanizales.manage_store.model.store;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class storeService {
    private List<store> stores;

    public storeService() {
        stores = new ArrayList<>();
        stores.add(new store("1","Armenia"));
        stores.add(new store("2","Pereira"));
    }

    public void addStore(store store) {
        stores.add(store);
    }

    public store getStoreById(String code)
    {
        for(co.edu.umanizales.manage_store.model.store store:stores)
        {
            if(store.getCode().equalsIgnoreCase(code))
            {
                return store;
            }
        }
        return null;
    }
    public storesWithMoreSalesThan( int number)
    { int storequantity = 0;
        for (store store:stores)
        {
            if (saleService.getTotalSalesByStore(store)>number)
            {
                storequantity = storequantity+1;
            }
        }
    }

}

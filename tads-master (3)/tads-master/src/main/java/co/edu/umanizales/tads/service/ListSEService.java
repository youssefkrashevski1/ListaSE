package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE Kids;

    public ListSEService() {
        this.Kids = new ListSE();
    }

    public void invert() throws ListSEException { this.Kids.invert(); }
} //end class ListSEService

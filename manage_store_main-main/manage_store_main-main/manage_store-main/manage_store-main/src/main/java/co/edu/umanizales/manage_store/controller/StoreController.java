package co.edu.umanizales.manage_store.controller;

import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.model.store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="store")
public class StoreController {
    @Autowired
    private co.edu.umanizales.manage_store.service.storeService storeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getStores()
    {

        return new ResponseEntity<>(new ResponseDTO(
                200,storeService.getStores(),null
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createStore(@RequestBody store store) {
        co.edu.umanizales.manage_store.model.store findStore = storeService.getStoreById(store.getCode());
        if( findStore == null) {
            storeService.addStore(store);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Tienda agregada", null
            ), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "Ya existe una tienda con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getStoreById(@PathVariable String code)
    {
        store findStore= storeService.getStoreById(code);
        if(findStore == null)
        {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No existe una tienda con ese código", null
            ), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, findStore, null
            ), HttpStatus.OK);
        }
    }
}

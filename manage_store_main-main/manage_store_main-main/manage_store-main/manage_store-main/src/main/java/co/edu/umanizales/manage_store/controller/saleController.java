package co.edu.umanizales.manage_store.controller;

import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.controller.dto.SaleDTO;
import co.edu.umanizales.manage_store.model.*;
import co.edu.umanizales.manage_store.service.SaleService;
import co.edu.umanizales.manage_store.service.SellerService;
import co.edu.umanizales.manage_store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "sale")
public class saleController {
    @Autowired
    private SaleService saleservice;
    @Autowired
    private SellerService saleService;
    @Autowired
    private StoreService storeService;

    public void addStoreAverage(Store store){this.storeList.add(store);}
    @GetMapping("/salesbystorebyaverage/{average}")
    public ResponseEntity<ResponseDTO> getSalesByStoreByAverage(@PathVariable int Average)
    {

        for (Sale sale: saleservice.getSales())
        {
            if (saleservice.getTotalSalesByStore(i.getStore().getCode()>Average){
                storeList.add(i.getStore());
        }
        return new ResponseEntity<>(new ResponseDTO(200,objects,null),HttpStatus.OK);




    }
    } @GetMapping("/storeswithmoresalesthan/{number}")
        public ResponseEntity<ResponseDTO>storesWithMoreSalesThan(@PathVariable int number)
    {
        return new ResponseEntity<>(new ResponseDTO(200,storeService.storesWithMoreSalesThan(number),null),HttpStatus.OK);
    }







    public ResponseEntity<ResponseDTO> getSales(){
        return new ResponseEntity<>(
                new ResponseDTO(200,
                        SaleService.getSales(),
                null),
                HttpStatus.OK);
    }
    @GetMapping(path="/total")
    public ResponseEntity<ResponseDTO> getTotalSales(){
        return new ResponseEntity<>(
                new ResponseDTO(200, saleService.getTotalSales(),null),HttpStatus.OK);
    }

    @GetMapping(path="/total/{code}")
    public ResponseEntity<ResponseDTO> getTotalSalesBySeller(
            @PathVariable String code
    ){
        return new ResponseEntity<>(
                new ResponseDTO(200, saleService.getTotalSales(),null),HttpStatus.OK);
    }
    @GetMapping(path="/totalStore/{code}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(
            @PathVariable String code
    ){
        return new ResponseEntity<>(
                new ResponseDTO(200, saleService.getTotalSales(),null),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createSale(@RequestBody
                                                  SaleDTO saleDTO){
        seller findSeller = sellerService.getSellerById(saleDTO.getSellerId());
        if( findSeller == null){
            return new ResponseEntity<>(new ResponseDTO(409,
                    "El vendedor ingresado no existe",null),
                    HttpStatus.BAD_REQUEST);
        }
        store findStore = storeService.getStoreById(saleDTO.getStoreId());
        if( findStore == null){
            return new ResponseEntity<>(new ResponseDTO(409,
                    "La sucursal ingresada no existe",null),
                    HttpStatus.BAD_REQUEST);
        }
        saleService.addSale(new sale(findStore,findSeller,
                saleDTO.getQuantity()));
        return new ResponseEntity<>(new ResponseDTO(200,
                "Venta adicionada",null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/bestSeller")
    public ResponseEntity<ResponseDTO> getBestSeller(){
        return new ResponseEntity<>( new ResponseDTO(
                200, (saleService.getBestSeller(sellerService.getSellers())),null),HttpStatus.OK);
    }
    @GetMapping(path = "/bestStore")
    public ResponseEntity<ResponseDTO> getBestStore(){
        return new ResponseEntity<>( new ResponseDTO(
                200, (saleService.getBestStore(storeService.getStores())),null),HttpStatus.OK);
    }
}

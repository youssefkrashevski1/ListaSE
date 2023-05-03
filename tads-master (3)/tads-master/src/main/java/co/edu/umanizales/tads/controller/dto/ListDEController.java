package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;
    

    public ListDEController(ListDEService listDEService) {
        this.listDEService = listDEService;
    }
}





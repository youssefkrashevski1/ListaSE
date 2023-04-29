package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.CityGenderReportDTO;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
       if(location == null){
           return new ResponseEntity<>(new ResponseDTO(
                   404,"La ubicación no existe",
                   null), HttpStatus.OK);
       }
       listSEService.getKids().add(
               new Kid(kidDTO.getIdentification(),
                       kidDTO.getName(), kidDTO.getAge(),
                       kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

        @GetMapping("/city-gender-report")
        public ResponseEntity<ResponseDTO<List<CityGenderReportDTO>>> getCityGenderReport(@RequestParam int minAge) {
            List<Kid> kids = ListSEService.getKids(); // Obtener la lista de niños usando el servicio de niños.
            List<CityGenderReportDTO> report = listSEService.getCityGenderReport(minAge, kids);
            ResponseDTO<List<CityGenderReportDTO>> response = new ResponseDTO<>(HttpStatus.OK.value(), report, null);
            return ResponseEntity.ok(response);
        }
    @GetMapping("/kids/average-age")
    public ResponseEntity<Double> getAverageKidAge() {
        try {
            double averageAge = ListSEService.getAverageKidAge();
            return ResponseEntity.ok(averageAge);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/advance/{numPositions}")
    public ResponseEntity<String> advance(@PathVariable int numPositions) {
        try {
            ListSEService.advance(numPositions);
            return ResponseEntity.ok("El niño avanzó " + numPositions + " posiciones en la lista.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/losePositions/{numPositions}")
    public ResponseEntity<String> losePositions(@PathVariable int numPositions) {
        try {
            ListSEService.losePositions(numPositions);
            return ResponseEntity.ok("El niño perdió " + numPositions + " posiciones en la lista.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/moveKids/{letter}")
    public ResponseEntity<String> moveKidsToEnd(@PathVariable char letter) {
        try {
            moveKidsToEnd(letter);
            return ResponseEntity.ok("Kids with name starting with " + letter + " moved to end of list.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    }


package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.RangeDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDEExcepcion;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.model.Ranges;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @Autowired
    private RangeService rangeService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getPets().printPets() , null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO) throws ListDEExcepcion {
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null){
                return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
            }
            Pet newPet = new Pet(petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),petDTO.getPetType(),petDTO.getBreed(),petDTO.getGender(),location , false);
            listDEService.getPets().addPet(newPet);
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado a la mascota", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicacion no existe", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/delete_pet_by_age/{age}")
    public ResponseEntity<ResponseDTO> deletePetByAge(@PathVariable byte age) {
        try {
            listDEService.getPets().deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota fue eliminada exitosamente", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ocurrió un error al eliminar la mascota", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/delete_pet_by_id/{id}")
    public ResponseEntity<ResponseDTO> deletePetsById(@PathVariable String id) {
        try {
            listDEService.getPets().deletePetByIdentification(id);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota fue eliminada exitosamente", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ocurrió un error al eliminar la mascota", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/invert_pets")
    public ResponseEntity<ResponseDTO> invertPets() {
        try {
            listDEService.getPets().invertPets();
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha invertido la lista", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ocurrió un error al invertir la lista", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/order_pets_to_start")
    public ResponseEntity<ResponseDTO> orderPetsToStart() {
        try {
            listDEService.getPets().addPetsToStart();
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al agregar el primer niño", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "La lista fue actualizada", null), HttpStatus.OK);
    }

    @GetMapping(path = "/alternate_pets")
    public ResponseEntity<ResponseDTO> alternatePets() {
        try {
            listDEService.getPets().alternatePets();
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al realizar la accion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "La operacion tuvo exito, se han alternado las mascotas", null), HttpStatus.OK);
    }

    @GetMapping(path = "/average_pets")
    public ResponseEntity<ResponseDTO> getAveragePetsByAge() {
        try {
            double averageAge = listDEService.getPets().averagePetByAge();
            return new ResponseEntity<>(new ResponseDTO(200, "La edad promedio de mascotas es: " + averageAge, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add_pet_by_position/{position}")
    public ResponseEntity<ResponseDTO> addPetByPosition(@RequestBody PetDTO pet, @PathVariable int position) {
        try {
            Location location = locationService.getLocationByCode(pet.getCodeLocation());
            if (location == null){
                return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
            }
            Pet newPet = new Pet(pet.getName(), pet.getAge(), pet.getIdentification(), pet.getPetType() ,pet.getBreed(), pet.getGender() , location , false);
            listDEService.getPets().addPetByPosition(newPet,position);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota fue añadida en la posición solicitada", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/win_position_pet/{code}/{num}")
    public ResponseEntity<ResponseDTO> winPosition(@PathVariable String code, @PathVariable int num) {
        try {
            listDEService.getPets().winPositionPet(code,num);
            return new ResponseEntity<>(new ResponseDTO(200, "Accion realizada con exito, se ha podido adelantar la posicion de la mascota", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/lost_position_pet/{code}/{position}")
    public ResponseEntity<ResponseDTO> losePosition(@PathVariable String code, @PathVariable int position) {
        try {
            listDEService.getPets().lostPositionPet(code,position);
            return new ResponseEntity<>(new ResponseDTO(200, "Accion realizada con exito, se ha podido perder la posicion de la mascota ", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Se produjo un error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/report_range_by_age")
    public ResponseEntity<ResponseDTO> getReportRangeByAgePets(){
        try {
            List<RangeDTO> petsRangeList = new ArrayList<>();
            for (Ranges i : rangeService.getRanges()){
                int quantity = listDEService.getPets().getReportPetByRangeAge(i.getFrom(), i.getTo());
                petsRangeList.add(new RangeDTO(i,quantity));
            }
            return new ResponseEntity<>(new ResponseDTO(200,"Accion realizada con exito, el rango de los niños es: "+ petsRangeList, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500,"Error al obtener el rango de edades", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/report_by_city")
    public ResponseEntity<ResponseDTO> getReportPetsByLocationCode(){
        List<KidsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        try{
            for(Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getReportPetsByLocationCode(loc.getCode());
                if(count > 0){
                    petsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200,petsByLocationDTOList, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500,"Error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/report_by_department")
    public ResponseEntity<ResponseDTO> getReportPetsByDeptCode(){
        List<KidsByLocationDTO> listPetsByLocationDTO= new ArrayList<>();
        try{
            for(Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getReportPetsByDeptCode(loc.getCode());
                if(count > 0){
                    listPetsByLocationDTO.add(new KidsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200,listPetsByLocationDTO, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500,"Error al realizar la operacion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/send_pet_final_by_letter/{letter}")
    public ResponseEntity<ResponseDTO> petToFinishByLetter(@PathVariable char letter){
        try{
            listDEService.getPets().sendPetToTheEndByLetter(Character.toUpperCase(letter));
            return new ResponseEntity<>(new ResponseDTO(200,"Accion realizada con exito, las mascotas con la letra dada se han enviado al final de la lista", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/delete_specific_pet_by_ide/{ide}")
    public ResponseEntity<ResponseDTO> deleteSpecificPet(@PathVariable String ide) {
        if (listDEService.getPets().printPets() != null) {
            listDEService.getPets().deleteSpecificNodeDE(ide);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota se elimino con exito", null), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResponseDTO(400, "No se pudo realizar la operacion debido a que no existian mascotas", null), HttpStatus.BAD_REQUEST);
        }
    }
} // end class controller

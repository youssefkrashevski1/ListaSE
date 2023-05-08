package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets().getHead(), null), HttpStatus.OK);
    }
    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        try {
            listDEService.getPets().reverse();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se han intercambiado los extremos", null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ha ocurrido un error al intercambiar los extremos",
                    null), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/add_pet_to_start_and_end")
    public ResponseEntity<ResponseDTO> addPetToStartAndEnd() {
        try {
            listDEService.getPets().addPetToStartAndEnd();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Pet added to start and end of list",
                    null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, e.getMessage(),
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/interleave_pets")
    public ResponseEntity<ResponseDTO> interleavePets() throws Exception {
        ListDE pets = listDEService.getPets().interleavePets();
        if (pets == null || ((ListDE) pets).getHead() == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Lista vacía o sin elementos", null), HttpStatus.NOT_FOUND);
        }

        try {
            pets.interleavePets();
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al intercalar mascotas: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200, "Se han intercalado las mascotas de forma exitosa", null), HttpStatus.OK);
    }
    @GetMapping(path = "/delete_pets_by_age")
    public ResponseEntity<ResponseDTO> deletePetsByAge() {
        listDEService.getPets().deletePetsByAge();
        try {
            // Get the list of pets from the service
            ListDE list = listDEService.getPets().deletePetsByAge();

            // Check if the list is empty
            if (list.isEmpty()) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "The list is empty.",
                        null), HttpStatus.NOT_FOUND);
            }

            // Get the minimum and maximum age of pets in the list
            byte minAge = Byte.MAX_VALUE;
            byte maxAge = Byte.MIN_VALUE;
            NodeDE current = list.getHead();
            while (current != null) {
                Pet pet = ((NodeDE) current).getPet();
                if (pet != null) {
                    byte age = ((Pet) pet).getAge();
                    if (age < minAge) {
                        minAge = age;
                    }
                    if (age > maxAge) {
                        maxAge = age;
                    }
                }
                current = current.getNext();
            }

            // Delete all pets with the minimum and maximum age from the list
            boolean deleted = false;
            current = list.getHead();
            while (current != null) {
                Pet pet = current.getPet();
                if (pet != null) {
                    byte age = pet.getAge();
                    if (age == minAge || age == maxAge) {
                        list.deleteNodeDE();
                        deleted = true;
                    }
                }
                current = current.getNext();
            }

            // Check if any pets were deleted and return an appropriate response
            if (deleted) {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "The pets with age " + minAge + " and " + maxAge + " have been deleted.",
                        null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "No pets with minimum or maximum age found in the list.",
                        null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "An error occurred while processing the request: " + e.getMessage(),
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/average_age")
    public ResponseEntity<ResponseDTO> getAverageAge() {
        double averageAge = 0.0;
        int numPets = 0;
        NodeDE curr = listDEService.getHead();
        while (curr != null) {
            Pet pet = curr.getData();
            if (pet != null) {
                averageAge += pet.getAge();
                numPets++;
            }
            curr = curr.getNext();
        }
        if (numPets == 0) {
            return new ResponseEntity<>(new ResponseDTO(
                    404,"No hay mascotas en la lista",
                    null), HttpStatus.NOT_FOUND);
        } else {
            averageAge /= numPets;
            return new ResponseEntity<>(new ResponseDTO(
                    200,"La edad promedio de las mascotas es " + averageAge,
                    null), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/delete_node")
    public ResponseEntity<ResponseDTO> deleteNodeDE() {
        listDEService.getPets().deleteNodeDE();
        try {
            ListDE petsList = listDEService.getPetsList(); // Supongamos que así obtienes la lista de mascotas
            NodeDE firstNode = petsList.getFirstNode();
            NodeDE lastNode = petsList.getLastNode();
            NodeDE nodeToDelete = null;

            // Aquí puedes agregar tu lógica para obtener el nodo que deseas eliminar de la lista

            if (nodeToDelete == null) {
                throw new Exception("No se encontró ningún nodo para eliminar");
            } else if (nodeToDelete == firstNode) {
                petsList.deleteFirst();
            } else if (nodeToDelete == lastNode) {
                petsList.deleteLast();
            } else {
                petsList.deleteNodeDE();
            }

            return new ResponseEntity<>(new ResponseDTO(
                    200,"Nodo eliminado exitosamente",
                    null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/remove_node_at_position")
    public ResponseEntity<ResponseDTO> removeNodeAtPosition() {
        ListDE petList = listDEService.getPets().getPets();
        try {
            petList.removeNodeAtPosition(5); // Se eliminara el nodo en la posicion 5, puedes cambiar el valor si lo necesitas.
            return new ResponseEntity<>(new ResponseDTO(
                    200,"El nodo ha sido eliminado exitosamente",
                    null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al eliminar el nodo: " + e.getMessage(),
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/age_range_report")
    public ResponseEntity<ResponseDTO> generateAgeRangeReport() throws ListDE.AgeOutOfRangeException {
        listDEService.getPets().generateAgeRangeReport();
        try {
            listDEService.getPets().generateAgeRangeReport();
        } catch (ListDE.AgeOutOfRangeException e) {
            throw new RuntimeException(e);
        }
        try {
            String report = listDEService.getPets().generateAgeRangeReport();
            return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(400, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ResponseDTO> movePetsWithNameStartingWith(@PathVariable char letter) {
        {
            try {
                listDEService.movePetsWithNameStartingWith(letter);
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se han movido los pets cuyo nombre empieza con " + letter + " al final de la lista",
                        null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "No se encontraron pets cuyo nombre empieza con " + letter,
                        null), HttpStatus.NOT_FOUND);
            }
        }
    }
    @GetMapping(path = "/interleave_pets")
    public ResponseEntity<ResponseDTO> removeidenticate() {
        ListDE pets = listDEService.getPets().removeidenticate();
        if (pets.getHead() == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Lista vacía o sin elementos", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseDTO(
                200, "Mascotas intercaladas con éxito", null));
    }


    @GetMapping(path = "/delete_first_last_and_middle_node")
    public ResponseEntity<ResponseDTO> deleteFirstLastAndMiddleNode() {
        ListDE petsList = listDEService.getPets();
        int sizeBefore = petsList.size();
        deleteNodes(petsList);
        int sizeAfter = petsList.size();
        int deletedNodes = sizeBefore - sizeAfter;
        return new ResponseEntity<>(new ResponseDTO(
                200, (deletedNodes + " nodes deleted from list"),
                null), HttpStatus.OK);
    }

    private void deleteNodes(ListDE list) {
        // Eliminar primer nodo
        if (list.getHead() != null) {
            list.setHead(list.getHead().getNext());
            if (list.getHead() != null) {
                list.getHead().setPrev(null);
            }
        }

        // Eliminar último nodo
        if (list.getTail() != null) {
            list.setTail(list.getTail().getPrev());
            if (list.getTail() != null) {
                list.getTail().setNext(null);
            }
        }

        // Eliminar nodo de la mitad
        int size = list.size();
        if (size >= 3) {
            int middleIndex = size / 2;
            NodeDE currentNode = list.getHead();
            for (int i = 0; i < middleIndex; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.getPrev().setNext(currentNode.getNext());
            currentNode.getNext().setPrev(currentNode.getPrev());
        }
    }

}










package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/kids")
public class KidController {

    private final List<Kid> boysList = new ArrayList<>();
    private final List<Kid> girlsList = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Void> addKid(@RequestBody Kid kid) {
        if (kid.isBoy()) {
            boysList.add(0, kid);
        } else {
            girlsList.add(kid);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/boys")
    public ResponseEntity<List<Kid>> getBoysList() {
        return ResponseEntity.ok().body(boysList);
    }

    @GetMapping("/girls")
    public ResponseEntity<List<Kid>> getGirlsList() {
        return ResponseEntity.ok().body(girlsList);
    }

    @PostMapping("/interleave-boy-girl")
    public ResponseEntity<Void> interleaveBoyGirl() {
        ListSEController listSEService = null;
        listSEService.getKids().getHeaders();
        try {
           interleaveBoyGirl();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // handle any exceptions here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/age-report")
    public ResponseEntity<String> generateAgeReport() {

        // Crear una nueva instancia de Kid y agregarla a la lista
        ListSE.addKid(new Kid());

        // Contar el número de niños en cada rango de edad
        int[] ageCounts = new int[19];
        assert ListSE.getHead() != null;
        Kid node = ListSE.getHead().getData();
        while (node != null) {
            ageCounts[node.getAge()]++;
            node = node.getNext();
        }

        // Construir un informe de edad en formato de cadena
        StringBuilder report = new StringBuilder("Age Report:\n");
        for (int i = 0; i < ageCounts.length; i++) {
            if (ageCounts[i] > 0) {
                report.append("Age ").append(i).append(": ").append(ageCounts[i]).append(" kids\n");
            }
        }

        // Devolver la respuesta HTTP con el informe de edad generado
        return new ResponseEntity<>(report.toString(), HttpStatus.OK);
    }
}






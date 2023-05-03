package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
        ListSE.addKid(new Kid());

        int[] ageCounts = new int[19];
        Kid node = ageCounts.Get();
        while (node != null) {
            ageCounts[node.getAge()]++;
            node = node.getNext();
        }

        StringBuilder report = new StringBuilder("Age Report:\n");
        for (int i = 0; i < ageCounts.length; i++) {
            if (ageCounts[i] > 0) {
                report.append("Age ").append(i).append(": ").append(ageCounts[i]).append(" kids\n");
            }
        }
        return new ResponseEntity<>(report.toString(), HttpStatus.OK);
    }
}





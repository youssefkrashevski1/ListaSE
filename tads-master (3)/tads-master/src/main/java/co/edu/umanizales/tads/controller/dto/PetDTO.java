package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetDTO {
    private String identification;
    private String nameOwner;
    private String name;
    private char gender;
    private String especies;
    private byte age;
    private Location location;
}

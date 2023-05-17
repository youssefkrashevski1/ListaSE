package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Kid;
import lombok.Data;

@Data
public class KidDTO {

    private String identification;
    private String name;
    private byte age;
    private char gender;
    private String codeLocation;
}

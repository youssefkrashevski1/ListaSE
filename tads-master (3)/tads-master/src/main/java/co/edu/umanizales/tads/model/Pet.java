package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class Pet {
    private String identification;
    private String nameOwner;
    private String name;
    private char gender;
    private String especies;
    private byte age;
    private Location location;
}

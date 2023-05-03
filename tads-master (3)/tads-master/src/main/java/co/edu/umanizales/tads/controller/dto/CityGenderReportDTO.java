package co.edu.umanizales.tads.controller.dto;

import jdk.javadoc.doclet.Taglet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
 @AllArgsConstructor
public class CityGenderReportDTO {
        private String city;
        private List<GenderDTO> genderDTOSlist;
        private int total;


    public void updateQuantity(String name, char gender) {
    }
}

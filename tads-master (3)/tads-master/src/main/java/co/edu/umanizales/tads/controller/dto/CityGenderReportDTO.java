package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
 @AllArgsConstructor
public class CityGenderReportDTO {
        private String city;
        private List<GenderDTO> genderDTOSlist;
        private int total;



}

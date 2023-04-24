package co.edu.umanizales.tads.model;

import ch.qos.logback.core.joran.spi.ElementSelector;
import co.edu.umanizales.tads.controller.dto.CityGenderReportDTO;
import co.edu.umanizales.tads.controller.dto.GenderDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ListSE {
    private Node head;
    private int size;

    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;
    }

    public void invert() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void changeExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

        public List<CityGenderReportDTO> getCityGenderReport(int minAge, List<Kid> children) {
            List<CityGenderReportDTO> report = new ArrayList<>();

            // Agrupar los niños por ciudad y género
            Map<String, Map<String, Integer>> cityGenderCountMap = new HashMap<>();
            for (Kid kid : children) {
                if (Kid.getAge() >= minAge) {
                    String city = kid.getCity();
                    String gender = children.getGender();
                    cityGenderCountMap.computeIfAbsent(city, k -> new HashMap<>());
                    cityGenderCountMap.get(city).merge(gender, 1, Integer::sum);
                }
            }

            // Generar la lista de CityGenderReportDTO a partir del mapa
            for (Map.Entry<String, Map<String, Integer>> cityEntry : cityGenderCountMap.entrySet()) {
                String city = cityEntry.getKey();
                Map<String, Integer> genderCountMap = cityEntry.getValue();
                List<GenderDTO> genders = new ArrayList<>();
                int total = 0;
                for (Map.Entry<String, Integer> genderEntry : genderCountMap.entrySet()) {
                    String gender = genderEntry.getKey();
                    int count = genderEntry.getValue();
                    genders.add(new GenderDTO(gender, count));
                    total += count;
                }
                report.add(new CityGenderReportDTO(city, genders, total));
            }

            return report;
        }



}

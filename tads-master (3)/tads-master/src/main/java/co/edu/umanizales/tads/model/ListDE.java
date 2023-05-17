package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEExcepcion;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {

    private NodeDE headDE;
    private int size;
    List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) throws ListDEExcepcion {
        if (headDE != null) {
            NodeDE temp = headDE;
            while (temp.getNextDE() != null) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDEExcepcion("Ya existe el perro");
                }
                temp = temp.getNextDE();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDEExcepcion("Ya existe un perro");
            }
            /// Parado en el último
            NodeDE newNode = new NodeDE(pet);
            temp.setNextDE(newNode);
            newNode.setPreviousDE(temp);

        } else {
            headDE = new NodeDE(pet);
        }
        size++;
    }

    public void addPetToStart(Pet pet) {
        if (this.headDE != null) {
            NodeDE newNode = new NodeDE(pet);
            newNode.setNextDE(headDE);
            headDE.setPreviousDE(newNode);
            headDE = newNode;
        } else {
            headDE = new NodeDE(pet);
        }
        size++;
    }

    public void addPetByPosition(Pet pet, int position) throws ListDEExcepcion{
        if (position < 0 || position > size){
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        NodeDE newNode = new NodeDE(pet);
        if (position == 0){
            newNode.setNextDE(headDE);
            if (headDE != null){
                headDE.setPreviousDE(newNode);
            }
            headDE = newNode;
        }else {
            NodeDE current = headDE;
            for (int i = 1; i < position -1; i++){
                current = current.getNextDE();
            }
            newNode.setNextDE(current.getNextDE());
            if (current.getNextDE() != null){
                current.getNextDE().setPreviousDE(newNode);
            }
            current.setNextDE(newNode);
            newNode.setPreviousDE(current);
        }
    }

    public List <Pet> printPets(){
        pets.clear();
        if (headDE != null){
            NodeDE temp = headDE;
            while (temp != null){
                pets.add(temp.getData());
                temp = temp.getNextDE();
            }
        }
        return pets;
    }

    public void deletePetByIdentification(String identification) throws ListDEExcepcion{
        if (identification != null) {
            NodeDE temp = headDE;
            while (temp != null) {
                if (temp.getData().getIdentification().equals(identification)) {
                    NodeDE previo = temp.getPreviousDE();
                    NodeDE next = temp.getNextDE();
                    if (previo == null) {
                        headDE = next;
                    } else {
                        previo.setNextDE(next);
                    }
                    if (next != null) {
                        next.setPreviousDE(previo);
                    }
                }
                temp = temp.getNextDE();
            }
        }else {
            throw new ListDEExcepcion("La identificacion no puede ser nulo");
        }
    }

    public void invertPets() throws ListDEExcepcion {
        if (this.headDE == null) {
            throw new ListDEExcepcion("No hay niños para poder invertir la lista");
        } else {
            ListDE listCopy = new ListDE();
            NodeDE temp = this.headDE;
            while (temp != null) {
                listCopy.addPetToStart(temp.getData());
                temp = temp.getNextDE();
            }
            this.headDE = listCopy.getHeadDE();
        }
    }

    public void addPetsToStart() throws ListDEExcepcion {
        if (headDE == null) {
            throw new ListDEExcepcion("La lista está vacía y no se puede realizar la operacion");
        }
        ListDE listCopy = new ListDE();
        NodeDE temp = headDE;
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listCopy.addPetToStart(temp.getData());
            } else {
                listCopy.addPet(temp.getData());
            }
            temp = temp.getNextDE();
        }
        headDE = listCopy.getHeadDE();
    }

    public void alternatePets() throws ListDEExcepcion {
        ListDE alternateList = new ListDE();

        ListDE listBoys = new ListDE();
        ListDE listGirls = new ListDE();

        NodeDE temp = headDE;

        if (this.headDE == null && this.headDE.getNextDE() == null) {
            throw new ListDEExcepcion("No existen niños o no hay suficientes para alternar");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listBoys.addPet(temp.getData());
                } else {
                    if (temp.getData().getGender() == 'F') {
                        listGirls.addPet(temp.getData());
                    }
                }
                temp = temp.getNextDE();
            }

            NodeDE boysNode = listBoys.getHeadDE();
            NodeDE girlsNode = listGirls.getHeadDE();

            while (boysNode != null) {
                if (boysNode != null) {
                    alternateList.addPet(boysNode.getData());
                    boysNode = boysNode.getNextDE();
                }
                if (girlsNode != null) {
                    alternateList.addPet(girlsNode.getData());
                    girlsNode = girlsNode.getNextDE();
                }
            }
            this.headDE = alternateList.getHeadDE();
        }
    }

    public void deleteByAge(Byte age) throws IllegalArgumentException {
        if (age == null) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser nula");
        }
        NodeDE temp = headDE;
        NodeDE previo;
        while (temp != null) {
            if (temp.getData().getAge() == age) {
                previo = temp.getPreviousDE();
                NodeDE next = temp.getNextDE();
                if (previo == null) {
                    headDE = next;
                } else {
                    previo.setNextDE(next);
                }
                if (next != null) {
                    next.setPreviousDE(previo);
                }
            }
            temp = temp.getNextDE();
        }
    }

    public double averagePetByAge() throws ListDEExcepcion {
        if (headDE != null) {
            NodeDE temp = headDE;
            int count = 0;
            int ages = 0;
            while (temp.getNextDE() != null) {
                count++;
                ages += temp.getData().getAge();
                temp = temp.getNextDE();
            }
            if (count == 0) {
                throw new ListDEExcepcion("La lista esta vacia, no se puede realizar la accion");
            }
            return (float) ages / count;
        } else {
            throw new ListDEExcepcion("La lista esta vacia, no se puede realizar la accion");
        }
    }

    public int getReportPetsByLocationCode(String code) throws ListDEExcepcion{
        int count =0;
        if (code == null || code.isEmpty()) {
            throw new ListDEExcepcion("El codigo de la ciudad no puede estar vacio");
        }
        if(this.headDE != null){
            NodeDE temp = this.headDE;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNextDE();
            }
        }
        return count;
    }

    public int getReportPetsByDeptCode(String code) throws ListDEExcepcion{
        int count =0;
        if(this.headDE != null){
            NodeDE temp = this.headDE;
            while(temp != null){
                if(temp.getData().getLocation().getCode().substring(0,5).equals(code)){
                    count++;
                }
                temp = temp.getNextDE();
            }
        }else {
            throw new ListDEExcepcion("No existen mascotas para poder realizar la operación");
        }
        return count;
    }

    public void winPositionPet(String id, int position) throws ListDEExcepcion {
        if (position < 0) {
            throw new ListDEExcepcion("La posición debe ser un número positivo");
        }
        if (headDE != null) {
            NodeDE temp = headDE;
            int count = 1;
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNextDE();
                count++;
            }
            if (temp != null) {
                int newPosition = count - position;
                if (newPosition < 0) {
                    throw new IndexOutOfBoundsException("La posición especificada está fuera de los límites de la lista");
                }
                Pet listCopy = temp.getData();
                deletePetByIdentification(temp.getData().getIdentification());
                if (newPosition > 0) {
                    addPetByPosition(listCopy, newPosition);
                } else {
                    addPetToStart(listCopy);
                }
            }
        }
    }

    public void lostPositionPet (String identification , int position) throws ListDEExcepcion {
        if (position < 0) {
            throw new ListDEExcepcion("La posicion no puede ser menor a cero");
        }
        NodeDE temp = headDE;
        int count = 1;
        while (temp != null && !temp.getData().getIdentification().equals(identification)) {
            temp = temp.getNextDE();
            count++;
        }

        int sum = position + count;
        Pet listCopy = temp.getData();
        deletePetByIdentification(temp.getData().getIdentification());
        addPetByPosition(listCopy, sum);
    }

    public int getReportPetByRangeAge(int firstRange, int lastRange) throws ListDEExcepcion {
        NodeDE temp = headDE;
        int count = 0;
        if (this.headDE == null) {
            throw new ListDEExcepcion("No existen mascotas para poder realizar la operación");
        } else {
            while (temp != null) {
                if (temp.getData().getAge() >= firstRange && temp.getData().getAge() <= lastRange) {
                    count++;
                }
                temp = temp.getNextDE();
            }
            return count;
        }
    }

    public void sendPetToTheEndByLetter(char letter) throws ListDEExcepcion {
        if (this.headDE != null) {
            ListDE listCopy = new ListDE();
            NodeDE temp = this.headDE;
            char firstChar = Character.toUpperCase(letter);

            while (temp != null) {
                char firstLetter = temp.getData().getName().charAt(0);
                if (Character.toUpperCase(firstLetter) != firstChar) {
                    listCopy.addPetToStart(temp.getData());
                } else {
                    listCopy.addPet(temp.getData());
                }
                temp = temp.getNextDE();
            }
            this.headDE = listCopy.getHeadDE();
        } else {
            throw new ListDEExcepcion("La lista no puede estar vacia");
        }
    }

    /* Metodo eliminar pero esta vez parado exactamente en el nodo (en este caso mascota).

    Primero debo de recibir como parametro la identificacion de la mascota el cual me identifique el nodo para borrarlo y empiezar a realizar el metodo.

    Lo que debo realizar primero son validaciones las cuales serian si la lista tiene valores, es decir, que no sea null

    Despues lo que debo hacer es empezar a recorrer la lista por medio de un ayudante y lograr pararme en el nodo exactamente que deseo eliminar
    una vez parado en el nodo que deseo eliminar, tengo que realizar nuevas validaciones que serian:

     1- Si el "siguiente" del nodo es null, entonces debo eliminar y volver al nodo anterior como ultimo y definir que el siguiente del nodo anterior
     al que voy a eliminar va a tener null.

     Para eso, estando parado en el nodo que deseo eliminar, genero una vaiable temporal que va a ser igual al previous del nodo que voy a eliminar para asi
     decirle a ese nodo que su "siguiente" va a ser null, y a la vez decirle al previous del nodo que voy a eliminar que va a ser null

     2- Si el previous del nodo que voy a eliminar es null, quiere decir que es la cabeza por lo tanto digo que la cabeza va a ser igual a cabeza.siguiente y pregunto
     que si cabeza es diferente a null; si hay datos, digo que cabeza. previous va a ser igual a null

     3- Si el nodo que voy a eliminar es un nodo que esta en medio de dos nodos, entonces genero dos variables las cuales una de ellas va a ser
     igual al nodo anterior del que deseo eliminar y el otro va a ser igual al nodo siguiente que deseo eliminar, para que a la vez que
     elimino las conexiones que tiene el siguiente y el previous del nodo que voy a eliminar, actualizar las conexiones de los nodo vecinos

     */
    public void deleteSpecificNodeDE(String identification){
        NodeDE temp = headDE;
        NodeDE before , after;
        if(this.headDE != null) {
            if (this.headDE.getData().getIdentification().equals(identification)) {
                this.headDE = headDE.getNextDE();
                if (headDE != null) {
                    headDE.setPreviousDE(null);
                }
            } else {
                while (!temp.getData().getIdentification().equals(identification)) {
                    temp = temp.getNextDE();
                }
                temp = temp.getNextDE();
                if(temp.getNextDE() == null) {
                    before = temp.getPreviousDE();
                    before.setNextDE(null);
                } else {
                    before = temp.getPreviousDE();
                    after = temp.getNextDE();
                    before.setNextDE(after);
                    after.setPreviousDE(before);
                }
            }
        }
    }

} // end class ListDE




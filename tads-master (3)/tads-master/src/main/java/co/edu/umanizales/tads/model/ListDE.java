package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.service.ListDEService;
import lombok.Data;
@Data
public class ListDE {

    private NodeDE head;
    private NodeDE tail;
    private int size;

    public void add(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            NodeDE newNodeDE = new NodeDE(pet);
            temp.setNext(newNodeDE);
        } else {
            Pet Pet = null;
            head = new NodeDE(null);
        }
        size++;
    }

    public void Concat() {
    }

    // Constructor vacío
    public ListDE() {
        head = null;
        tail = null;
        size = 0;
    }

    // Agregar un nuevo nodo al final de la lista
    public void add(NodeDE node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    // Eliminar un nodo de la lista
    public void remove(Node node) {
        NodeDE current = head;
        while (current != null) {
            if (current.equals(node)) {
                if (current.prev == null) {
                    head = current.getNext();
                } else {
                    current.prev.setNext(current.getNext());
                }
                if (current.getNext() == null) {
                    tail = current.prev;
                } else {
                    current.getNext().prev = current.prev;
                }
                size--;
                break;
            }
            current = current.getNext();
        }
    }

    // Obtener un nodo de la lista por índice
    public NodeDE get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        NodeDE current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current;
    }

    // Modificar un nodo de la lista por índice
    public void set(int index, Node node) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        NodeDE current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        current.value = node.getValue();
    }

    // Invertir la lista
    public void reverse() {
        NodeDE temp = null;
        NodeDE current = head;

        /* Recorremos la lista hacia adelante para encontrar el último nodo */
        while (current != null) {
            temp = current.prev;
            current.prev = current.getNext();
            current.setNext(temp);
            current = current.prev;
        }

        /* Actualizamos la cabeza y la cola de la lista */
        if (temp != null) {
            head = temp.prev;
        }
    }

    // Mostrar la lista en orden de inicio a fin
    public void show() {
        NodeDE current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public void addPetToStartAndEnd() throws Exception {
        if (head == null) {
            throw new Exception("The linked list is empty.");
        }

        Pet malePet = null;
        NodeDE newNode1 = new NodeDE(null);
        Pet femalePet = null;
        NodeDE newNode2 = new NodeDE(null);

        NodeDE current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        newNode1.setNext(head);
        head.setPrev(newNode1);
        head = newNode1;

        current.setNext(newNode2);
        newNode2.setPrev(current);
    }

    public void interleavePets() throws Exception {
        if (head == null || head.getNext() == null) {
            throw new Exception("List is empty or contains only one element");
        }

        NodeDE current = head;
        NodeDE lastMale = null;

        while (current != null) {
            if (current.pet.getGender() == 'M') {
                if (lastMale == null) {
                    lastMale = current;
                } else {
                    NodeDE nextFemale = lastMale.getNext();
                    while (nextFemale != null && nextFemale.pet.getGender() == 'M') {
                        nextFemale = nextFemale.getNext();
                    }
                    if (nextFemale == null) {
                        throw new Exception("No female pets found to interleave with male pets");
                    }
                    NodeDE newMale = new NodeDE(current.pet);
                    lastMale.setNext(newMale);
                    newMale.prev = lastMale;
                    newMale.setNext(nextFemale);
                    nextFemale.prev = newMale;
                    lastMale = newMale;
                }
            } else if (current.pet.getGender() == 'F') {
                // do nothing
            } else {
                throw new Exception("Invalid pet gender: " + current.pet.getGender());
            }
            current = current.getNext();
        }
    }

    public void deletePetsByAge(NodeDE head, byte ageToDelete) throws Exception {
        if (head == null) {
            throw new Exception("List is empty");
        }

        NodeDE current = head;

        while (current != null) {
            if (current.getPet().getAge() == ageToDelete) {
                if (current == head) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                } else {
                    current.getPrev().setNext(current.getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrev(current.getPrev());
                    }
                }
            }
            current = current.getNext();
        }

        if (head == null) {
            throw new Exception("All pets with age " + ageToDelete + " have been deleted");
        }
    }

    public double getAverageAge() throws Exception {
        if (head == null) {
            throw new Exception("The list is empty.");
        }

        NodeDE current = head;
        int totalAge = 0;
        int count = 0;

        while (current != null) {
            if (current.getPet() == null) {
                throw new Exception("The list contains a node with null pet.");
            }
            totalAge += current.getPet().getAge();
            count++;
            current = current.getNext();
        }

        if (count == 0) {
            throw new Exception("The list is empty.");
        }

        return (double) totalAge / count;
    }

    public boolean isEmpty() {
        return false;
    }

    public void deleteNodeDE(NodeDE current) {
    }
    public void movePetForward(NodeDE petNode, int numPositions) throws IllegalArgumentException {
        if (petNode == null) {
            throw new IllegalArgumentException("Pet node cannot be null.");
        }
        if (numPositions < 0) {
            throw new IllegalArgumentException("Number of positions to move forward cannot be negative.");
        }
        for (int i = 0; i < numPositions; i++) {
            if (petNode.getNext() == null) {
                throw new IllegalArgumentException("Cannot move pet forward by " + numPositions + " positions. End of list reached.");
            }
            petNode = petNode.getNext();
        }
    }

    public NodeDE getFirstNoDE() {
        return null;
    }

    public NodeDE getLastNode() {
        return null;
    }

    public void deleteFirst() {
    }

    public void deleteLast() {
    }

    public NodeDE getFirstNode() {
        return null;
    }
    public void removeNodeAtPosition(int position) throws Exception {
        if (head == null) {
            throw new Exception("The list is empty");
        }

        NodeDE currentNode = head;
        int currentPosition = 1;

        while (currentNode != null && currentPosition != position) {
            currentNode = currentNode.getNext();
            currentPosition++;
        }

        if (currentNode == null) {
            throw new Exception("The position is out of range");
        }

        if (currentNode == head) {
            head = currentNode.getNext();
        }

        if (currentNode == tail) {
            tail = currentNode.getPrevious();
        }

        if (currentNode.getPrevious() != null) {
            currentNode.getPrevious().setNext(currentNode.getNext());
        }

        if (currentNode.getNext() != null) {
            currentNode.getNext().setPrevious(currentNode.getPrevious());
        }

        size--;
    }
    public ListDE getPets() {
        ListDE pets = null;
        return null;
    }
    public String generateAgeRangeReport(ListDEService petList) throws AgeOutOfRangeException {
        // Define the age ranges and initialize counters for each range
        final int[] AGE_RANGES = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] ageCount = new int[AGE_RANGES.length + 1]; // the last element counts all pets older than 19

        // Iterate over the pet list and count the number of pets in each age range
        NodeDE current = petList.getHead();
        while (current != null) {
            int age = current.getData().getAge();
            if (age < AGE_RANGES[0] || age > AGE_RANGES[AGE_RANGES.length - 1]) {
                ageCount[ageCount.length - 1]++; // pet is older than 19
            } else {
                for (int i = 0; i < AGE_RANGES.length; i++) {
                    if (age <= AGE_RANGES[i]) {
                        ageCount[i]++;
                        break;
                    }
                }
            }
            current = current.getNext();
        }

        // Generate the report string
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < AGE_RANGES.length; i++) {
            int startAge = AGE_RANGES[i];
            int endAge = (i == AGE_RANGES.length - 1) ? Integer.MAX_VALUE : AGE_RANGES[i+1] - 1;
            int count = ageCount[i];
            report.append(String.format("Pets between %d and %d years old: %d\n", startAge, endAge, count));
        }
        report.append(String.format("Pets older than %d years: %d\n", AGE_RANGES[AGE_RANGES.length - 1], ageCount[AGE_RANGES.length]));

        // Check for age ranges with no pets and throw an exception
        for (int count : ageCount) {
            if (count == 0) {
                throw new AgeOutOfRangeException("No pets found in age range.");
            }
        }

        return report.toString();
    }

    public static class AgeOutOfRangeException extends Exception {
        public AgeOutOfRangeException(String message) {
            super(message);
        }
    }
    public void movePetsWithNameStartingWith(char letter) throws Exception {
        NodeDE current = head;
        NodeDE last = tail;

        while (current != null) {
            if (current.getData().getName().charAt(0) == letter) {
                // move the node to the end
                if (current == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                } else {
                    current.getPrev().setNext(current.getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrev(current.getPrev());
                    }
                }
                current.setPrev(last);
                current.setNext(null);
                if (last != null) {
                    last.setNext(current);
                }
                last = current;
                current = head;
            } else {
                current = current.getNext();
            }
        }

        if (last == null) {
            // no pets were moved, so the letter was not found
            throw new Exception("No pets found with name starting with " + letter);
        }
    }

}



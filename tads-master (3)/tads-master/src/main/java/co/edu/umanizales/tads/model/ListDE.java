package co.edu.umanizales.tads.model;

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

    public ListDE interleavePets() throws Exception {
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
        return null;
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

    public void deleteNodeDE() {
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
    public String generateAgeRangeReport() throws AgeOutOfRangeException {
        // Define the age ranges and initialize counters for each range
        final int[] AGE_RANGES = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] ageCount = new int[AGE_RANGES.length + 1]; // the last element counts all pets older than 19

        // Iterate over the pet list and count the number of pets in each age range
        NodeDE current = tail.getHead();
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

    public ListDE deletePetsByAge() {
        return null;
    }

    public int size() {
        return 0;
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
    //Recorre la lista de nodos desde el primer nodo hasta el último nodo.
    //En cada iteración, compara la identificación del nodo actual con la identificación del nodo que se desea eliminar.
    //Si la identificación coincide, actualiza los enlaces previos y siguientes de los nodos adyacentes para que se salte el nodo que se desea eliminar.
    //Si la identificación no coincide, avanza al siguiente nodo y continúa el proceso.
    //Si llegas al final de la lista sin encontrar un nodo con la identificación deseada, el nodo no existe en la lista.
    public ListDE removeidenticate() {
        // Verificar si el nodo es el primer o el último en la lista.
        NodeDE next;
        next = null;
        if (head == null) {
            // Si es el único nodo en la lista, o el primer nodo en la lista, se actualiza el enlace siguiente del nodo siguiente.
            return null;
        }
        // Si es un nodo intermedio, se actualizan los enlaces previos y siguientes de los nodos adyacentes.
        head.prev.setNext(null);
        Node current = null;
        while (current != null) {
            boolean id = false;
            if (current.id == id) {
                // Se ha encontrado el nodo a eliminar.
                current.remove();
                break;
            }
            current = current.getNext();
        }

        return null;
    }
//Verifico si el head está vacio. Si es así, no hay nada que eliminar.
//
//Verifico si la lista tiene sólo un elemento (es decir, si el head no tiene un nodo siguiente ni anterior). En ese caso, eliminamos el único nodo de la lista y devolvemos null como nuevo head.
//
//Para eliminar el primero:
//
//Asignamos el siguiente nodo del head como el nuevo head.
//Establecemos el anterior del nuevo head como null.
//Establecemos el siguiente del head como null.
//Devolvemos el nuevo head.
//Para eliminar el último:
//
//Creamos un nuevo nodo llamado current y lo establecemos como el head.
//Recorremos la lista hasta que current tenga un nodo siguiente nulo
//Establecemos el siguiente del nodo anterior al current como null.
//Establecemos el anterior del current como null.
//Devolvemos el head.
//Para eliminar el de la mitad:
//
//Creamos dos nuevos nodos llamados slow y fast, y los establecemos ambos como el head.
//Avanzamos slow un nodo a la vez y fast dos nodos a la vez hasta que fast alcance el final de la lista o el nodo anterior al final de la lista.
//Si la lista tiene un número par de nodos, eliminamos el nodo en la posición N/2+1. Si la lista tiene un número impar de nodos, eliminamos el nodo en la posición (N+1)/2.
//Establecemos el siguiente del nodo anterior a slow como el siguiente del nodo fast.
//Establecemos el anterior del nodo siguiente a slow como el anterior del nodo fast.
//Establecemos el anterior y el siguiente del nodo fast como null.
//Devolvemos el head.
public static NodeDE deleteNodes(NodeDE head) {
    // Si la lista está vacía, no hay nada que eliminar
    if (head == null) {
        return null;
    }
    // Si la lista tiene sólo un elemento, eliminamos el único nodo y devolvemos null
    if (head.getNext() == null && head.prev == null) {
        return null;
    }
    // Eliminar el primer nodo
    NodeDE newHead = head.getNext();
    newHead.prev = null;
    head.setNext(null);
    if (head == null) {
        return null;
    }

    // Eliminar el último nodo
    NodeDE current = head;
    while (current.getNext() != null) {
        current = current.getNext();
    }
    current.prev.setNext(null);
    current.prev = null;

    // Eliminar el nodo de la mitad
    NodeDE slow = head;
    NodeDE fast = head;
    while (fast.getNext() != null && fast.getNext().getNext() != null) {
        slow = slow.getNext();
        fast = fast.getNext().getNext();
    }
    if (fast.getNext() == null) { // lista de longitud par
        slow.setNext(slow.getNext().getNext());
        slow.getNext().prev = slow;
    } else { // lista de longitud impar
        slow.setNext(fast.getNext());
        fast.getNext().prev = slow;
    }
    fast.prev = null;
    fast.setNext(null);

    return newHead;
}

}




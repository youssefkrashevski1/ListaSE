package co.edu.umanizales.tads.model;

import lombok.Data;
@Data
public class ListDE {

    private NodeDE head;
    private NodeDE tail;
    private int size;
//Comprueba si la cabeza de la lista (head) no es nula.
//Si la cabeza no es nula, crea una variable temporal (temp) y asígnale el valor de la cabeza.
//Mientras el siguiente nodo de temp no sea nulo, avanza al siguiente nodo.
//Después del bucle, estás parado en el último nodo de la lista.
//Crea un nuevo nodo (newNodeDE) con el objeto pet como su valor.
//Establece el siguiente nodo del último nodo (temp) como el nuevo nodo (newNodeDE).
//Si la cabeza era nula en el paso 1, crea un nuevo nodo de lista doblemente enlazada (head) con un valor nulo.
//Incrementa el tamaño de la lista.
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
    //Crea un nuevo nodo (temp) y asígnale el valor de la cabeza (head) de la lista.
    //Si la cabeza no es nula, avanza al siguiente nodo hasta llegar al último nodo de la lista.
    //Si el último nodo (temp) no es nulo, establece su siguiente nodo (next) como la cabeza del nodo siguiente.
    //Si el último nodo no es nulo, establece la cabeza (head) del último nodo como nula y actualiza la cola (tail) de la lista con el último nodo.
    //Incrementa el tamaño de la lista.
    public void Concat() {
    }
    // Constructor vacío
    public ListDE() {
        head = null;
        tail = null;
        size = 0;
    }
//Comprueba si la cabeza de la lista (head) es nula.
//Si la cabeza es nula, asigna el nodo recibido como parámetro tanto a la cabeza (head) como a la cola (tail) de la lista.
//Si la cabeza no es nula, establece el siguiente nodo del último nodo (tail) como el nodo recibido.
//Establece el nodo anterior (prev) del nodo recibido como el último nodo (tail).
//Actualiza la cola de la lista (tail) con el nodo recibido.
//Incrementa el tamaño de la lista.
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
//Inicia un bucle mientras current no sea nulo.
//Dentro del bucle, verifica si current es igual al nodo que se desea eliminar.
//Si current es igual al nodo a eliminar:
//Verifica si el nodo anterior (prev) de current es nulo. En ese caso, el nodo a eliminar es la cabeza de la lista, por lo que actualizamos la cabeza (head) al siguiente nodo.
//Si el nodo anterior (prev) de current no es nulo, establecemos el siguiente nodo (next) del nodo anterior como el siguiente nodo de current.
//Verifica si el siguiente nodo (next) de current es nulo. En ese caso, el nodo a eliminar es la cola de la lista, por lo que actualizamos la cola (tail) al nodo anterior (prev) de current.
//Si el siguiente nodo (next) de current no es nulo, actualizamos el nodo anterior (prev) del siguiente nodo de current al nodo anterior de current.
//Reducimos el tamaño de la lista en 1.
//Rompemos el bucle.
//Si no se encontró el nodo a eliminar, avanzamos al siguiente nodo de la lista actualizando current al siguiente nodo (getNext()).
//Finaliza el método.
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
//Verifica si el índice (index) está fuera de los límites válidos (menor que 0 o mayor o igual al tamaño de la lista). Si es así, lanza una excepción Exception indicando que el índice está fuera de los límites.
//Crea una variable current y asígnale el valor de la cabeza de la lista (head).
//Inicia una variable i en 0 para realizar un seguimiento del índice actual.
//Inicia un bucle mientras i sea menor que el índice deseado:
//Avanza al siguiente nodo de current asignándole el valor del siguiente nodo (getNext()).
//Incrementa i en 1.
//Al salir del bucle, current apuntará al nodo en la posición del índice deseado.
//Devuelve current.
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
//Inicia un bucle mientras current no sea nulo:
//Asigna el nodo anterior (prev) de current al nodo siguiente (getNext()) de current.
//Asigna el nodo anterior (temp) al nodo anterior (prev) de current.
//Asigna current al siguiente nodo (getNext()) del nodo original.
//Asigna temp a current.
//Avanza al siguiente nodo de la lista.
//Después del bucle, temp apuntará al último nodo original de la lista.
//Actualiza la cabeza de la lista (head) con el nodo temp, que es ahora el último nodo de la lista.
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
//Inicia un bucle mientras current no sea nulo:
//Imprime el valor del nodo actual (current.value).
//Avanza al siguiente nodo de la lista asignando a current el valor del siguiente nodo (getNext()).
//Imprime una nueva línea para separar la salida.
    // Mostrar la lista en orden de inicio a fin
    public void show() {
        NodeDE current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.getNext();
        }
        System.out.println();
    }
//Verifica si la cabeza de la lista (head) es nula. Si es nula, lanza una excepción Exception indicando que la lista enlazada está vacía.
//Crea una variable malePet y asígnale el valor nulo.
//Crea un nuevo nodo newNode1 con un valor nulo.
//Crea una variable femalePet y asígnale el valor nulo.
//Crea un nuevo nodo newNode2 con un valor nulo.
//Recorre la lista hasta llegar al último nodo (cuando current.getNext() sea nulo):
//Actualiza current al siguiente nodo de la lista (current.getNext()).
//Establece newNode1 como el siguiente nodo de head y establece head como el nodo newNode1.
//Establece newNode2 como el siguiente nodo de current y establece current como el nodo anterior (prev) de newNode2.
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
//Verifica si la cabeza de la lista (head) es nula o si solo contiene un elemento. Si se cumple alguna de estas condiciones, lanza una excepción Exception indicando que la lista está vacía o solo contiene un elemento.
//Crea una variable lastMale y asígnale el valor nulo.
//Inicia un bucle mientras current no sea nulo:
//Verifica si el género de la mascota actual (current.pet.getGender()) es masculino ('M').
//Si es masculino:
//Verifica si lastMale es nulo. En ese caso, asigna current a lastMale.
//Si lastMale no es nulo, busca el siguiente nodo femenino (nextFemale) a partir de lastMale.getNext(). Recorre los nodos hasta encontrar un nodo femenino o hasta llegar al final de la lista.
//Si nextFemale es nulo, lanza una excepción Exception indicando que no se encontraron mascotas femeninas para combinar con las mascotas masculinas.
//Crea un nuevo nodo newMale con la mascota actual de current.
//Establece newMale como el siguiente nodo de lastMale y establece lastMale como el nodo anterior (prev) de newMale.
//Establece newMale como el siguiente nodo de nextFemale y establece nextFemale como el nodo anterior (prev) de newMale.
//Actualiza lastMale con el valor de newMale.
//Si el género de la mascota actual es femenino ('F'), no se realiza ninguna acción.
//Si el género de la mascota actual no es ni masculino ni femenino, lanza una excepción Exception indicando un género de mascota no válido.
//Avanza al siguiente nodo de la lista asignando a current el valor del siguiente nodo (getNext()).
//Devuelve null.
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
//Verifica si la cabeza de la lista (head) es nula. Si es nula, lanza una excepción Exception indicando que la lista está vacía.
//Crea una variable current y asígnale el valor de la cabeza de la lista (head).
//Inicia un bucle mientras current no sea nulo:
//Verifica si la edad de la mascota actual (current.getPet().getAge()) es igual a la edad a eliminar (ageToDelete).
//Si son iguales:
//Verifica si current es igual a head. En ese caso, asigna el siguiente nodo de current a head. Si head no es nulo, establece su nodo anterior (prev) como nulo.
//Si current no es igual a head, establece el siguiente nodo de current.getPrev() como el siguiente nodo de current. Si el siguiente nodo de current no es nulo, establece su nodo anterior (prev) como el nodo anterior de current.
//Avanza al siguiente nodo de la lista asignando a current el valor del siguiente nodo (getNext()).
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
//Verifica si la cabeza de la lista (head) es nula. Si es nula, lanza una excepción Exception indicando que la lista está vacía.
//Crea una variable totalAge y asígnale el valor 0.
//Crea una variable count y asígnale el valor 0.
//Inicia un bucle mientras current no sea nulo:
//Verifica si la mascota de current es nula. Si es nula, lanza una excepción Exception indicando que la lista contiene un nodo con una mascota nula.
//Incrementa totalAge sumándole la edad de la mascota de current.
//Incrementa count en 1.
//Avanza al siguiente nodo de la lista asignando a current el valor del siguiente nodo (getNext()).
//Verifica si count es igual a 0. Si es igual a 0, lanza una excepción Exception indicando que la lista está vacía.
//Devuelve el promedio de edad calculado como (double) totalAge / count.
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
//Verifica si el nodo de mascota (petNode) es nulo. Si es nulo, lanza una excepción IllegalArgumentException indicando que el nodo de mascota no puede ser nulo.
//Verifica si el número de posiciones para mover hacia adelante (numPositions) es negativo. Si es negativo, lanza una excepción IllegalArgumentException indicando que el número de posiciones no puede ser negativo.
//Inicia un bucle desde 0 hasta numPositions - 1:
//Verifica si el siguiente nodo de petNode es nulo. Si es nulo, lanza una excepción IllegalArgumentException indicando que no se puede mover la mascota hacia adelante por la cantidad de posiciones especificada porque se ha alcanzado el final de la lista.
//Asigna a petNode el valor del siguiente nodo (getNext()).
//Fin del método.
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
    //Verifica si la cabeza de la lista (head) es nula. Si es nula, lanza una excepción Exception indicando que la lista está vacía.
    //Crea una variable currentPosition y asígnale el valor 1.
    //Inicia un bucle mientras currentNode no sea nulo y currentPosition no sea igual a position:
    //Avanza al siguiente nodo de la lista asignando a currentNode el valor del siguiente nodo (getNext()).
    //Incrementa currentPosition en 1.
    //Verifica si currentNode es nulo. Si es nulo, lanza una excepción Exception indicando que la posición está fuera de rango.
    //Verifica si currentNode es igual a la cabeza de la lista (head). Si es igual, asigna a head el valor del siguiente nodo de currentNode (getNext()).
    //Verifica si currentNode es igual a la cola de la lista (tail). Si es igual, asigna a tail el valor del nodo anterior de currentNode (getPrevious()).
    //Verifica si el nodo anterior de currentNode no es nulo. Si no es nulo, asigna al siguiente nodo del nodo anterior de currentNode el valor del siguiente nodo de currentNode (getNext()).
    //Verifica si el siguiente nodo de currentNode no es nulo. Si no es nulo, asigna al nodo anterior del siguiente nodo de currentNode el valor del nodo anterior de currentNode (getPrevious()).
    //Decrementa size en 1.
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
    //Define un arreglo AGE_RANGES que contiene los rangos de edades y crea un arreglo ageCount para contar el número de mascotas en cada rango. El último elemento de ageCount contará todas las mascotas mayores a 19 años.
    //Itera sobre la lista de mascotas desde la cabeza (head) hasta la cola (tail).
    //Obtén la edad de cada mascota y verifica en qué rango se encuentra.
    //Si la edad es menor que el primer rango o mayor que el último rango, incrementa el contador del último elemento de ageCount, que cuenta las mascotas mayores a 19 años.
    //De lo contrario, itera sobre los rangos de edades y encuentra el rango al que pertenece la mascota. Incrementa el contador correspondiente en ageCount y rompe el bucle.
    //Genera una cadena de texto report usando un StringBuilder. Itera sobre los rangos de edades y agrega al report la cantidad de mascotas en cada rango.
    //Verifica si hay algún rango de edades sin mascotas (contador igual a cero) y lanza una excepción AgeOutOfRangeException en ese caso.
    //Devuelve el report como una cadena de texto.
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
    //Inicializa dos nodos, current y last, con la cabeza (head) y la cola (tail).
    //Itera sobre la lista de mascotas mientras el nodo current no sea nulo.
    //Verifica si el nombre de la mascota en el nodo actual (current.getData().getName()) comienza con la letra dada.
    //Si el nombre cumple con la condición, mueve el nodo al final de la lista.
    //Si el nodo actual es la cabeza, actualiza la cabeza (head) al siguiente nodo y establece el enlace previo a nulo.
    //De lo contrario, actualiza los enlaces previo y siguiente del nodo actual para saltar el nodo en la lista.
    //Establece el enlace previo del nodo actual al último nodo (last) y el enlace siguiente a nulo.
    //Si el último nodo no es nulo, actualiza su enlace siguiente para apuntar al nodo actual.
    //Actualiza el nodo last al nodo actual.
    //Establece current como la cabeza para reiniciar el bucle desde el principio.
    //Si el nombre no cumple con la condición, pasa al siguiente nodo (current = current.getNext()).
    //Repite los pasos 3 a 5 hasta que se hayan revisado todos los nodos.
    //Una vez finalizada la iteración, los nodos con nombres que comienzan con la letra dada se habrán movido al final de la lista.
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




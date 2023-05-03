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
    private void concat(ListDE girlList) {
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
        current.value = node.value;
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
    public void addPetToStartAndEnd(NodeDE head, Pet malePet, Pet femalePet) throws Exception {
        if (head == null) {
            throw new Exception("The linked list is empty.");
        }

        NodeDE newNode1 = new NodeDE(malePet);
        NodeDE newNode2 = new NodeDE(femalePet);

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
    public void getReportKidsByLocationGendersByAge(byte age, ReporPetLocationGenderDTO report){
        if(head !=null){
            NodeDE temp = this.head;
            while(temp!=null){
                if(temp.getData().getAge()>age){
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }
}


package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.CityGenderReportDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

@Data
public class ListSE {
    private static Node head;
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
        if (head != null) {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
    }

    public void orderBoysToStart() {
        if (head != null) {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
    }

    public static Node getHead() {
        return null;
    }

    public void changeExtremes() {
        if (head != null && head.getNext() != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = head.getData();
            head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void getReportKidsByLocationGendersByAge(byte age, CityGenderReportDTO report){
        if(head !=null){
            Node temp = head;
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

    public static void addKid(Kid kid) {
        if (head == null) {
        } else {
            Kid current = head.getData();
            Kid prev = null;
            while (current != null) {
                if (current.isBoy && kid.isBoy) {
                    // add boy to the beginning of the list
                    kid.setNext(head.getData());
                    return;
                } else if (!current.isBoy && !kid.isBoy) {
                    // add girl to the end of the list
                    if (current.getNext() == null) {
                        current.setNext(kid);
                        return;
                    }
                }
                prev = current;
                current = current.getNext();
            }
            // add girl to the end of the list if there were no girls in the list

        }
    }
    public  void interleaveBoyGirl() {
        if (head == null || head.getNext() == null) {
            return; // nothing to interleave
        }

        Node boyHead = null;
        Node girlHead = null;
        Node boyTail = null;
        Node girlTail = null;
        Node current = head;
        boolean isBoy = true;

        // split the list into two separate lists for boys and girls
        while (current != null) {
            if (isBoy) {
                if (boyHead == null) {
                    boyHead = current;
                    boyTail = current;
                } else {
                    boyTail.setNext(current);
                    boyTail = current;
                }
            } else {
                if (girlHead == null) {
                    girlHead = current;
                    girlTail = current;
                } else {
                    girlTail.setNext(current);
                    girlTail = current;
                }
            }
            current = current.getNext();
            isBoy = !isBoy; // switch between boy and girl
        }

        // merge the boy and girl lists by alternating between them
        boyTail.setNext(girlHead);
        girlTail.setNext(null); // terminate the new list

        Node boyCurrent = boyHead;
        Node girlCurrent = girlHead;

        while (boyCurrent != null && girlCurrent != null) {
            Node nextBoy = boyCurrent.getNext();
            Node nextGirl = girlCurrent.getNext();
            boyCurrent.setNext(girlCurrent);
            girlCurrent.setNext(nextBoy);
            boyCurrent = nextBoy;
            girlCurrent = nextGirl;
        }


    }
    public  double getAverageKidAge() throws ListSEException {
        if (head == null) {
            throw new ListSEException("La lista está vacía.");
        }

        int sum = 0;
        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.getData() != null) {
                Kid kid = (Kid) current.getData();
                sum += kid.getAge();
                count++;
            }
            current = current.getNext();
        }

        if (count == 0) {
            throw new ListSEException("La lista no contiene niños..");
        }

        return (double) sum / count;
    }
    public  void advance() throws Exception {
        Node current = head;
        int count = 0;
        int numPositions;
        while (current != null && count < numPositions) {
            current = current.getNext();
            count++;
        }
        if (count < numPositions) {
            throw new Exception("No se puede avanzar " + numPositions + " posiciones, la lista solo tiene " + count + " elementos.");
        }
        head = current;
    }
    public void losePositions() throws Exception {
        Node current = head;
        Node prev = null;
        int count = 0;
        int numPositions;
        while ((current != null) && (count < numPositions)) {
            prev = current;
            current = current.getNext();
            count++;
        }
        if (count < numPositions) {
            throw new Exception("No se puede perder " + numPositions + " posiciones, la lista solo tiene " + count + " elementos.");
        }
        if (prev != null) {
            assert current != null;
            prev.setNext(current.getNext());
        } else {
            assert current != null;
            head = current.getNext();
        }
    }

    public void generateAgeReport() {
        int[] ageCounts = new int[19];
        System.out.println("Age Report:");
        for (int i = 0; i < ageCounts.length; i++) {
            if (ageCounts[i] > 0) {
                System.out.println("Age " + i + ": " + ageCounts[i] + " kids");
            }
        }
    }
    public void moveKidsToEnd() throws Exception {
        if (head == null) {
            throw new Exception("la lista esta vacia");
        }
        Kid prev = null;
        Kid curr = head.getData();
        Kid end = null;
        char letter = 0;
        while (curr != null) {
            if (curr.getName().charAt(0) == letter) {

                if (end == null) {
                    end = curr;
                } else {
                    end.setNext(curr);
                    end = curr;
                }
                curr = curr.getNext();
                end.setNext(null);
            } else {
                prev = curr;
                curr = curr.getNext();
            }
        }
        if (end == null) {
            throw new Exception("No hay niños con nombre que comiencen con " + letter + " found");
        }
    }

}



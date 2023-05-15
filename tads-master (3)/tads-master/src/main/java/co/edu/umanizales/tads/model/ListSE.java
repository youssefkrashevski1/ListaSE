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
            head = getHead();
        }
    }
//Verificar si head no es null (es decir, si la lista enlazada no está vacía).
//Si head no es null:
//a. Crear una nueva lista enlazada llamada listCp.
//b. Establecer un puntero temp en el nodo inicial (head) de la lista original.
//c. Iniciar un bucle mientras temp no sea null:
//- Verificar si el género de los datos en el nodo actual (temp.getData().getGender()) es 'M' (indicando un niño).
//- Si el género es 'M':
//- Agregar los datos del nodo actual al principio de la lista listCp utilizando el método addToStart().
//- De lo contrario (si el género no es 'M'):
//- Agregar los datos del nodo actual al final de la lista listCp utilizando el método add().
//- Avanzar al siguiente nodo en la lista original (temp = temp.getNext()).
//d. Establecer head de la lista original como el nodo inicial de la lista listCp (head = listCp.getHead()).
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
//Verificar si head no es null y si head.getNext() no es null (es decir, si la lista enlazada tiene al menos dos nodos).
//Si se cumple la condición anterior:
//a. Establecer un puntero temp en el nodo inicial (head) de la lista.
//b. Iniciar un bucle mientras el siguiente nodo de temp no sea null:
//- Avanzar al siguiente nodo en la lista (temp = temp.getNext()).
//c. Al final del bucle, temp estará en el último nodo de la lista.
//d. Intercambiar los datos del nodo inicial (head) con los datos del último nodo (temp):
//- Crear una copia de los datos del nodo inicial (Kid copy = head.getData()).
//- Establecer los datos del nodo inicial (head.setData()) como los datos del último nodo (temp.getData()).
//- Establecer los datos del último nodo (temp.setData()) como la copia de los datos del nodo inicial (copy).
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
//Inicializar count como 0 para mantener el conteo de los niños encontrados.
//Verificar si head no es null (es decir, si la lista enlazada no está vacía).
//Si head no es null:
//a. Establecer un puntero temp en el nodo inicial (head) de la lista.
//b. Iniciar un bucle mientras temp no sea null:
//- Verificar si el código de ubicación (getLocation().getCode()) de los datos en el nodo actual es igual al código proporcionado (code) como parámetro.
//- Si los códigos son iguales, incrementar el contador (count++).
//- Avanzar al siguiente nodo en la lista (temp = temp.getNext()).
//Devolver el valor de count, que representa el número de niños encontrados con el código de ubicación especificado.
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
//Verificar si head no es null (es decir, si la lista enlazada no está vacía).
//Si head no es null:
//a. Establecer un puntero temp en el nodo inicial (head) de la lista.
//b. Iniciar un bucle mientras temp no sea null:
//- Verificar si la edad (getAge()) de los datos en el nodo actual es mayor que la edad proporcionada (age) como parámetro.
//- Si la edad es mayor, llamar al método updateQuantity() del objeto report para actualizar la cantidad correspondiente al nombre de ubicación (getLocation().getName()) y género (getGender()) de los datos en el nodo actual.
//- Avanzar al siguiente nodo en la lista (temp = temp.getNext()).
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
//Verificar si head es null (es decir, si la lista enlazada está vacía).
//a. Si head es null, no se realiza ninguna acción ya que el nuevo niño kid será el primer nodo de la lista.
//En caso contrario:
//a. Establecer current como los datos del nodo inicial (head.getData()).
//b. Establecer prev como null.
//c. Iniciar un bucle mientras current no sea null:
//- Verificar si current es un niño y kid también es un niño.
//- Si ambos son niños, se agrega el niño kid al principio de la lista:
//- Establecer kid como el siguiente nodo después de head.getData() (kid.setNext(head.getData())).
//- Terminar el método utilizando return.
//- En caso contrario, si current no es un niño y kid tampoco es un niño:
//- Verificar si current.getNext() es null, lo que indica que current es el último nodo de la lista.
//- Si current.getNext() es null, se agrega el niño kid al final de la lista:
//- Establecer kid como el siguiente nodo después de current (current.setNext(kid)).
//- Terminar el método utilizando return.
//- Actualizar prev a current.
//- Actualizar current al siguiente nodo en la lista (current = current.getNext()).
//d. Si se sale del bucle, significa que no se encontraron niñas en la lista.
//- Agregar el niño kid al final de la lista:
//- Establecer kid como el siguiente nodo después de prev (prev.setNext(kid)).
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
    //Verificar si head es null o si head.getNext() es null. Si se cumple esta condición, no hay elementos suficientes para intercalar y se retorna.
    //Crear cuatro punteros: boyHead, girlHead, boyTail y girlTail, inicializándolos como null.
    //Crear un puntero current y establecerlo en el nodo inicial (head) de la lista.
    //Crear una variable booleana isBoy y establecerla como true.
    //Separar la lista en dos listas separadas para niños y niñas:
    //a. Iniciar un bucle mientras current no sea null:
    //- Verificar si isBoy es true (indicando un niño).
    //- Si isBoy es true:
    //- Verificar si boyHead es null.
    //- Si es null, establecer boyHead y boyTail como current.
    //- Si no es null, establecer el siguiente nodo de boyTail como current y actualizar boyTail a current.
    //- En caso contrario (si isBoy es false, indicando una niña):
    //- Verificar si girlHead es null.
    //- Si es null, establecer girlHead y girlTail como current.
    //- Si no es null, establecer el siguiente nodo de girlTail como current y actualizar girlTail a current.
    //- Avanzar al siguiente nodo en la lista (current = current.getNext()).
    //- Cambiar el valor de isBoy alternando entre true y false (isBoy = !isBoy).
    //Unir las listas de niños y niñas alternándolas:
    //a. Establecer el siguiente nodo de boyTail como girlHead.
    //b. Establecer el siguiente nodo de girlTail como null para terminar la nueva lista.
    //Crear dos punteros boyCurrent y girlCurrent, inicializándolos como boyHead y girlHead, respectivamente.
    //Iniciar un bucle mientras boyCurrent y girlCurrent no sean null:
    //a. Crear dos punteros nextBoy y nextGirl para almacenar los siguientes nodos de boyCurrent y girlCurrent, respectivamente.
    //b. Establecer el siguiente nodo de boyCurrent como girlCurrent.
    //c. Establecer el siguiente nodo de girlCurrent como nextBoy.
    //d. Actualizar boyCurrent a nextBoy y girlCurrent a nextGirl.
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
    //Verificar si head es null. Si se cumple esta condición, lanzar una excepción ListSEException con el mensaje "La lista está vacía".
    //Inicializar count como 0 para llevar el conteo de los niños encontrados.
    //Crear un puntero current y establecerlo en el nodo inicial (head) de la lista.
    //Iniciar un bucle mientras current no sea null:
    //a. Verificar si los datos en el nodo actual (current.getData()) no son null.
    //b. Si los datos no son null, convertirlos a un objeto de tipo Kid y asignarlo a la variable kid.
    //c. Incrementar el contador (count++).
    //d. Avanzar al siguiente nodo en la lista (current = current.getNext()).
    //Verificar si count es igual a 0. Si se cumple esta condición, lanzar una excepción ListSEException con el mensaje "La lista no contiene niños".
    //Si no se lanza ninguna excepción, el método finaliza.
    public void getAverageKidAge() throws ListSEException {
        if (head == null) {
            throw new ListSEException("La lista está vacía.");
        }

        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.getData() != null) {
                Kid kid = (Kid) current.getData();
                count++;
            }
            current = current.getNext();
        }

        if (count == 0) {
            throw new ListSEException("La lista no contiene niños..");
        }
    }
//Crear un puntero current y establecerlo en el nodo inicial (head) de la lista.
//Inicializar count como 0 para llevar el conteo de las posiciones avanzadas.
//Inicializar numPositions con el número de posiciones que se desean avanzar.
//Iniciar un bucle mientras current no sea null y count sea menor que numPositions:
//a. Avanzar al siguiente nodo en la lista (current = current.getNext()).
//b. Incrementar el contador (count++).
//Verificar si count es menor que numPositions. Si se cumple esta condición, lanzar una excepción Exception con el mensaje "No se puede avanzar numPositions posiciones, la lista solo tiene count elementos".
//Establecer head como el nodo en el que se encuentra el puntero current.
//El método finaliza.
    public  void advance() throws Exception {
        Node current = head;
        int count = 0;
        int numPositions = 0;
        while (current != null && count < numPositions) {
            current = current.getNext();
            count++;
        }
        if (count < numPositions) {
            throw new Exception("No se puede avanzar " + numPositions + " posiciones, la lista solo tiene " + count + " elementos.");
        }
        head = current;
    }
//Crear un puntero current y establecerlo en el nodo inicial (head) de la lista.
//Crear un puntero prev y establecerlo como null.
//Inicializar count como 0 para llevar el conteo de las posiciones perdidas.
//Inicializar numPositions con el número de posiciones que se desean perder.
//Iniciar un bucle mientras current no sea null y count sea menor que numPositions:
//a. Establecer prev como current.
//b. Avanzar al siguiente nodo en la lista (current = current.getNext()).
//c. Incrementar el contador (count++).
//Verificar si count es menor que numPositions. Si se cumple esta condición, lanzar una excepción Exception con el mensaje "No se puede perder numPositions posiciones, la lista solo tiene count elementos".
//Verificar si prev no es null:
//a. Verificar que current no sea null (comprobar la aserción).
//b. Establecer el siguiente nodo de prev como el siguiente nodo de current (prev.setNext(current.getNext())).
//En caso contrario (si prev es null):
//a. Verificar que current no sea null (comprobar la aserción).
//b. Establecer head como el siguiente nodo de current (head = current.getNext()).
//El método finaliza.
    public void losePositions() throws Exception {
        Node current = head;
        Node prev = null;
        int count = 0;
        int numPositions = 0;
        while ((current != null) && (numPositions > count)) {
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
//Crear un arreglo de enteros llamado ageCounts con una longitud de 19 elementos. Este arreglo se utilizará para contar la cantidad de niños en cada edad.
//Imprimir el encabezado del informe con el mensaje "Age Report:".
//Iniciar un bucle desde 0 hasta ageCounts.length - 1 (inclusive) utilizando la variable de iteración i:
//a. Verificar si el contador de edad en la posición i del arreglo ageCounts es mayor que 0.
//b. Si se cumple la condición, imprimir la línea del informe con el mensaje "Age i: ageCounts[i] kids".
//El método finaliza.
    public void generateAgeReport() {
        int[] ageCounts = new int[19];
        System.out.println("Age Report:");
        for (int i = 0; i < ageCounts.length; i++) {
            if (ageCounts[i] > 0) {
                System.out.println("Age " + i + ": " + ageCounts[i] + " kids");
            }
        }
    }
//Verificar si head es null. Si se cumple esta condición, lanzar una excepción Exception con el mensaje "La lista está vacía".
//Crear un puntero prev y establecerlo como null. Este puntero se utilizará para realizar el seguimiento del nodo anterior al nodo actual (curr).
//Crear un puntero curr y establecerlo como el dato almacenado en el nodo inicial (head.getData()). Este puntero se utilizará para recorrer la lista de niños.
//Crear un puntero end y establecerlo como null. Este puntero se utilizará para mantener el rastro del último niño que cumple con la condición.
//Inicializar una variable letter como 0. Esta variable almacenará la letra con la que se compara el primer carácter del nombre del niño.
//Iniciar un bucle mientras curr no sea null:
//a. Verificar si el primer carácter del nombre del niño (curr.getName().charAt(0)) es igual a letter.
//b. Si se cumple la condición, realizar lo siguiente:
//- Verificar si end es null.
//- Si es null, establecer end como curr.
//- Si no es null, establecer el siguiente nodo de end como curr y actualizar end como curr.
//- Avanzar al siguiente nodo en la lista (curr = curr.getNext()).
//- Establecer el siguiente nodo de end como null (end.setNext(null)).
//c. Si no se cumple la condición, realizar lo siguiente:
//- Establecer prev como curr.
//- Avanzar al siguiente nodo en la lista (curr = curr.getNext()).
//Verificar si end es null. Si se cumple esta condición, lanzar una excepción Exception con el mensaje "No hay niños con nombre que comiencen con letter found".
//El método finaliza.
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



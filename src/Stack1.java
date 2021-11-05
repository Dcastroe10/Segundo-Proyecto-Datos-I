import java.util.ArrayList;
import java.util.List;

public class Stack1{
    private List<String> list;
    private int size;
    public String topper;

    /**
     * Método constructor de la clase Stack1 que inicializa un Array, el tamaño del stack y el último elemento agregado
     */

    public Stack1(){
        list = new ArrayList<String>();
        size = 0;
        topper = null;
    }

    /**
     *
     * @return int, el cual es la cantidad de elementos en el stack
     */

    public int getSize(){
        return size;
    }

    /**
     * Agrega un dato al stack el cual va a ser el primero en salir en caso de hacer un pop
     * @param element Es el dato que queremos agregar al stack
     */

    public void push (String element){
        list.add(element);
        topper = element;
        size++;
    }

    /**
     * Nos devuelve la cabeza del stack
     * @return String el cual es el último elemento agregado al stack
     */

    public String pop(){
        String maximum = list.get(size-1);
        list.remove(size-1);
        size--;
        topper = size == 0 ? null : list.get(size-1);
        return maximum;
    }
}

public class Node {
    //Atributos de la clase
    public Object data; //lo que guarda el nodo
    public Node prev;  // puntero hacia el nodo anterior
    public Node next;  // puntero hacia el nodo siguiente

    // METODO CONSTRUCTOR
    public Node(Object data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    // Metodo para obtener el dato asociado al nodo
    public Object getData(){
        return this.data;
    }

    // Metodo para modificar lo que guarda el nodo
    public void setData(Object data){
        this.data = data;
    }

    // Metodo para conocer a quien apunta puntero prev
    public Node getPrev(){
        return this.prev;
    }
    // Metodo para modificar a quien apunta puntero prev
    public void setPrev(Node node){
        this.prev = node;
    }

    // Metodo para conocer a quien apunta puntero next
    public Node getNext(){
        return this.next;
    }
    // Metodo para modificar a quien apunta puntero next
    public void setNext(Node node){
        this.next = node;
    }
}

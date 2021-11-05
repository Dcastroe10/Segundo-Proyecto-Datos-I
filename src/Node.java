public class Node {
    public Object data;
    public Node left;
    public Node right;

    /**
     * Crea un nodo con los datos ingresados, se utiliza para la creación del árbol de expresión
     * @param data
     */
    public Node(Object data){
        this.data = data;
        this.right = null;
        this.left = null;
    }

    public Node(){
        this.right = null;
        this.left = null;
    }

    /**
     *Obtiene el dato almacenado en el nodo
     * @return
     */
    public Object getData(){
        return this.data;
    }

    /**
     * Cambia el tipo dato del nodo
     * @param data
     */
    public void setData(Object data){
        this.data = data;
    }

    /**
     * Obtiene el hijo izquierdo del nodo
     * @return
     */
    public Node getLeft(){
        return this.left;
    }

    /**
     * Se le asigna el hijo izquierdo al nodo
     * @param node
     */
    public void setLeft(Node node){
        this.left = node;
    }

    /**
     * Obtiene el hijo derecho del nodo
     * @return
     */
    public Node getRight(){
        return this.right;
    }

    /**
     * Se asigna el hijo de la derecha al nodo
     * @param node
     */
    public void setRight(Node node){
        this.right = node;
    }

}

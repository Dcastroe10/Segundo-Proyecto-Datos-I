public class Node {
    public Object data;
    public Node left;
    public Node right;
    public String type;

    public Node(Object data){ //add type, si es operando o numero
        this.data = data;
        this.right = null;
        this.left = null;
        this.type = "";
    }

    public Object getData(){
        return this.data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public Node getLeft(){
        return this.left;
    }

    public void setLeft(Node node){
        this.left = node;
    }

    public Node getRight(){
        return this.right;
    }

    public void setRight(Node node){
        this.right = node;
    }

}

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
    public Node(){
        this.right = null;
        this.left = null;
        this.type= "";
    }

    public boolean operador(char f){
        if (f == '+'){
            return true;
        }else if (f == '-'){
            return true;
        }else if (f == '*'){
            return true;
        }else if (f == '/'){
            return true;
        }else if (f == '%'){
            return true;
        }else{
            return false;
        }
    }

    public Object getData(){
        return this.data;
    }

    public void setData(Object data){
        this.data = data;
        if (operador(String.valueOf(data).charAt(0))){
            setType(String.valueOf(String.valueOf(data).charAt(0)));
            //this.type=String.valueOf(String.valueOf(data).charAt(0));
        }else {
            setType("number");
            //this.type = "number";
        }
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
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

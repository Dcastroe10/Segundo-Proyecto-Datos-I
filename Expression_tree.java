import java.util.ArrayList;

public class Expression_tree {
    public String expression = "";
    private Node root;

    public Expression_tree (String data){
        ArrayList<Node> operators = new ArrayList<Node>();
        ArrayList<Node> numbers = new ArrayList<Node>();
        for (int i = 0; i< data.length(); i ++){
            char string_characters = data.charAt(i);
            if (string_characters >= '0' && string_characters <='9'){
                expression += string_characters;
            }else{
                numbers.add(new Node(expression));
                expression = "";
                operators.add(new Node(string_characters));
            }
        }
        numbers.add(new Node(expression));

        while (operators.size() > 0){
            Node left = numbers.remove(0);
            Node right = numbers.remove(0);
            Node opera = operators.remove(0);
            opera.setLeft(left);
            opera.setRight(right);
            numbers.add(0, opera);
        }
        root= numbers.get(0);
    }

    public void print_tree(){
        print_tree(this.root);
    }


    public void print_tree(Node root){
        if (root.getLeft() != null){
            print_tree(root.getLeft());
        }
        System.out.println(root.getData());
        if (root.getRight() != null){
            print_tree(root.getRight());
        }
    }

    public void solve(){
        solve(this.root);
    }

    public void solve(Node root){
        if (root.getLeft() != null){
            print_tree(root.getLeft());
        }
       System.out.println(root.getData());
        if (root.getRight() != null){
            print_tree(root.getRight());
        }
    }

    public void answer(String kekekeke) {
        int result = 0;
        for (int i = kekekeke.length()-1; i >= 0; i--){
            int op1=0;
            int op2=0;
            if (Character.getNumericValue(kekekeke.charAt(i)) <=9){
                op1 += Character.getNumericValue(kekekeke.charAt(i));
            }
            if (Character.getNumericValue(kekekeke.charAt(i)) <=9){
                op2 += Character.getNumericValue(kekekeke.charAt(i));
            }
            if (Character.toString(kekekeke.charAt(i-1)) == "+") {
                result += op1+op2;
            }
            //result += op1;

        }
        System.out.println(result);
    }

        /*
        System.out.println(this.expression);
        for (int i = this.expression.length()-1; i >= 0; i--){
            operation += Character.getNumericValue(this.expression.charAt(0));

        }
        */



    public static void main(String[] args) {
        Expression_tree ex = new Expression_tree("1+9");
        //ex.print_tree();
        System.out.println("--------------------------------");
        ex.answer("1+2");
    }

}

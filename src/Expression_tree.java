import java.util.Stack;

public class Expression_tree {

    public Expression_tree(String postfix){
        Stack<Node> stack = new Stack<Node>();
        int indice = 0;
        System.out.println(postfix);
        while (indice < postfix.length()) {
            Node nodo = new Node();
            if (postfix.charAt(indice) != 'P' && !operador(postfix.charAt(indice))) {
                String number = "";
                for (int i = indice; i < postfix.length(); i++) {
                    if (postfix.charAt(i) != 'P' && !operador(postfix.charAt(i))) {// && !operador(postfix.charAt(i))) {
                        number += postfix.charAt(i);
                    }
                    if (postfix.charAt(i) == 'P') {
                        break;
                    }
                    indice = i;
                }
                while (postfix.charAt(indice) != 'P') {
                    indice += 1;
                }
                nodo.setData(number);
                stack.push(nodo);
                //System.out.println("PUSHEDDDDDDD");
                //System.out.println(nodo.getData());
                continue;
            }


            if (operador(postfix.charAt(indice))){
                nodo.setData(postfix.charAt(indice));
                //System.out.println(nodo.getData()+" DATOS DEL NODO OPERADOR");
                //System.out.println(stack.peek().getData());
                //System.out.println(stack.peek().getData());
                nodo.setRight(stack.pop());
                nodo.setLeft(stack.pop());
                stack.push(nodo);
            }
            indice++;
        }
        System.out.println(stack.peek().getData()+ "ROOOT");

        Node root = stack.pop();
        //pruebas
        /*
        System.out.println("EEEEEEEEEE");
        System.out.println(pepe.getData());
        System.out.println(pepe.getLeft().getLeft().getData()+ "LEFTTT");
        System.out.println(pepe.getLeft().getRight().getData()+ "LEFTTT");
        System.out.println(pepe.getRight().getData()+"RIIIGHT");
        System.out.println("SE CELEBRA");
        //return nodo;
         */
        System.out.println("EMPIEZA LA SOLUCIÓN");
        //recorrer(root);
        solve(root);


    }

    public void recorrer(Node root){
        String result ="";
        if (root!=null){
            recorrer(root.getLeft());
            recorrer(root.getRight());
            System.out.println(root.getData());
            result+=root.getData();
        }
    }

    public int solve(Node root){
        Stack<Integer> stack = new Stack<Integer>();
        int result=0;
        if (root!= null && root.getLeft().getType()=="number" && root.getRight().getType()=="number"){
            System.out.println("EL CAREPICHA ENTRÓ");
            String left = String.valueOf(root.getLeft().getData());
            int operando_left =Integer.valueOf(left);
            String right = String.valueOf(root.getRight().getData());
            int operando_right = Integer.valueOf(right);

            System.out.println(operando_left);
            System.out.println(operando_right + "operador de la derecha");
            String tipo = root.getType();
            System.out.println(tipo);
            if (tipo.equals("+")){
                result+= operando_left + operando_right;
                System.out.println("FUCK");
            }
            if (tipo.equals("+")){
                result+= operando_left + operando_right;
                System.out.println("FUCK");
            }
            if (tipo.equals("-")){
                result+= operando_left - operando_right;
                System.out.println("FUCK");
            }
            if (tipo.equals("*")){
                result+= operando_left * operando_right;
                System.out.println("FUCK");
            }
            if (tipo.equals("/")){
                result+= operando_left / operando_right;
                System.out.println("FUCK");
            }
            if (tipo.equals("%")){
                result+= operando_left % operando_right;
                System.out.println("FUCK");
            }
        System.out.println(result);
        }
        if (root != null && root.getLeft().getData() != "number" && root.getRight().getData()!= "number" ) {
            System.out.println("PRAY GOD");
            String tipo = root.getType();
            System.out.println(tipo);
            if (tipo.equals("+")) {
                System.out.println("THANKS GOD");
                System.out.println(root.getLeft().getData());
                result += solve(root.getLeft()) + solve(root.getRight());
            }

        }
    System.out.println("El resultado final es: "+result);
    return result;
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


}

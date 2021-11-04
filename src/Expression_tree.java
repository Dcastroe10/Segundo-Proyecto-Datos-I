import java.util.ArrayList;
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
        //System.out.println("EMPIEZA LA SOLUCIÓN");
        //System.out.println(recorrer(root)+ "expresión del árbol");
        String recorrido = recorrer(root);
        System.out.println(solve(recorrido)+ "RESULTADOOOOOOO");
        //solve(recorrido);
        //solve_manual(root);


    }

    public String recorrer(Node root){
        String result ="";
        if (root!=null){
            result+=recorrer(root.getLeft());
            result+=recorrer(root.getRight());
            //System.out.println(root.getData());
            result+="P" + root.getData();
        }
        return "P" + result;
    }

    public int solve(String postfix){
        int indice = 0;
        int result = 0;
        Stack<String> stack = new Stack<String>();
        System.out.println("NUEVA ITERACIÓN");
        while (indice<postfix.length()-1){
                while (postfix.charAt(indice) == 'P') {
                    indice += 1;
                }

            if (!operador(postfix.charAt(indice))){
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
                System.out.println(number + "esteeeee es el operando");
                stack.push(number);
                indice++;
                continue;
            }

            if (operador(postfix.charAt(indice)) && !stack.empty()){
                System.out.println("calculoooo");
                String tipo = String.valueOf(postfix.charAt(indice));
                int operando_right = Integer.valueOf(stack.pop());
                int operando_left = Integer.valueOf(stack.pop());
                while (postfix.charAt(indice) == 'P') {
                    indice += 1;
                }
                if (tipo.equals("+")){
                    result+= operando_left + operando_right;
                    System.out.println(operando_left);
                    System.out.println("SUMA");
                    System.out.println(operando_right);
                    System.out.println("EL resultado de la subsuma es: "+ result);
                    //stack.push(String.valueOf(result));
                    System.out.println("PUSHED!!!");
                    indice++;
                    continue;
                }
                if (tipo.equals("-")){
                    result+= operando_left - operando_right;
                    System.out.println(operando_left);
                    System.out.println("RESTA");
                    System.out.println(operando_right);
                    System.out.println("EL resultado de la resta es: "+ result);
                    //stack.push(String.valueOf(result));
                    indice++;
                    continue;
                }
                if (tipo.equals("*")){
                    result+= operando_left * operando_right;
                    System.out.println("MULTIPLICACIÓN");
                }
                if (tipo.equals("/")){
                    result+= operando_left / operando_right;
                    System.out.println("DIVISION");
                }
                if (tipo.equals("%")){
                    result+= operando_left % operando_right;
                    System.out.println("RESIDUO");
                }

            }
            indice++;
        }
        return result;
    }


    public void solve2(String postfix){
    }

    public boolean its_number(char x){
        if (x == '0'){
            return true;
        }else if (x == '1'){
            return true;
        }else if (x == '2'){
            return true;
        }else if (x == '3'){
            return true;
        } else if (x == '4'){
            return true;
        }else if (x == '5'){
            return true;
        }else if (x == '6'){
            return true;
        }else if (x == '7'){
            return true;
        }else if (x == '8'){
            return true;
        }else if (x == '9'){
            return true;
        }else{
            return false;
        }
    }

    public String get_numer (String postfix, int indice) {
        String number = "";
        for (int  i = indice; i < postfix.length(); i++) {
            if (postfix.charAt(i) != 'P' && !operador(postfix.charAt(i))) {// && !operador(postfix.charAt(i))) {
                number += postfix.charAt(i);
                    }
                    if (postfix.charAt(i) == 'P') {
                        break;
                    }
                    indice = i;

                }
        return number;
            }



    public int solve_manual(Node root){
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
                result += solve_manual(root.getLeft()) + solve_manual(root.getRight());
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

import java.util.ArrayList;
import java.util.Stack;


public class Expression_tree {
    Node root;

    /**
     * Crea un árbol de expresión del cual vamos a obtener la raíz para recorrerlo
     * @param postfix expresión postfija de la entrada del usuario
     */
    public Expression_tree(String postfix){
        Stack<Node> stack = new Stack<Node>();
        int indice = 0;
        while (indice < postfix.length()) {
            Node nodo = new Node();
            if (postfix.charAt(indice) != 'P' && !operador(postfix.charAt(indice))) {
                String number = "";
                for (int i = indice; i < postfix.length(); i++) {
                    if (postfix.charAt(i) != 'P' && !operador(postfix.charAt(i))) {
                        number += postfix.charAt(i);
                    }
                    if (postfix.charAt(i) == 'P') {
                        break;
                    }
                    if (operador(postfix.charAt(i))){
                        break;
                    }
                    indice = i;
                }
                while (postfix.charAt(indice) != 'P'){// || operador(postfix.charAt(indice))) {
                    indice += 1;
                }
                nodo.setData(number);
                stack.push(nodo);
                continue;
            }


            if (operador(postfix.charAt(indice))){
                nodo.setData(postfix.charAt(indice));
                nodo.setRight(stack.pop());
                nodo.setLeft(stack.pop());
                stack.push(nodo);
            }
            indice++;
        }
        root = stack.pop();
        //System.out.println("El resultado es: " + solve(root));
    }


    /**
     * Evalúa el árbol de expresiónn y nos devuelve el resultado
     * @param root
     * @return El resultado de la evaluación de la expresión del árbol
     */

    public int solve(Node root){
        if (root == null){
            return 0;
        }
        if (root.getLeft()==null && root.getRight()==null){
            return Integer.valueOf(String.valueOf(root.getData()));
        }
        int operando_left = solve(root.getLeft());
        int operando_right = solve(root.getRight());
        String tipo = String.valueOf(root.getData());
        if (tipo.equals("+")){
            System.out.println("Suma");
            return operando_left + operando_right;

        }
        if (tipo.equals("-")){
            System.out.println("Resta");
            return operando_left - operando_right;

        }
        if (tipo.equals("x")){
            System.out.println("Multiplicación");
            return operando_left * operando_right;

        }
        if (tipo.equals("÷")){
            System.out.println("División");
            return operando_left / operando_right;
        }
        if (tipo.equals("%")){
            System.out.println("Residuo");
            return operando_left % operando_right;
        }
        return operando_left % operando_right;
    }

    /**
     *Nos indica si un caracter es un operador o no
     * @param f un caracter para
     * @return boolean
     */
    public boolean operador(char f){
        if (f == '+'){
            return true;
        }else if (f == '-'){
            return true;
        }else if (f == 'x'){
            return true;
        }else if (f == '/'){
            return true;
        }else if (f == '%'){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Se obtiene la raíz del árbol de expresión creado
     * @return
     */
    public Node get_root(){
        return this.root;
    }
}

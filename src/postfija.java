import java.util.Stack;

public class postfija {
    private String expresion;
    private Stack stack;
    private String result;

    public postfija (String data) {
        this.expresion = data;
        stack = new Stack();
        this.result = "";
        //Boolean

        for (int i = 0; i < data.length()-1; i++) {
            char character = data.charAt(i);
            if (character=='P' && data.charAt(i+1)!='(' && !operador(data.charAt(i+1))){
                result+="P";
            }else if (!operador(character) && character!='(' && character != ')' && character!='P') {
                result += character;

            } else if (operador(character)) {
                stack.push(character);

            } else if (character ==')') {;
                while (!stack.empty()){
                    result +=stack.pop();
                }

            }
        }
        this.result += "P"; // se agrega la última P no sé si es necesaria lol


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

    public static void main(String[] args) {
        postfija aja = new postfija("P(P24P+P3P)P-P(P5P)P"); //(24 + 3 ) - 5
        System.out.println(aja.result);
        //System.out.println(aja.stack.pop());
    }
    /*
    public static void main(String[] args) {
        StringTokenizer token = new StringTokenizer( "a*b+c-d*e","*+-",true);
        String jaja = "23";
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
        System.out.println(token.nextToken());
    }
     */
}

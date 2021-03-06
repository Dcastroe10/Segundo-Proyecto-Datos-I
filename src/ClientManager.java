import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de encargarse de la comunicación e interpretación de cada cliente en específico.
 */
public class ClientManager extends Thread{
    private final Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    public int identificator;
    private final Stack expresions = new Stack();

    /**
     * Método constructor de la clase, se encarga de definir los atributos de la clase.
     * @param socket dirección de la conexión por sockets de un cliente específico.
     * @param identificator número del cliente que está vinculado al administrador.
     */
    public ClientManager(Socket socket, int identificator) {
        this.clientSocket = socket;
        this.identificator = identificator;
    }

    /**
     * Método que se encarga de inicializar los canales de comunicación
     * @throws IOException
     */
    public void startManager() throws IOException {
        input = new DataInputStream(this.clientSocket.getInputStream());
        output = new DataOutputStream(this.clientSocket.getOutputStream());
        output.writeUTF(String.valueOf(identificator));
    }

    /**
     * Método encargado de cerrar los sockets de comunicación
     * @throws IOException
     */
    public void closeChannel() throws IOException{
        input.close();
        output.close();
        clientSocket.close();
    }

    /**
     * Método sobreescrito run correspondiente a los hilos que se mantiene en espera de mensajes del cliente
     */
    @Override
    public void run(){
        try {
            startManager();
            while (true){
                String msg = input.readUTF();
                System.out.println(msg);
                if (msg.equalsIgnoreCase("EXIT")){
                    break;
                } else if (msg.equalsIgnoreCase("HIS")){
                    CSVManager manager = new CSVManager();
                    String record = manager.readFiles(identificator);
                    output.writeUTF(record);
                } else{
                    try {
                        List prueba1 = normalConvertor(msg);
                        List prueba2 = polishNotation(prueba1);
                        String for_tree = polish(prueba2);
                        Expression_tree expressionTree = new Expression_tree(for_tree);
                        Node root = expressionTree.get_root();
                        String result = String.valueOf(expressionTree.solve(root));
                        output.writeUTF(result);
                        CSVManager manager = new CSVManager();
                        manager.writeFiles(identificator, msg, result);

                    } catch (Exception e){
                        output.writeUTF("Syntax Error");
                    }
                }
            }
            output.writeUTF("EXIT");
            closeChannel();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Convierte la expresión matemática y la divide por operandos o operadores en el mismo orden pero en una lista
     * @param expresion Es el String que ingresa el usuario, la expresión matemática
     * @return devuelve la lista con la expresión matemática
     */


    public List<String> normalConvertor(String expresion) {
        List<String> list = new ArrayList<String>();
        int ind = 0;
        String element;
        char symbol;
        do {
            if ((symbol = expresion.charAt(ind)) < 48 || (symbol = expresion.charAt(ind)) > 57) {
                list.add("" + symbol);
                ind++;
            } else {
                element = "";
                while (ind < expresion.length() && (symbol = expresion.charAt(ind)) >= 48 && (symbol = expresion.charAt(ind)) <= 57) {
                    element += symbol;
                    ind++;
                }
                list.add(element);
            }

        } while (ind < expresion.length());
        System.out.println(list);
        return list;
    }

    /**
     * Convierte una lista en otra lista con la expresión polaca inversa (postfija)
     * @param expresion Es una lista con la expresión ingresada en la calculadora separando los datos
     * @return list con la expresión postfija
     */


    public List<String> polishNotation(List<String> expresion) {
        List<String> result = new ArrayList<String>();
        for (String str : expresion) {
            if (str.matches("\\d+")) {
                result.add(str);
            } else if (str.equals("(")) {
                expresions.push(str);
            } else if (str.equals(")")) {

                while (!expresions.topper.equals("(")) {
                    result.add(expresions.pop());
                }
                expresions.pop();
            } else {
                while (expresions.getSize() != 0 && getOrder(expresions.topper) >= getOrder(str)) {
                    result.add(expresions.pop());
                }
                expresions.push(str);
            }
        }
        while (expresions.getSize() != 0) {
            result.add(expresions.pop());
        }
        System.out.println(result);
        return result;
    }

    /**
     * Se utiliza para decidir cual es el orden de prioridad de los operadores
     * @param str Será un elemento de la lista que se está convirtiendo a postfija en polishNotation
     * @return int el cual es el valor de prioridad del operador
     */

    public int getOrder(String str) {
        if (str.equals("+")) {
            return 1;
        } else if (str.equals("-")) {
            return 1;
        } else if (str.equals("x")) {
            return 2;
        } else if (str.equals("/")) {
            return 2;
        } else if (str.equals("%")) {
            return 2;
        }
        return 0;
    }

    /**
     *Convierte una lista en notación postfija al String con las divisiones necesarias para la creación del árbol de expresión
     * @param list es una lista que contiene la expresión en notación postfija
     * @return String con la notación polaca necesaria para la creación del árbol
     */

    public String polish (List list){
        String result = "";
        for (int i = 0; i<list.size();i++){
            result+= "P" + list.get(i);
        }
        return result+"P";
    }
}

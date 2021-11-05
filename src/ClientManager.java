import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Clase encargada de encargarse de la comunicación e interpretación de cada cliente en específico.
 */
public class ClientManager extends Thread{
    private final Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    public int identificator;
    private final Stack1 expresions = new Stack1();
    private final Stack1 operations = new Stack1();



    /**
     * Método constructor de la clase, se encarga de definir los atributos de la clase.
     * @param socket dirección de la conexión por sockets de un cliente específico.
     * @param clientsList lista que contiene los clientes conectados al servidor.
     * @param identificator número del cliente que está vinculado al administrador.
     */
    public ClientManager(Socket socket, ArrayList<ClientManager> clientsList, int identificator) {
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
                        manager.writeFiles(identificator, msg.substring(0, msg.length()-1), result);

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
     * Convierte los caracteres de un String a una lista
     * @param expresion
     * @return devuelve la lista
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
     * @param expresion
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
     * @param str
     * @return int
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
     *
     * @param list
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase encargada de encargarse de la comunicación e interpretación de cada cliente en específico.
 */
public class ClientManager extends Thread{
    private final Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    public int identificator;

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
                        String expresion = encrypt(msg);
                        String treenotation = postfix(expresion);
                        System.out.println(expresion);
                        System.out.println(treenotation + " This is the original postfix");
                        Expression_tree expressionTree = new Expression_tree(treenotation);
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
     * Este método cambia de notación infija a notación postfija
     * @param data
     * @return Devuelve la expresión ya en notación postfija
     */
    public String postfix(String data){
        Stack stack = new Stack();
        String result = "";
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
        return result += "P"; // se agrega la última P no sé si es necesaria lol
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
     * Identifica cuando hay un cambio entre números y símbolos para luego poder diferenciarlos en la notación postfija
     * @param to_encrypt
     * @return el string "encriptado"
     */
    public String encrypt(String to_encrypt) {
        String result = "";
        for (int i = 0; i < to_encrypt.length(); i++) {
            if (!simbolos(to_encrypt.charAt(i))) {
                result += String.valueOf(to_encrypt.charAt(i));
            } else if (simbolos(to_encrypt.charAt(i))) {
                result += "P" + to_encrypt.charAt(i) + "P";
            }
        }
        //System.out.println(result);
        return result + "P";
    }

    /**
     * Identifica si un caracter es distinto de un número
     * @param f
     * @return true si no es un número y false si lo es
     */
    public boolean simbolos(char f){
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
        }else if (f == '('){
            return true;
        }else if (f == ')') {
            return true;
        }else {
            return false;
        }
    }
}

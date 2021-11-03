import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase encargada de encargarse de la comunicación e interpretación de cada cliente en específico.
 */
public class ClientManager extends Thread{
    private final Socket clientSocket;
    private final ArrayList<ClientManager> clientsList;
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
        this.clientsList = clientsList;
        this.identificator = identificator;
    }

    /**
     * Método que se encarga de inicializar los canales de comunicación
     * @throws IOException
     */
    public void startManager() throws IOException {
        input = new DataInputStream(this.clientSocket.getInputStream());
        output = new DataOutputStream(this.clientSocket.getOutputStream());
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
                }
                output.writeUTF("Hola cliente #" + identificator);
            }
            closeChannel();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

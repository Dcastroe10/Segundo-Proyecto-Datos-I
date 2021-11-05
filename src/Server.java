import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que se encarga de crear el servidor encargado de administrar los clientes de la calculadora mediante sockets
 */
public class Server {
    // Declaración de Atributos de la clase
    private ServerSocket serverSocket; //
    private final ArrayList<ClientManager> clientsList;

    /**
     * Método constructor de la clase que inicializa la variable clientsList con un nuevo arreglo que contiene instancias de la clase ClientManager
     */
    public Server(){
        clientsList = new ArrayList<ClientManager>();
    }

    /**
     * Función que se encarga de iniciar el servidor, se mantiene en espera a que un cliente se conecte y al hacerlo le asigna un administrador
     * @param gate numero de puerto al que se va a crear el servidor
     * @throws IOException Error de conexión en los sockets
     */
    public void startServer(int gate) throws IOException{
        serverSocket = new ServerSocket(gate);
        System.out.println("Servidor Iniciado");
        //-----------El Servidor esta Encendido---------------

        while (true){ // Se queda en espera de clientes que quieran conectarse
            Socket clientSocket = serverSocket.accept(); //acepta a un cliente
            int identificator = clientsList.size() + 1;
            ClientManager clientManager = new ClientManager(clientSocket, clientsList, identificator); // Le asigna un administrador
            clientsList.add(clientManager); // lo agrega a la lista de clientes
            clientManager.start();
            System.out.println("Se conectó un nuevo cliente");
        }
    }

    /**
     * Método que se encarga de cerrar la conexión por Sockets
     * @throws IOException algún tipo de error al intentar cerrar los sockets
     */
    public void closeServer() throws IOException {
        serverSocket.close();
    }

    /**
     * Método main de la clase que se encarga de crear una instancia nueva del servidor y llamar las funciones que lo inician
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

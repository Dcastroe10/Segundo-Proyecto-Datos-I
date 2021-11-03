import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private final ArrayList<ClientManager> clientsList;

    public Server(){
        clientsList = new ArrayList<ClientManager>();
    }

    public void startServer(int gate) throws IOException{
        serverSocket = new ServerSocket(gate);
        System.out.println("Servidor Iniciado");

        // Se queda en espera de clientes que quieran conectarse
        while (true){
            Socket clientSocket = serverSocket.accept();
            int identificator = clientsList.size() + 1;
            ClientManager clientManager = new ClientManager(clientSocket, clientsList, identificator);
            clientsList.add(clientManager);
            clientManager.start();
            System.out.println("Se conectó un nuevo cliente");
        }
    }

    public void closeServer() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.startServer(5000);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

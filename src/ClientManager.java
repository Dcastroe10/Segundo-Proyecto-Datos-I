import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager extends Thread{
    private final Socket clientSocket;
    private final ArrayList<ClientManager> clientsList;
    private DataInputStream input;
    private DataOutputStream output;
    public int identificator;

    public ClientManager(Socket socket, ArrayList<ClientManager> clientsList, int identificator) {
        this.clientSocket = socket;
        this.clientsList = clientsList;
        this.identificator = identificator;
    }

    public void startManager() throws IOException {
        input = new DataInputStream(this.clientSocket.getInputStream());
        output = new DataOutputStream(this.clientSocket.getOutputStream());
    }

    public void closeChannel() throws IOException{
        input.close();
        output.close();
        clientSocket.close();
    }

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

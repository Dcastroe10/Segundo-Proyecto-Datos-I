import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public void startClient(String host, int gate) throws IOException{
        socket = new Socket(host, gate);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public void closeClient() throws IOException {
        output.close();
        input.close();
        socket.close();
    }

    private void startComunication(String host, int gate) throws IOException{
        startClient(host, gate);

        while (true){
            String send = JOptionPane.showInputDialog("Escribe tu mensaje");
            output.writeUTF(send);
            String msg = input.readUTF();
            if (msg.equals("EXIT")){
              break;
            }
            System.out.println(msg);
        }
        closeClient();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try{
            client.startComunication("localhost", 5000);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

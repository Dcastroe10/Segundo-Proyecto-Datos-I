import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase cliente que posee su propia interfaz de usuario y sus canales de comunicación con el servidor
 */
public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    /**
     * Función que se encarga de crear el socket con el cliente así como los canales de comunicación con el mismo
     * @param host string con la dirección ip del host
     * @param gate puerto al que se va a conectar el cliente
     * @throws IOException Excepción al realizarse la conexión por sockets
     */
    public void startClient(String host, int gate) throws IOException{
        socket = new Socket(host, gate);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Metodo encargado de cerrar todos los canales de comunicación con el servidor
     * @throws IOException
     */
    public void closeClient() throws IOException {
        output.close();
        input.close();
        socket.close();
    }

    /**
     * Método que se mantiene que se mantiene escuchando por los
     * @param host string con la dirección ip del host
     * @param gate puerto al que se va a conectar el cliente
     * @throws IOException excepción al comunicarse por sockets
     */
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

    /**
     * Función main que se ejecuta al inicio, encargada de crear una nueva instancia de cliente e iniciar la comunicación con el servidor
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        try{
            client.startComunication("localhost", 5000);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

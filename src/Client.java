import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
    public JFrame root;
    public JLabel textField;
    boolean beggin = true;
    boolean showing_result = false;
    boolean record = false;

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

        while (true){
            String msg = input.readUTF();
            if (msg.equals("EXIT")){
                break;
            } else if (beggin){
                System.out.println("Bienvenido a la Calculadora Cliente #" + msg);
                String nameFrame = "Calculadora - Cliente #" + msg;
                createInterface(nameFrame); // se crea la interfaz de la calculadora
                beggin = false;
            } else if (!record) {
                showing_result = true;
                textField.setText(msg);
                System.out.println("llegue aqui uuu = " + msg);
            }
        }
        closeClient();
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
     * Método para enviar mensajes al servidor
     * @param msg mensaje que se quiere enviar al servidor
     * @throws IOException excepción al comunicarse por sockets
     */
    private void sendMessage(String msg) throws IOException {
        output.writeUTF(msg);
    }

    /**
     * Función main que se ejecuta al inicio, encargada de crear una nueva instancia de cliente e iniciar la comunicación con el servidor
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        try{
            client.startClient("localhost", 5000);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createInterface(String nameFrame){
        // Fonts that were are going to use in the components
        Font titlesFont = new Font("Cambria", Font.BOLD, 30);
        Font buttonsFont = new Font("Cambria", Font.BOLD, 25);

        //---------------------------------------------------------------
        // First Panel that contents the screen where the numbers will appear
        JPanel screen = new JPanel();
        screen.setLayout(new GridLayout(1,1, 10, 10)); // Layout that allows rescale the components in diferent sizes of frame

        // Label that shows the content
        textField = new JLabel();
        textField.setBackground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setVerticalAlignment(SwingConstants.CENTER);
        textField.setText("");
        textField.setFont(titlesFont);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3); // Black Border of the Label
        textField.setBorder(border);
        screen.add(textField);
        // End of the First Panel
        //-----------------------------------------------------------------

        //---------------------------------------------------------------
        // Second Panel that contents the first row of buttons
        JPanel buttonRow1 = new JPanel();
        buttonRow1.setLayout(new GridLayout(1,4,10,10));
        JButton button1 = new JButton("HIS");
        button1.setFont(buttonsFont);
        //button1.addActionListener(e -> textField.setText(textField.getText() + "1"));
        JButton button2 = new JButton("DEL");
        button2.setFont(buttonsFont);
        button2.addActionListener(e -> textField.setText(textField.getText().substring(0, textField.getText().length()-1)));
        JButton button3 = new JButton("AC");
        button3.setFont(buttonsFont);
        button3.addActionListener(e -> textField.setText(""));
        JButton button4 = new JButton("÷");
        button4.setFont(buttonsFont);
        button4.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "÷");
            this.showing_result = false;
        });

        buttonRow1.add(button1);
        buttonRow1.add(button2);
        buttonRow1.add(button3);
        buttonRow1.add(button4);
        // End of the Second Panel
        //--------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------
        // Third Panel that contents the second row of buttons
        JPanel buttonRow2 = new JPanel();
        buttonRow2.setLayout(new GridLayout(1,4,10,10));
        JButton button5 = new JButton("7");
        button5.setFont(buttonsFont);
        button5.addActionListener(e ->{
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "7");
            this.showing_result = false;
        });

        JButton button6 = new JButton("8");
        button6.setFont(buttonsFont);
        button6.addActionListener(e ->{
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "8");
            this.showing_result = false;
        });

        JButton button7 = new JButton("9");
        button7.setFont(buttonsFont);
        button7.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "9");
            this.showing_result = false;
        });

        JButton button8 = new JButton("x");
        button8.setFont(buttonsFont);
        button8.addActionListener(e ->{
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "x");
            this.showing_result = false;
        });
        buttonRow2.add(button5);
        buttonRow2.add(button6);
        buttonRow2.add(button7);
        buttonRow2.add(button8);
        // End of the Third Panel
        //------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------
        // Fourth Panel that contents the third row of buttons
        JPanel buttonRow3 = new JPanel();
        buttonRow3.setLayout(new GridLayout(1,4,10,10));
        JButton button9 = new JButton("4");
        button9.setFont(buttonsFont);
        button9.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "4");
            this.showing_result = false;
        });

        JButton button10 = new JButton("5");
        button10.setFont(buttonsFont);
        button10.addActionListener(e ->{
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "5");
            this.showing_result = false;
        });

        JButton button11 = new JButton("6");
        button11.setFont(buttonsFont);
        button11.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "6");
            this.showing_result = false;
        });

        JButton button12 = new JButton("-");
        button12.setFont(buttonsFont);
        button12.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "-");
            this.showing_result = false;
        });
        buttonRow3.add(button9);
        buttonRow3.add(button10);
        buttonRow3.add(button11);
        buttonRow3.add(button12);
        // End of the Fourth Panel
        //------------------------------------------------------------------------------

        //------------------------------------------------------------------------------
        // Fifth Panel that contents the fourth row of buttons-
        JPanel buttonRow4 = new JPanel();
        buttonRow4.setLayout(new GridLayout(1,4,10,10));
        JButton button13 = new JButton("1");
        button13.setFont(buttonsFont);
        button13.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "1");
            this.showing_result = false;
        });

        JButton button14 = new JButton("2");
        button14.setFont(buttonsFont);
        button14.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "2");
            this.showing_result = false;
        });

        JButton button15 = new JButton("3");
        button15.setFont(buttonsFont);
        button15.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "3");
            this.showing_result = false;
        });

        JButton button16 = new JButton("+");
        button16.setFont(buttonsFont);
        button16.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "+");
            this.showing_result = false;
        });

        buttonRow4.add(button13);
        buttonRow4.add(button14);
        buttonRow4.add(button15);
        buttonRow4.add(button16);
        // End of the Fifth
        //------------------------------------------------------------------------------

        //------------------------------------------------------------------------------
        // Sixth Panel that contents the fifth and last row of buttons
        JPanel buttonRow5 = new JPanel();
        buttonRow5.setLayout(new GridLayout(1,4,10,10));
        JButton button17 = new JButton("0");
        button17.setFont(buttonsFont);
        button17.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "0");
            this.showing_result = false;
        });

        JButton button18 = new JButton("00");
        button18.setFont(buttonsFont);
        button18.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "00");
            this.showing_result = false;
        });

        JButton button19 = new JButton("000");
        button19.setFont(buttonsFont);
        button19.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "000");
            this.showing_result = false;
        });

        JButton button20 = new JButton("%");
        button20.addActionListener(e ->{   if (showing_result){
            textField.setText("");
        }
        textField.setText(textField.getText() + "%");
            this.showing_result = false;
        });
        button20.setFont(buttonsFont);
        //button20.addActionListener(e -> textField.setText(textField.getText() + "4"));
        buttonRow5.add(button17);
        buttonRow5.add(button18);
        buttonRow5.add(button19);
        buttonRow5.add(button20);
        // End of the Fifth
        //-------------------------------------------------------------------------------------

        // Start of the Sixth
        //------------------------------------------------------------------------------------
        JPanel buttonRow6 = new JPanel();
        buttonRow6.setLayout(new GridLayout(1,4,10,10));
        JButton button21 = new JButton("(");
        button21.setFont(buttonsFont);
        button21.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "(");
            this.showing_result = false;
        });

        JButton button22 = new JButton(")");
        button22.setFont(buttonsFont);
        button22.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + ")");
            this.showing_result = false;
        });

        JButton button23 = new JButton("XX");
        button23.setFont(buttonsFont);
        button23.addActionListener(e -> {
            if (showing_result){
                textField.setText("");
            }
            textField.setText(textField.getText() + "XX");
            this.showing_result = false;
        });

        JButton button24 = new JButton("=");
        button24.setFont(buttonsFont);
        button24.addActionListener(e -> {
            String expresion = textField.getText().replace(" ", "");
            try {
                sendMessage(expresion);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        button21.setFont(buttonsFont);
        //button20.addActionListener(e -> textField.setText(textField.getText() + "4"));
        buttonRow6.add(button21);
        buttonRow6.add(button22);
        buttonRow6.add(button23);
        buttonRow6.add(button24);
        //-----------------------------------------------------------------
        // Creation and set the parameters of the main frame
        root = new JFrame();
        root.setLayout(new GridLayout(7, 1, 10,10));
        root.setSize(410, 500);
        root.setTitle(nameFrame);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creation and Add the icon of the Window
        ImageIcon icon = new ImageIcon("images/icono.jpg");
        root.setIconImage(icon.getImage());

            // Add all the components
        root.add(screen);
        root.add(buttonRow1);
        root.add(buttonRow2);
        root.add(buttonRow3);
        root.add(buttonRow4);
        root.add(buttonRow5);
        root.add(buttonRow6);

        // Show the window
        root.setVisible(true);
        // End of the main frame
        //------------------------------------------------------------------
    }
}

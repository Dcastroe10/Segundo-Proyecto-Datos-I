import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Stack;

public class Interface{

    // Constructor method of the class. Here the interface of the calculator is created
    public Interface(){

        // Fonts that were are going to use in the components
        Font titlesFont = new Font("Cambria", Font.BOLD, 30);
        Font buttonsFont = new Font("Cambria", Font.BOLD, 25);

        //---------------------------------------------------------------
        // First Panel that contents the screen where the numbers will appear
        JPanel screen = new JPanel();
        screen.setLayout(new GridLayout(1,1, 10, 10)); // Layout that allows rescale the components in diferent sizes of frame

        // Label that shows the content
        JLabel textField = new JLabel();
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
        button4.addActionListener(e -> textField.setText(textField.getText() + " / "));
        buttonRow1.add(button1);
        buttonRow1.add(button2);
        buttonRow1.add(button3);
        buttonRow1.add(button4);
        // End of the Second Panel
        //-----------------------------------------------------------------

        //---------------------------------------------------------------
        // Third Panel that contents the second row of buttons
        JPanel buttonRow2 = new JPanel();
        buttonRow2.setLayout(new GridLayout(1,4,10,10));
        JButton button5 = new JButton("7");
        button5.setFont(buttonsFont);
        button5.addActionListener(e -> textField.setText(textField.getText() + "7"));
        JButton button6 = new JButton("8");
        button6.setFont(buttonsFont);
        button6.addActionListener(e -> textField.setText(textField.getText() + "8"));
        JButton button7 = new JButton("9");
        button7.setFont(buttonsFont);
        button7.addActionListener(e -> textField.setText(textField.getText() + "9"));
        JButton button8 = new JButton("x");
        button8.setFont(buttonsFont);
        button8.addActionListener(e -> textField.setText(textField.getText() + " * "));
        buttonRow2.add(button5);
        buttonRow2.add(button6);
        buttonRow2.add(button7);
        buttonRow2.add(button8);
        // End of the Third Panel
        //-----------------------------------------------------------------

        //---------------------------------------------------------------
        // Fourth Panel that contents the third row of buttons
        JPanel buttonRow3 = new JPanel();
        buttonRow3.setLayout(new GridLayout(1,4,10,10));
        JButton button9 = new JButton("4");
        button9.setFont(buttonsFont);
        button9.addActionListener(e -> textField.setText(textField.getText() + "4"));
        JButton button10 = new JButton("5");
        button10.setFont(buttonsFont);
        button10.addActionListener(e -> textField.setText(textField.getText() + "5"));
        JButton button11 = new JButton("6");
        button11.setFont(buttonsFont);
        button11.addActionListener(e -> textField.setText(textField.getText() + "6"));
        JButton button12 = new JButton("-");
        button12.setFont(buttonsFont);
        button12.addActionListener(e -> textField.setText(textField.getText() + " - "));
        buttonRow3.add(button9);
        buttonRow3.add(button10);
        buttonRow3.add(button11);
        buttonRow3.add(button12);
        // End of the Fourth Panel
        //-----------------------------------------------------------------

        //---------------------------------------------------------------
        // Fifth Panel that contents the fourth row of buttons
        JPanel buttonRow4 = new JPanel();
        buttonRow4.setLayout(new GridLayout(1,4,10,10));
        JButton button13 = new JButton("1");
        button13.setFont(buttonsFont);
        button13.addActionListener(e -> textField.setText(textField.getText() + "1"));
        JButton button14 = new JButton("2");
        button14.setFont(buttonsFont);
        button14.addActionListener(e -> textField.setText(textField.getText() + "2"));
        JButton button15 = new JButton("3");
        button15.setFont(buttonsFont);
        button15.addActionListener(e -> textField.setText(textField.getText() + "3"));
        JButton button16 = new JButton("+");
        button16.setFont(buttonsFont);
        button16.addActionListener(e -> textField.setText(textField.getText() + " + "));
        buttonRow4.add(button13);
        buttonRow4.add(button14);
        buttonRow4.add(button15);
        buttonRow4.add(button16);
        // End of the Fifth
        //-----------------------------------------------------------------

        //---------------------------------------------------------------
        // Sixth Panel that contents the fifth and last row of buttons
        JPanel buttonRow5 = new JPanel();
        buttonRow5.setLayout(new GridLayout(1,4,10,10));
        JButton button17 = new JButton("(");
        button17.setFont(buttonsFont);
        button17.addActionListener(e -> textField.setText(textField.getText() + "("));
        JButton button18 = new JButton(")");
        button18.setFont(buttonsFont);
        button18.addActionListener(e -> textField.setText(textField.getText() + ")"));
        JButton button19 = new JButton("0");
        button19.setFont(buttonsFont);
        button19.addActionListener(e -> textField.setText(textField.getText() + "0"));
        JButton button20 = new JButton("=");
        button20.addActionListener(e ->{        //AQUÍ ES EL BOTÓN IGUAL PARA CREAR EL ARBOL Y LUEGO CALCULAR

            String for_tree;
            String expresion = textField.getText().replace(" ", "");
            expresion = this.encrypt(expresion);
            String for_me = this.postfix2(expresion);
            System.out.println(for_me);
            //System.out.println(operadores(for_me)+"aaaaaaaaaaaaaaaaaaaaa");
            for_tree = this.postfix(expresion);
            System.out.println(for_tree);
            create_tree(for_tree,true,false,1,"","");

            //System.out.println(cant_operandos(for_tree));
            Expression_tree tree = new Expression_tree(for_tree);



        }
        );
        button20.setFont(buttonsFont);
        //button20.addActionListener(e -> textField.setText(textField.getText() + "4"));
        buttonRow5.add(button17);
        buttonRow5.add(button18);
        buttonRow5.add(button19);
        buttonRow5.add(button20);
        // End of the Fifth
        //-----------------------------------------------------------------

        //-----------------------------------------------------------------
        // Creation and set the parameters of the main frame
        JFrame root = new JFrame();
        root.setLayout(new GridLayout(6, 1, 10,10));
        root.setSize(410, 500);
        root.setTitle("Calculadora");
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

        // Show the window
        root.setVisible(true);
        // End of the main frame
        //------------------------------------------------------------------
    }
    public String postfix(String data){
        String expresion = data;
        Stack stack = new Stack();
        String result = "";
        //Boolean

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

    public String operadores(String for_me){
        String result="";
        String invertidos ="";
        for (int i = for_me.length()-1; i>=0;i--){
            if(operador(for_me.charAt(i))){
                invertidos+=for_me.charAt(i);
                System.out.println(for_me.charAt(i));
            }
        }
        for (int i =0; i<for_me.length(); i++){
            if(operador(for_me.charAt(i))){
                result+=for_me.charAt(i);
            }
        }
        return result + "KAKA" + invertidos;
    }

    public String postfix2(String data){
        String expresion = data;
        Stack stack = new Stack();
        String result = "";
        //Boolean

        for (int i = 0; i < data.length()-1; i++) {
            char character = data.charAt(i);
            if (character=='P' && data.charAt(i+1)!='(' && !operador(data.charAt(i+1))){
                result+="";
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
        return result; // se agrega la última P no sé si es necesaria lol


    }

    public boolean operador(char f){
        if (f == '+'){
            return true;
        }else if (f == '-'){
            return true;
        }else if (f == '*'){
            return true;
        }else if (f == '/'){
            return true;
        }else if (f == '%'){
            return true;
        }else{
            return false;
        }
    }

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

    public int cant_operandos(String for_tree){
        int result=0;
        for (int i=0;i<for_tree.length();i++ ){
            if (operador(for_tree.charAt(i))){
                result++;
            }
        }
        return result;
        }


    public boolean simbolos(char f){
        if (f == '+'){
            return true;
        }else if (f == '-'){
            return true;
        }else if (f == '*'){
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

    public void create_tree(String postfix, boolean izquierda, boolean derecha, int indice, String data_left, String data_right){ //expresión,true,false PARA LA PRIMERA VEZ
        Node left;
        Node right;
        Node root;
        if (izquierda &&data_left=="") {
            for (int i = indice; i < postfix.length(); i++) {
                if (postfix.charAt(i) != 'P'){// && !operador(postfix.charAt(i))) {
                    data_left += postfix.charAt(i);
                }
                if (postfix.charAt(i) == 'P'){
                    break;
                }
                indice=i;
            }
            System.out.println(data_left+"-------------");
            while (postfix.charAt(indice)!= 'P'){
                indice+=1;
            }
            create_tree(postfix,false,true,indice,data_left,data_right);
        }

        System.out.println("indice:" + Integer.toString(indice+1));// la idea es para work recursivo digamos para probar
        System.out.println(postfix.charAt(indice+1));
        //create_tree(postfix, false,true,indice);
        if (derecha) {
            for (int i = indice; i < postfix.length(); i++) {
                if (postfix.charAt(i) != 'P'){// && !operador(postfix.charAt(i))) {
                    data_right += postfix.charAt(i);
                }
                if (postfix.charAt(i) == 'P'){
                    break;
                }
            }
        }
        /*
        while (!operador(postfix.charAt(indice))){
            indice++;
        }
         */



    }

    public String get_number(String postfix, int indice){
        String result ="";

        for (int i = indice; i<postfix.length(); i++){
            if (!operador(postfix.charAt(i)) && postfix.charAt(i) != 'P'){
                result+=postfix.charAt(i);
            }if(postfix.charAt(i)=='P'){
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Interface proof = new Interface();
    }
}


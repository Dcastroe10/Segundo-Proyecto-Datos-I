public class Expression_tree {

    public Expression_tree(String expression){
        String data1 = "";
        String operator = "";
        String data2 = "";
        int cambiar_esto = 1;
        //System.out.println("ENTRÓOOOOOOO");
        //System.out.println(expression);

        for (int i =1; i<expression.length();i++){
            char daam = expression.charAt(i);
            if(!decode(daam) && cambiar_esto<2){
                String result = "";
                for (int n = 0; n<expression.length();n++){
                    if (!decode(expression.charAt(n))){
                        result += expression.charAt(n);
                    }else{
                        break;
                    }
                }
                System.out.println(data1);
                cambiar_esto++;
                data1=result;
                System.out.println(data1);
            }





        }




    }

    public boolean decode(char f){
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
        }else if (f == 'P') {
            return true;
        }else {
            return false;
        }
    }








}

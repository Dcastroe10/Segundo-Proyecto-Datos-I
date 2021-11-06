import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase encargada de la administración, lectura y escritura del documento .csv
 */
public class CSVManager {
    public BufferedReader csvreader = null; //lector de archivos
    public BufferedWriter csvwriter = null; // escritor de archivos
    public FileWriter filewriter = null; // escritor de archivos complementario
    public PrintWriter printWriter = null; // otro escritor de archivos complementario :)
    public String filepath = "src\\Historial.csv"; //dirección del archivo editado

    /**
     * Método encargado de leer el archivo y regresar los datos que empiecen únicamente con el identificador dado
     * @param identificator número que se va a utilizar para clasificar los archivos
     * @return devuelve un string con todos los dados obtenido con base al identificador
     */
    public String readFiles(int identificator) {
        String record = "";
        String row;
        String ident = String.valueOf(identificator);
        boolean conter = false;
        try {
            csvreader = new BufferedReader(new FileReader(filepath));
            while ((row = csvreader.readLine()) != null) {
                if (row.charAt(0) == ident.charAt(0) && conter) {
                    record += "," + row;
                } else if (row.charAt(0) == ident.charAt(0) && !conter) {
                    record += row;
                    conter = true;
                }
            }
            System.out.println(record);
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo leer el archivo";
        }
    }

    /**
     * Metodo encargado de escribir información dentro del archivo .csv
     * @param identificator primer parámetro que se escribe.
     * @param operation segundo parámentro que se escribe.
     * @param result tercer parámetro que se escribe.
     */
    public void writeFiles(int identificator, String operation, String result) {
        Calendar calendar = GregorianCalendar.getInstance();
        String ident = String.valueOf(identificator);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minutes = String.valueOf(calendar.get(Calendar.MINUTE));
        String seconds = String.valueOf(calendar.get(Calendar.SECOND));
        String time = hour + ":" + minutes + ":" + seconds;

        try {
            filewriter = new FileWriter(filepath, true);
            csvwriter = new BufferedWriter(filewriter);
            printWriter = new PrintWriter(csvwriter);

            printWriter.println(ident + "," + operation + "," + result + "," + date + "," + time);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

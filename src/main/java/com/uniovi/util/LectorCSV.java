package com.uniovi.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LectorCSV {

    public static final String SEPARATOR=",";
    public static final String QUOTE="\"";

//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = null;
//
//        try {
//
//            br = new BufferedReader(new FileReader("files/Libro1.csv"));
//            String line = br.readLine();
//            while (null != line) {
//                String[] fields = line.split(SEPARATOR);
//                System.out.println(Arrays.toString(fields));
//
//                fields = removeTrailingQuotes(fields);
//                System.out.println(Arrays.toString(fields));
//
//                line = br.readLine();
//            }
//
//        } catch (Exception e) {
//         System.out.println(e);
//        } finally {
//            if (null != br) {
//                br.close();
//            }
//        }
//    }
//
//        private static String[] removeTrailingQuotes(String[] fields) {
//
//            String result[] = new String[fields.length];
//
//            for (int i=0;i<result.length;i++){
//                result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
//            }
//            return result;
//        }

    public List<String> readLines(String inFileName) throws FileNotFoundException {

        List<String> res = new LinkedList<>();

        // FileReader --> Coge los caracteres del fichero que le pasemos.
        BufferedReader in = new BufferedReader(new FileReader("listadoEstaciones.csv"));

        // Bucle de lectura.
        String line = "";
        try {
            try {
                while( (line = in.readLine()) != null) {
                    res.add(line);
                }
            } finally  { // Envolvemos el bucle en un try/catch y cambiamos el catch por finally --> cerramos fichero.
                in.close();
            }
        } catch (IOException e) {
            // IO Exception --> error de programacion o sistema. Recogemos esta io exception y la convertimos en un runtime. Excep. comprobada.
            throw new RuntimeException(e); // Una excp. comprobada la comnvertimos en una no comprobada.
        }
        return res;
    }
}

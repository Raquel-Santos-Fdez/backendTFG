package com.uniovi.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LectorCSV {

    public List<String> readLines(String inFileName) throws FileNotFoundException {

        List<String> res = new LinkedList<>();

        // FileReader --> Coge los caracteres del fichero que le pasemos.
        BufferedReader in = new BufferedReader(new FileReader(inFileName));

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

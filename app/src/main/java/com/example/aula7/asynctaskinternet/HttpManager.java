package com.example.aula7.asynctaskinternet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by aula7 on 19/09/17.
 */

public class HttpManager {
    public static String getData(String uri) throws IOException {
        BufferedReader reader = null;
        //clase URL de java para manejar rutas
        URL url = new URL(uri);


        // clase que permite hacer la conexion a internet
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Declara variable para abrir un buffer de recoleccionde datos
        StringBuilder stringBuilder = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line= reader.readLine()) != null){
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();

    }
}

package com.datatecsolutions.masterposmovil.modelo;

/**
 * Created by Reynaldo on 25/01/2016.
 */

import android.os.StrictMode;
import android.util.Log;

import com.datatecsolutions.masterposmovil.clientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class modelo_cliente {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private List<Cliente> listaclientes;
    private Cliente cliente;


    public String getJSON(String url, int timeout) {
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public modelo_cliente(){
        getDatos();

    }

    public List<Cliente> getDatos(){
        String data = getJSON("http://192.168.1.106/web/obtener_metas.php", 3600);
        Log.e("MyTag",data);

        listaclientes=new ArrayList<Cliente>();

        if(!data.equalsIgnoreCase("")){
            JSONObject json;
            try {
                json = new JSONObject(data);
                JSONArray jsonArray = json.optJSONArray("metas");
                for (int i = 0; i < jsonArray.length(); i++) {
                    cliente=new Cliente();
                    JSONObject jsonArrayChild = jsonArray.getJSONObject(i);
                    cliente.setId(jsonArrayChild.optString("id_cliente"));
                    cliente.setCodigo(jsonArrayChild.optString("codigo"));
                    cliente.setNombre(jsonArrayChild.optString("nombre"));
                    cliente.setSaldo(jsonArrayChild.optString("email"));
                    listaclientes.add(cliente);
                }
                Log.e("Mytag",Integer.toString(listaclientes.size()));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return listaclientes;
    }

}

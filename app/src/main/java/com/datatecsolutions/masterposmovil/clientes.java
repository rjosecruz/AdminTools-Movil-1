package com.datatecsolutions.masterposmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.masterposmovil.modelo.Cliente;
import com.datatecsolutions.masterposmovil.modelo.adaptercliente;
import com.datatecsolutions.masterposmovil.modelo.modelo_cliente;

import java.util.ArrayList;
import java.util.List;

public class clientes extends AppCompatActivity {
    public  modelo_cliente micliente;
    ListView lv;
    Context context;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        micliente=new modelo_cliente();
        List<Cliente> listaclientes=new ArrayList<Cliente>();
        listaclientes=micliente.getDatos();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        context=this;




        lv=(ListView) findViewById(R.id.lv);



        if(listaclientes==null){
           Cliente micliente=new Cliente();
            micliente.setCodigo("");
            micliente.setNombre("No hay elementos en la lista");
            micliente.setSaldo("");
            micliente.setId("");
            listaclientes.add(micliente);
            lv.setAdapter(new adaptercliente(this, listaclientes));
        }
        else{
            lv.setAdapter(new adaptercliente(this, listaclientes));
            lv.setClickable(true);
           // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
            //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Log.e("dataAdapter",dataAdapter.toString());
            //mispinner.setAdapter(dataAdapter);
        }


        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("MyError", "Error");
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_LONG).show();
            }
        });
*/
       // Toast.makeText(getApplicationContext(), Integer.toString(listaclientes.size()), Toast.LENGTH_LONG).show();
    }
}
package com.datatecsolutions.masterposmovil.modelo;

/**
 * Created by Reynaldo on 26/01/2016.
 */

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.masterposmovil.R;

public class adaptercliente extends ArrayAdapter<Cliente> {


    Activity context;
    List<Cliente> clientes;

    public adaptercliente(Activity context, List<Cliente> clientes){
        super(context, R.layout.itemcliente, clientes);
        this.context = context;
        this.clientes = clientes;

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        Holder holder=new Holder();
        final Cliente cliente=getItem(position);
        if (convertView == null || (convertView.getTag() == null)) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.itemcliente, null);
            holder=new Holder();


            holder.id=(TextView) convertView.findViewById(R.id.id_cliente);
            holder.nombre=(TextView) convertView.findViewById(R.id.nombre);
            holder.saldo=(TextView) convertView.findViewById(R.id.saldo);

            holder.id.setText("");
            holder.nombre.setText("");
            //holder.saldo.setText("");

            holder.id.setText(cliente.getId());
            holder.nombre.setText(cliente.getNombre());
           // holder.saldo.setText(cliente.getSaldo().toString());
            convertView.setTag(holder);
        } else {

            // return convertView;
            holder = (Holder) convertView.getTag();



        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String codigo=getItem(position).getId();

                Toast.makeText(context, "You Clicked " + getItem(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    public class Holder
    {
        TextView id;
        TextView codigo;
        TextView nombre;
        TextView saldo;
        ImageView img;
    }


}

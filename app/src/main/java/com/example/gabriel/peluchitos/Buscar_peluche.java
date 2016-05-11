package com.example.gabriel.peluchitos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar_peluche extends AppCompatActivity {

    private EditText enombre;
    private TextView Tbuscar;
    SQLiteDatabase lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_peluche);

        enombre= (EditText) findViewById(R.id.nombre_buscar);
        Tbuscar = (TextView)findViewById(R.id.Tbuscado);

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches",null, 2);
        lm= Peluches.getWritableDatabase();

    }

    public void buscanpeluche (View view) {

        String nombre = enombre.getText().toString();

        Toast.makeText(this, "Buscando Peluche", Toast.LENGTH_SHORT).show();

        TiendaPeluches(nombre);
    }

    protected void TiendaPeluches(String  nombre) {
        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        Cursor c = lm.rawQuery("SELECT nombre,cantidad,precio,id FROM Muñecos where nombre='"+nombre+"'", null);
        String Nombre="",cantidad="",precio="";
        int id=0;
        Tbuscar.setText("");
        if (c.moveToFirst()) {
            Nombre = c.getString(0);
            cantidad = c.getString(1);
            id = c.getInt(3);
            precio = c.getString(2);

            Tbuscar.setText("id:" + id + "\n" + "Nombre: " + Nombre + "\n" + "cantidad: " + cantidad + "\n" +
                    " Precio:" + precio + "\n");
            lm.close();
            enombre.setText("");
        }

        else
        {
            Toast.makeText(this, "No Existe Peluche", Toast.LENGTH_SHORT).show();
        }
    }
}

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

public class Actualizar_peluche extends AppCompatActivity {


    private EditText enombre, cant;
    String Nombre="";
    SQLiteDatabase lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_peluche);

        enombre= (EditText) findViewById(R.id.nombreact);
        Nombre = enombre.getText().toString();
        cant = (EditText) findViewById(R.id.Cantidadact);

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches",null, 2);
        lm= Peluches.getWritableDatabase();
    }

    public void actua_peluche(View view) {

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        Nombre = enombre.getText().toString();
        Cursor c = lm.rawQuery("SELECT nombre,cantidad FROM Muñecos where nombre='"+Nombre+"'", null);

        if (c.moveToFirst()) {
            enombre.setText(c.getString(0));
            Toast.makeText(this, "Buscando", Toast.LENGTH_SHORT).show();

            lm.close();

        }
        else
        {
            Toast.makeText(this, "No Existe Peluche", Toast.LENGTH_SHORT).show();
        }


    }
    public void actualizado(View view) {

        Toast.makeText(this, "Buscando Spelcuhe a Actualizar", Toast.LENGTH_SHORT).show();
        TiendaPeluches(Nombre);
    }

    protected void TiendaPeluches(String  nombre) {
        this.Nombre = enombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        lm.execSQL("UPDATE Muñecos SET cantidad='" + cant.getText().toString() + "' WHERE nombre='" + this.Nombre + "'");

        lm.close();
        Toast.makeText(this, "Peluche Actualizado", Toast.LENGTH_SHORT).show();
        enombre.setText("");
        cant.setText("");
        }
}

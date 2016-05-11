package com.example.gabriel.peluchitos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar_peluche extends AppCompatActivity {

    private EditText nombre;
    String Nombre="";
    SQLiteDatabase lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_peluche);

        nombre= (EditText) findViewById(R.id.enombre_eliminar);
        Nombre = nombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches",null, 2);
        lm= Peluches.getWritableDatabase();
    }

    public void eliminando(View view) {


        Toast.makeText(this, "Por favor Espere", Toast.LENGTH_SHORT).show();

        Tienda_Peluches(Nombre);
    }

    protected void Tienda_Peluches(String  Nombre) {
        this.Nombre = nombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        lm.execSQL("DELETE FROM Muñecos WHERE nombre='" + this.nombre + "'");
        Toast.makeText(this, this.Nombre, Toast.LENGTH_SHORT).show();
        nombre.setText("");
        lm.close();
        Toast.makeText(this, "Peluche Eliminado", Toast.LENGTH_SHORT).show();

    }

}

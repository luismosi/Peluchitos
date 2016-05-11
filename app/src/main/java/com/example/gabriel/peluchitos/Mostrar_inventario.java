package com.example.gabriel.peluchitos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Mostrar_inventario extends AppCompatActivity {

    private TextView mostrar;

    String tienda=" Id - " + "Nombre  -  " + "Cantidad - " + "Precio " + "\n";
    SQLiteDatabase lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_inventario);

        mostrar = (TextView)findViewById(R.id.tMOstrar);

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        Cursor c = lm.rawQuery("SELECT * FROM Muñecos", null);

        while (c.moveToNext())
        {
            tienda+="  "+c.getString(0);
            tienda+=" - "+c.getString(1);
            tienda+=" - "+c.getString(2);
            tienda+=" - "+c.getString(3);
            tienda+="\n";
        }

        mostrar.setText(tienda);

    }
}

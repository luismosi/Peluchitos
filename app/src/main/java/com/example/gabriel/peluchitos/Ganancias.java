package com.example.gabriel.peluchitos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ganancias extends AppCompatActivity {

    private TextView ganancia;
    String cant="(" + "Ganancia Total - " + ")"+"\n";

    SQLiteDatabase lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganancias);

        ganancia = (TextView)findViewById(R.id.ganancias_totales);

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();

        Cursor l = lm.rawQuery("SELECT * FROM producido", null);

        while (l.moveToNext())
        {
            cant+="  "+l.getString(0);
            cant+="\n";
        }

        ganancia.setText(cant);
    }
}

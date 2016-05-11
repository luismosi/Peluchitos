package com.example.gabriel.peluchitos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Agregr_peluche extends AppCompatActivity {

    private EditText eIdentificacion;
    private EditText enombre;
    private EditText eCantidad;
    private EditText ePrecio;
    private Button bGuardado;
    private TextView msj;
    SQLiteDatabase lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregr_peluche);

        eIdentificacion = (EditText) findViewById(R.id.eId);
        enombre = (EditText) findViewById(R.id.enombre);
        eCantidad = (EditText) findViewById(R.id.eCantidad);
        ePrecio = (EditText) findViewById(R.id.ePrecio);
        bGuardado = (Button) findViewById(R.id.bGuardando);
        msj = (TextView) findViewById(R.id.tMuestraAg);

    }

    public void guardanPeluche(View view) {

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        String nombre = enombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        String precio = ePrecio.getText().toString();

        ContentValues nuevoPeluche = new ContentValues();
        nuevoPeluche.put("nombre", nombre);
        nuevoPeluche.put("cantidad", cantidad);
        nuevoPeluche.put("precio", precio);
        lm.insert("Muñecos", null, nuevoPeluche);
        lm.close();


        eIdentificacion.setText("");
        eCantidad.setText("");
        ePrecio.setText("");
        tienda_peluches();
        Toast.makeText(this, "Peluche guardado", Toast.LENGTH_SHORT).show();
    }

    protected void tienda_peluches() {
        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        Cursor c = lm.rawQuery("SELECT nombre,cantidad,precio,id FROM Muñecos where nombre='" + enombre.getText().toString() + "'", null);
        String nombre="",cantidad="",precio="";
        int id=0;
        msj.setText("");
        if (c.moveToLast()) {
            nombre = c.getString(0);
            cantidad = c.getString(1);
            id = c.getInt(3);
            precio = c.getString(2);



        }

        msj.setText("id:" + id + "\n" + "Nombre: " + nombre + "\n" + "cantidad: " + cantidad + "\n" +
                " Precio:" + precio + "\n");

        lm.close();
        enombre.setText("");
    }
}

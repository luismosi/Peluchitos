package com.example.gabriel.peluchitos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Realizar_venta extends AppCompatActivity {

    String nombre = "";
    private EditText Nombre, Cantidad;
    private TextView  Precio_total;
    SQLiteDatabase lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_venta);

        Nombre = (EditText)findViewById(R.id.eNombreV);
        Cantidad = (EditText)findViewById(R.id.eCantidadV);
        Precio_total = (TextView)findViewById(R.id.tPrecioT);

        BaseDatos Peluches = new BaseDatos(this, "Tienda Peluches", null, 2);
        lm = Peluches.getWritableDatabase();
    }

    public void vender_peluche (View view){

        nombre = Nombre.getText().toString();
        String cantidad = Cantidad.getText().toString();

        Toast.makeText(this, "Buscando Peluche a vender en la base de datos", Toast.LENGTH_SHORT).show();

        Tienda_Peluches(nombre,cantidad);

        Nombre.setText("");
        Cantidad.setText("");


    }

    protected void Tienda_Peluches(String nombre, String cantidad) {
        int Precio1=0;
        int totales=0;
        int canti=0;
        int cant=0;
        int actual_cant=0;
        int acomulado=0;
        int cantidades=0;
        String  Cant, Total, nueva_cantidad;

        BaseDatos Peluches = new BaseDatos(this, "TiendaPeluches", null, 2);
        lm = Peluches.getWritableDatabase();
        Cursor c = lm.rawQuery("SELECT nombre,cantidad,precio FROM Muñecos where nombre='" + nombre + "'", null);




        if(c.moveToFirst()) {
            Cant = c.getString(1);
            Precio1=c.getInt(2);
            canti = Integer.parseInt(cantidad);
            cant = Integer.parseInt(Cant);

            if(cant >=canti) {
                totales = canti * Precio1;
                Total=String.valueOf(totales);
                Precio_total.setText(Total);
                actual_cant = cant-canti;
                nueva_cantidad=String.valueOf(actual_cant);
                lm.execSQL("UPDATE Muñecos SET cantidad='" + nueva_cantidad + "' WHERE nombre='" + this.nombre + "'");

                Cursor v = lm.rawQuery("SELECT acomulado FROM producido", null);

                if(v.moveToLast()) {
                    acomulado =v.getInt(0);
                }

                acomulado+=totales;


                lm.execSQL("INSERT INTO producido (acomulado) VALUES ('" + acomulado + "') ");



                Toast.makeText(this, "Peluche Vendido", Toast.LENGTH_SHORT).show();

                if(actual_cant<= 4){

                    Notificacion(this.nombre, actual_cant);
                }


            }else
            {
                Toast.makeText(this, "No Existe La Cantidad Pedida", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "El peluche no existe", Toast.LENGTH_SHORT).show();
        }
    }
    public void Notificacion(String nombre, int actual_cant) {


        NotificationManager noti = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("alerta").setContentText("Quedan pocos peluches: "+nombre)
                .setTicker(String.valueOf(actual_cant)).setSmallIcon(R.drawable.pilas);

        Intent i = new Intent(Realizar_venta.this, Notificar.class);
        i.putExtra("Muñeco", nombre);
        i.putExtra("Cantidad", String.valueOf(actual_cant));
        PendingIntent noti2 = PendingIntent.getActivity(Realizar_venta.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(noti2);
        noti.notify(1992, builder.build());
    }




}

package com.example.gabriel.peluchitos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notificar extends AppCompatActivity {

    private TextView advertencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificar);

        advertencia = (TextView)findViewById(R.id.advertencia);

        Bundle extras = getIntent().getExtras();
        advertencia.setText("Queda: " + extras.getString("Cantidad") + " Peluches " + "\n" + " De: " + extras.getString("Muñeco"));
    }

    public void aceptar (View view){

        finish();
    }
}

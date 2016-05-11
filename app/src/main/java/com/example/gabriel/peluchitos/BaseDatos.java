package com.example.gabriel.peluchitos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GABRIEL on 10/05/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {


    //Sentencia SQL Para crear una tabla
    String sqlCreate = "CREATE TABLE Muñecos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad TEXT,precio INTEGER)";
    String sqlCreate3 = "CREATE TABLE producido(acomulado INTEGER)";

    public BaseDatos(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Muñecos");
        db.execSQL("DROP TABLE IF EXISTS producido");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate3);
    }
}

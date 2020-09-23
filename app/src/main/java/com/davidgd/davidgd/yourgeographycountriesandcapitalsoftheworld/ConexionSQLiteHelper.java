package com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld.utilidades.Utilidades;

import androidx.annotation.Nullable;

//Conecta con nuesra base de datos
public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Crea los scrips
    @Override
    public void onCreate(SQLiteDatabase db) {
        //crea la tabla
        db.execSQL(Utilidades.CREAR_TABLA_PUNTOSUSUARIO);
    }
    //Refresca los scrips
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //borra la tabla existente
        db.execSQL("DROP TABLE IF EXISTS puntosUsuario");
        //la vuelve a crear
        onCreate(db);
    }
}

package com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld.utilidades;

public class Utilidades {

    //constantes campos tabla usuario
    public static final String TABLA_PUNTOSUSUARIO = "puntosUsuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_MARCADOR = "marcador";

    //crea la tabla usuarios
    public static final String CREAR_TABLA_PUNTOSUSUARIO =
            "CREATE TABLE "+ TABLA_PUNTOSUSUARIO+
                    " ("+CAMPO_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+CAMPO_MARCADOR+" INTEGER NOT NULL) ";
}

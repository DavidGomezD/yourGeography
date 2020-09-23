package com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld.entidades;

public class puntosUsuario {

    private int id;
    private int marcador;

    public puntosUsuario(int id, int marcador) {
        this.id = id;
        this.marcador = marcador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarcador() {
        return marcador;
    }

    public void setMarcador(int marcador) {
        this.marcador = marcador;
    }
}

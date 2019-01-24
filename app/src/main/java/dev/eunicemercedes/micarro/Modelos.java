package dev.eunicemercedes.micarro;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Modelos")
public class Modelos {

    @PrimaryKey
    @ColumnInfo(name = "IDModelo")
    private int idModelo;

    @ColumnInfo(name = "Marca")
    private String marca;
    @ColumnInfo(name = "Modelo")
    private String modelo;
    @ColumnInfo(name = "Anio")
    private String anio;

    public Modelos(int idModelo, String marca, String modelo, String anio) {
        this.idModelo = idModelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Modelos{" +
                "idModelo=" + idModelo +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio='" + anio + '\'' +
                '}';
    }

    @NonNull
    public int getIdModelo() {
        return idModelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnio() {
        return anio;
    }
}

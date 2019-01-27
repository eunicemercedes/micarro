package dev.eunicemercedes.micarro.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Modelos")
public class Modelo {


    @PrimaryKey
    @ColumnInfo(name = "IDModelo")
    @SerializedName("ID")
    private int idModelo;

    @SerializedName("make")
    @ColumnInfo(name = "Marca")
    private String marca;

    @SerializedName("model")
    @ColumnInfo(name = "Modelo")
    private String modelo;

    @SerializedName("year")
    @ColumnInfo(name = "Anio")
    private String anio;

    public Modelo(int idModelo, String marca, String modelo, String anio) {
        this.idModelo = idModelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return marca + '\t' + modelo + '\t' + anio;
    }


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

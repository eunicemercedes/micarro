package dev.eunicemercedes.micarro.estaciones;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Estaciones")
public class Estaciones {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoEstacion")
    private int codigoEstacion;

    @ColumnInfo(name = "Nombre")
    private String nombre;

    @ColumnInfo(name = "Activo")
    private boolean activo;

    public Estaciones(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Estaciones{" +
                "codigoEstacion=" + codigoEstacion +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                '}';
    }

    public int getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(int codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActivo() {
        return activo;
    }
}

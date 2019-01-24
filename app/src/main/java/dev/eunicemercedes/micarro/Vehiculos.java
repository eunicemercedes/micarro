package dev.eunicemercedes.micarro;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.time.Instant;
import java.util.Date;

@Entity(tableName = "Vehiculos")
public class Vehiculos {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoVehiculo")
    private int codigoVehiculo;

    @ColumnInfo(name = "IDModelo")
    private int idModelo;

    @ColumnInfo(name = "Nombre")
    private String nombre;

    @ColumnInfo(name = "FechaInicio")
    private String fechaInicio;

    @ColumnInfo(name = "Activo")
    private boolean activo;

    public Vehiculos(int idModelo, String nombre, String fechaInicio, boolean activo) {

        this.idModelo = idModelo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Vehiculos{" +
                "codigoVehiculo=" + codigoVehiculo +
                ", idModelo=" + idModelo +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", activo=" + activo +
                '}';
    }

    public int getCodigoVehiculo() {
        return codigoVehiculo;
    }

    void setCodigoVehiculo(int codigoVehiculo) {
        this.codigoVehiculo = codigoVehiculo;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public String getNombre() {
        return nombre;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }

    public boolean isActivo() {
        return activo;
    }
}

package dev.eunicemercedes.micarro.mantenimiento;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Mantenimientos")
public class Mantenimiento {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoMantenimiento")
    int codigoMantenimiento;

    @ColumnInfo(name = "CodigoVehiculo")
    int codigoVehiculo;

    @ColumnInfo(name = "CodigoEstaciones")
    int codigoEstaciones;


    @ColumnInfo(name = "CodigoTipoMantenimiento")
    int codigoTipoMantenimiento;//gasolina - cambio aceite - gomas - bateria - reparacion

    @ColumnInfo(name = "ModoMantenimiento")
    int modoMantenimiento;//PREVENTIVO - CORRECTIVO - CONSUMO


    @ColumnInfo(name = "Kilometraje")
    Double kilometrajeActual;

    @ColumnInfo(name = "CodigoMetodoPago")
    int codigoMetodoPago;

    @ColumnInfo(name = "Cantidad")
    double cantidad;

    @ColumnInfo(name = "Costo")
    double costo;

    @ColumnInfo(name = "KilometrajeProximoMantenimiento")
    double kilometrajeProximoMantenimiento;

    @ColumnInfo(name = "Comentario")
    String comentario;

    public Mantenimiento(int codigoVehiculo, int codigoEstaciones, int codigoTipoMantenimiento, int modoMantenimiento, Double kilometrajeActual, int codigoMetodoPago, double cantidad, double costo, double kilometrajeProximoMantenimiento, String comentario) {
        this.codigoVehiculo = codigoVehiculo;
        this.codigoEstaciones = codigoEstaciones;
        this.codigoTipoMantenimiento = codigoTipoMantenimiento;
        this.modoMantenimiento = modoMantenimiento;
        this.kilometrajeActual = kilometrajeActual;
        this.codigoMetodoPago = codigoMetodoPago;
        this.cantidad = cantidad;
        this.costo = costo;
        this.kilometrajeProximoMantenimiento = kilometrajeProximoMantenimiento;
        this.comentario = comentario;
    }

    @Ignore
    public Mantenimiento(int codigoVehiculo, int codigoEstaciones, int codigoTipoMantenimiento, int modoMantenimiento, Double kilometrajeActual, int codigoMetodoPago, double cantidad, double costo, String comentario) {
        this.codigoVehiculo = codigoVehiculo;
        this.codigoEstaciones = codigoEstaciones;
        this.codigoTipoMantenimiento = codigoTipoMantenimiento;
        this.modoMantenimiento = modoMantenimiento;
        this.kilometrajeActual = kilometrajeActual;
        this.codigoMetodoPago = codigoMetodoPago;
        this.cantidad = cantidad;
        this.costo = costo;
        this.comentario = comentario;
    }

    public int getCodigoMantenimiento() {
        return codigoMantenimiento;
    }

    public void setCodigoMantenimiento(int codigoMantenimiento) {
        this.codigoMantenimiento = codigoMantenimiento;
    }

    public int getCodigoVehiculo() {
        return codigoVehiculo;
    }

    public void setCodigoVehiculo(int codigoVehiculo) {
        this.codigoVehiculo = codigoVehiculo;
    }

    public int getCodigoEstaciones() {
        return codigoEstaciones;
    }

    public void setCodigoEstaciones(int codigoEstaciones) {
        this.codigoEstaciones = codigoEstaciones;
    }

    public int getCodigoTipoMantenimiento() {
        return codigoTipoMantenimiento;
    }

    public void setCodigoTipoMantenimiento(int codigoTipoMantenimiento) {
        this.codigoTipoMantenimiento = codigoTipoMantenimiento;
    }

    public int getModoMantenimiento() {
        return modoMantenimiento;
    }

    public void setModoMantenimiento(int modoMantenimiento) {
        this.modoMantenimiento = modoMantenimiento;
    }


    public Double getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(Double kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getKilometrajeProximoMantenimiento() {
        return kilometrajeProximoMantenimiento;
    }

    public void setKilometrajeProximoMantenimiento(double kilometrajeProximoMantenimiento) {
        this.kilometrajeProximoMantenimiento = kilometrajeProximoMantenimiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

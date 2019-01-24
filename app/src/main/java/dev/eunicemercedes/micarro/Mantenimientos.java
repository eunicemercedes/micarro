package dev.eunicemercedes.micarro;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Mantenimientos")
public class Mantenimientos {
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

    @ColumnInfo(name = "Nombre")
    String nombre;


    //EFECTIVO - TARJETA - CREDITO - CUPON
    @ColumnInfo(name = "TipoMetodoPago")
    String tipoMetodoPago;

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
}

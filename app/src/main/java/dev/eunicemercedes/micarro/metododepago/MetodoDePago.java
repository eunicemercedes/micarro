package dev.eunicemercedes.micarro.metododepago;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MetodoDePago")
public class MetodoDePago {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoMetodoPago")
    int codigoMetodoPago;

    @ColumnInfo(name = "Nombre")
    String nombre;
    //EFECTIVO - TARJETA - CREDITO - CUPON
    @ColumnInfo(name = "TipoMetodoPago")
    String tipoMetodoPago;

    @ColumnInfo(name = "Activo")
    boolean activo;
}

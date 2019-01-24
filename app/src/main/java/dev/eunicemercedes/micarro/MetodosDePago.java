package dev.eunicemercedes.micarro;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MetodosDePago")
public class MetodosDePago {

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

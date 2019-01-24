package dev.eunicemercedes.micarro;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TipoMantenimiento")
public class TipoMantenimiento {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoTipoMantenimiento")
    int codigoMetodoPago;

    @ColumnInfo(name = "Nombre")
    String nombre;


    @ColumnInfo(name = "Activo")
    boolean activo;
}

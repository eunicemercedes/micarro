package dev.eunicemercedes.micarro.metododepago;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MetodosDePago")
public class MetodoDePago {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodigoMetodoPago")
    int codigoMetodoPago;

    @ColumnInfo(name = "Nombre")
    String nombre;
    //EFECTIVO - TARJETA - CREDITO - CUPON
    @ColumnInfo(name = "TipoMetodoPago")
    int tipoMetodoPago;

    @ColumnInfo(name = "Activo")
    boolean activo;

    public MetodoDePago(String nombre, int tipoMetodoPago, boolean activo) {
        this.nombre = nombre;
        this.tipoMetodoPago = tipoMetodoPago;
        this.activo = activo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoMetodoPago() {
        return tipoMetodoPago;
    }

    public void setTipoMetodoPago(int tipoMetodoPago) {
        this.tipoMetodoPago = tipoMetodoPago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }
}

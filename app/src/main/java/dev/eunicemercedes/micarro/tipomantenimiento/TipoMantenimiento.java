package dev.eunicemercedes.micarro.tipomantenimiento;


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

    public TipoMantenimiento(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

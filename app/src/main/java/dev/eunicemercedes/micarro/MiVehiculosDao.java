package dev.eunicemercedes.micarro;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dev.eunicemercedes.micarro.estaciones.Estacion;
import dev.eunicemercedes.micarro.mantenimiento.Mantenimiento;
import dev.eunicemercedes.micarro.metododepago.MetodoDePago;
import dev.eunicemercedes.micarro.modelo.Modelo;
import dev.eunicemercedes.micarro.tipomantenimiento.TipoMantenimiento;
import dev.eunicemercedes.micarro.vehiculo.Vehiculo;

@Dao
public interface MiVehiculosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarMantenimiento(Mantenimiento mantenimiento);

    @Query("select * from Vehiculos")
    LiveData<List<Vehiculo>> listarVehiculos();

    @Query("select nombre from Vehiculos where Activo=1")
    List<String> listarNombreVehiculosActivos();

    @Query("select * from Vehiculos where Nombre=:nombre limit 1")
    Vehiculo filtrarVehiculo(String nombre);


    @Query("select CodigoVehiculo from Vehiculos where Nombre=:nombre")
    int obtenerCodigoVehiculoPorNombre(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarVehiculo(Vehiculo vehiculo);

    @Query("select * from modelos")
    LiveData<List<Modelo>> listarModelos();

    @Query("select * from modelos where modelos.IDModelo=:idModelo limit 1")
    Modelo buscarModelos(int idModelo);

    @Query("select distinct marca from Modelos")
    List<String> listarMarcas();

    @Query("select distinct modelo from Modelos where marca=:marca")
    List<String> listarModelos(String marca);

    @Query("select IDModelo from Modelos where marca=:marca and modelo=:modelo and Anio=:anio")
    int buscarCodigoModelo(String marca, String modelo, int anio);

    @Query("select distinct anio from Modelos where marca=:marca and modelo=:modelo")
    List<String> listarAnios(String marca, String modelo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarModelo(Modelo modelo);

    @Query("select * from TipoMantenimiento")
    LiveData<List<TipoMantenimiento>> listarTipoMantenimiento();

    @Query("select nombre from TipoMantenimiento where Activo=1")
    List<String> listarNombreTipoMantenimientoActivos();

    @Query("select * from TipoMantenimiento where Nombre=:nombre limit 1")
    TipoMantenimiento listarTipoMantenimiento(String nombre);

    @Query("select CodigoTipoMantenimiento from TipoMantenimiento where Nombre=:nombre")
    int obtenerCodigoTipoMantenimientoPorNombre(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarTipoMantenimiento(TipoMantenimiento tipoMantenimiento);


    @Query("select * from MetodosDePago")
    LiveData<List<MetodoDePago>> listarMetodosDePago();

    @Query("select nombre from MetodosDePago where Activo=1")
    List<String> listarNombreMetodosDePagoActivos();

    @Query("select * from MetodosDePago where Nombre=:nombre limit 1")
    MetodoDePago listarMetodosDePago(String nombre);

    @Query("select CodigoMetodoPago from MetodosDePago where Nombre=:nombre")
    int obtenerCodigoMetodoPagoPorNombre(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarMetodoPago(MetodoDePago metodoDePago);


    @Query("select * from Estaciones")
    LiveData<List<Estacion>> listarEstaciones();

    @Query("select nombre from Estaciones where Activo=1")
    List<String> listarNombreEstacionesActivos();

    @Query("select  * from Estaciones where Nombre=:nombre limit 1")
    Estacion listarEstaciones(String nombre);

    @Query("select CodigoEstacion from Estaciones where Nombre=:nombre")
    int obtenerCodigoEstacionPorNombre(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void agregarEstacion(Estacion estacion);

    @Update
    abstract void actualizarEstacion(Estacion estacion);

}

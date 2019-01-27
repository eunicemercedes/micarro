package dev.eunicemercedes.micarro;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import dev.eunicemercedes.micarro.estaciones.Estacion;
import dev.eunicemercedes.micarro.metododepago.MetodoDePago;
import dev.eunicemercedes.micarro.modelo.Modelo;
import dev.eunicemercedes.micarro.tipomantenimiento.TipoMantenimiento;
import dev.eunicemercedes.micarro.vehiculo.Vehiculo;

@Database(entities = {Estacion.class, Mantenimientos.class, MetodoDePago.class, Modelo.class, TipoMantenimiento.class, Vehiculo.class}, version = 1)
public abstract class MiCarroDB extends RoomDatabase {
    private static MiCarroDB INSTANCE;

    public static MiCarroDB getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            MiCarroDB.class, "MiCarro-Database.db")
                            //.allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;

    }

    public abstract MiVehiculosDao miVehiculosDao();
}

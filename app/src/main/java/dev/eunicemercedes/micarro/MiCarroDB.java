package dev.eunicemercedes.micarro;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import dev.eunicemercedes.micarro.estaciones.Estaciones;
import dev.eunicemercedes.micarro.metododepago.MetodoDePago;
import dev.eunicemercedes.micarro.tipomantenimiento.TipoMantenimiento;

@Database(entities = {Estaciones.class, Mantenimientos.class, MetodoDePago.class, Modelos.class, TipoMantenimiento.class, Vehiculos.class}, version = 1)
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

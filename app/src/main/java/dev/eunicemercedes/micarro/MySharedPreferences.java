package dev.eunicemercedes.micarro;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class MySharedPreferences {
    private static final String PREF_NAME_DB = "MiCarroPrefs";


    public static String mostrarValorSharedPreference(String campo, Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREF_NAME_DB, Context.MODE_PRIVATE);
        return prefs.getString(campo, "");
    }

    public static void guardarValorSharedPreference(String campo, String valor, Context context) {
        SharedPreferences.Editor prefs =
                context.getSharedPreferences(PREF_NAME_DB, Context.MODE_PRIVATE).edit();
        prefs.putString(campo, valor);
        prefs.commit();
    }


}

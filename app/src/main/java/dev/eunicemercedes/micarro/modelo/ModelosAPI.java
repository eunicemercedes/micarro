package dev.eunicemercedes.micarro.modelo;

import android.content.Context;
import android.media.midi.MidiOutputPort;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelosAPI {


    final static String BASE_URL = "https://doc-10-0g-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/4sh9ntjjkvvuh96h08n1spo92q7jfsr1/1548676800000/15963182392774467456/*/";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder()
                            .setLenient()
                            .create()
            ))
            .build();

    public static void syncModelo(final Context contexto) {
        ModelosServices service = retrofit.create(ModelosServices.class);
        Call<List<Modelo>> sincronizarMarcas = service.getModelo();
        Log.i("Pre onResponse", sincronizarMarcas.request().url().toString());
        sincronizarMarcas.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {

                if (response != null) {
                    if (response.isSuccessful()) {
                        Log.i("onResponse", "Sync Completada");
                        Log.i("onResponse", response.body().toString());
                        List<Modelo> modelos = response.body();
                        SyncModelos syncModelos = new SyncModelos(contexto, modelos);
                        syncModelos.execute();
                    } else {
                        Log.i("onResponse", " Response Not Successful");
                    }
                } else {
                    Log.i("onResponse", "Response Is Null");
                }
            }

            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {
                Toast.makeText(contexto, "Sincronizacion Fallida", Toast.LENGTH_LONG).show();
                Log.i("onResponse", t.toString());
            }
        });

    }

    public static void syncModel(final Context contexto) {
        ModelosServices service = retrofit.create(ModelosServices.class);
        Call<Modelos> sincronizarMarcas = service.getModels();
        Log.i("Pre onResponse", sincronizarMarcas.request().url().toString());
        sincronizarMarcas.enqueue(new Callback<Modelos>() {
            @Override
            public void onResponse(Call<Modelos> call, Response<Modelos> response) {

                if (response != null) {
                    if (response.isSuccessful()) {
                        Log.i("onResponse", "Sync Completada");
                        Log.i("onResponse", response.body().toString());
                        Modelos modelos = response.body();
                        SyncModelos syncModelos = new SyncModelos(contexto, modelos.getModelos());
                        syncModelos.execute();
                    } else {
                        Log.i("onResponse", " Response Not Successful");
                    }
                } else {
                    Log.i("onResponse", "Response Is Null");
                }
            }

            @Override
            public void onFailure(Call<Modelos> call, Throwable t) {
                Toast.makeText(contexto, "Sincronizacion Fallida", Toast.LENGTH_LONG).show();
                Log.i("onResponse", t.toString());
            }
        });

    }


}

class SyncModelos extends AsyncTask<Void, Void, Boolean> {
    Context context;
    List<Modelo> modelos;

    public SyncModelos(Context context, List<Modelo> modelos) {
        this.context = context;
        this.modelos = modelos;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        MiCarroDB.getINSTANCE(context).runInTransaction(
                new Runnable() {
                    @Override
                    public void run() {
                        for (Modelo m : modelos) {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarModelo(m);
                        }

                    }
                }

        );
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

        Toast.makeText(context, "Sincronizacion Completada", Toast.LENGTH_LONG);

    }

}

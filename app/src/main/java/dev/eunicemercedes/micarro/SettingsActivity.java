package dev.eunicemercedes.micarro;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    Spinner vehiculosSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        vehiculosSpinner = findViewById(R.id.vehiculoPreferidoSpinner);
        new LlenarDropdown(this).execute();
        ImageButton guardar = findViewById(R.id.editButton);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarvehiculoPreferido(vehiculosSpinner.getSelectedItem().toString());
            }
        });
    }

    public void guardarvehiculoPreferido(String nombreVehiculo) {
        MySharedPreferences.guardarValorSharedPreference("vehiculoPreferido", (nombreVehiculo), this);
        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
    }

    class LlenarDropdown extends AsyncTask<Void, Void, Void> {
        List<String> vehiculos;
        Context context;

        public LlenarDropdown(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(context,
                            R.layout.support_simple_spinner_dropdown_item, vehiculos);
            vehiculosSpinner.setAdapter(adapter);
            String nombreVehiculo = MySharedPreferences.mostrarValorSharedPreference("vehiculoPreferido", context);
            if (nombreVehiculo != null) {
                if (nombreVehiculo.length() > 0) {
                    vehiculosSpinner.setSelection(adapter.getPosition(nombreVehiculo));
                }
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(new Runnable() {
                @Override
                public void run() {
                    vehiculos =
                            MiCarroDB.getINSTANCE(context)
                                    .miVehiculosDao().listarNombreVehiculosActivos();
                }
            });
            return null;
        }
    }
}

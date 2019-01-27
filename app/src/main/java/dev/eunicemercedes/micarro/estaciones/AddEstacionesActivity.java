package dev.eunicemercedes.micarro.estaciones;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class AddEstacionesActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    ImageButton editFloatinActionButton;
    Estacion estacion;
    private boolean editMode;

    public void setEstacion(final String nombreEstacion) {
        new BuscarEstaciones(this, nombreEstacion).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode_view_basico_activity);
        editFloatinActionButton = findViewById(R.id.editButton);
        nombre = findViewById(R.id.nombreEditText);
        activo = findViewById(R.id.activoCheckBox);
        cargarDatos();
        editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().length() > 0) {
                    if (estacion == null) {
                        estacion = new Estacion(nombre.getText().toString(),
                                activo.isChecked());
                    } else {
                        estacion.setNombre(nombre.getText().toString());
                        estacion.setActivo(activo.isChecked());
                    }
                    new ActualizarEstaciones(AddEstacionesActivity.this, estacion).execute();


                }
            }
        });
    }

    public void limpiar() {
        nombre.setText("");
        activo.setChecked(true);
    }

    public void cargarDatos() {
        if (getIntent() != null) {
            String nombreEstacion = getIntent().getStringExtra("nombreEstacion");
            if (nombreEstacion != null) {
                if (nombreEstacion.length() > 0) {
                    this.setEstacion(nombreEstacion);
                    if (estacion != null) {
                        nombre.setText(estacion.getNombre());
                        activo.setChecked(estacion.isActivo());
                        editMode = true;
                    } else {
                        limpiar();
                        editMode = false;
                    }
                }
            } else {
                limpiar();
                editMode = false;
            }
        } else {
            limpiar();
            editMode = false;
        }
    }

    class ActualizarEstaciones extends AsyncTask<Void, Void, Boolean> {
        Context context;
        Estacion estacion;

        public ActualizarEstaciones(Context context, Estacion estacion) {
            this.context = context;
            this.estacion = estacion;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarEstacion(estacion);
                        }
                    }

            );
            return true;
        }
    }

    class BuscarEstaciones extends AsyncTask<Void, Void, Estacion> {
        Context context;
        String nombreEstacion;
        Estacion resultado;

        public BuscarEstaciones(Context context, String nombreEstacion) {
            this.context = context;
            this.nombreEstacion = nombreEstacion;
        }

        @Override
        protected Estacion doInBackground(Void... voids) {

            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            resultado = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarEstaciones(nombreEstacion);
                        }
                    }

            );
            return resultado;
        }

        @Override
        protected void onPostExecute(Estacion resultado) {
            if (resultado != null) {
                nombre.setText(resultado.getNombre());
                activo.setChecked(resultado.isActivo());
                Toast.makeText(context, "Datos Cargados", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Datos NO Cargados", Toast.LENGTH_LONG);
            }
            estacion = resultado;
        }
    }
}

package dev.eunicemercedes.micarro.estaciones;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.stateful.ExtendableSavedState;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class AddEstacionesActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    ImageButton editFloatinActionButton;
    Estaciones estacion;
    private boolean editMode;

    public void setEstacion(String nombreEstacion) {
        this.estacion = MiCarroDB.getINSTANCE(AddEstacionesActivity.this)
                .miVehiculosDao().listarEstaciones(nombreEstacion);
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
                        estacion = new Estaciones(nombre.getText().toString(),
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
                    nombre.setText(estacion.getNombre());
                    activo.setChecked(estacion.isActivo());
                    editMode = true;
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
        Estaciones estacion;

        public ActualizarEstaciones(Context context, Estaciones estaciones) {
            this.context = context;
            this.estacion = estaciones;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Log.i("saving Estaciones", estacion.toString());
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarEstacion(estacion);
                        }
                    }

            );

          /*  if (estacion.getCodigoEstacion() == 0) {

                MiCarroDB.getINSTANCE(context)
                        .miVehiculosDao().agregarEstacion(estacion);
            } else {
                MiCarroDB.getINSTANCE(context)
                        .miVehiculosDao().actualizarEstacion(estacion);
            }*/
            return true;
        }


    }
}

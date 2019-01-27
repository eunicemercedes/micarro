package dev.eunicemercedes.micarro.vehiculo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;
import dev.eunicemercedes.micarro.modelo.Modelo;

public class AddVehiculoActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    Spinner modelo;
    Spinner marca;
    Spinner anio;
    ImageButton editFloatinActionButton;
    Vehiculo vehiculo;
    private boolean editMode;

    public void setVehiculo(final String nombreVehiculo) {
        new BuscarVehiculo(this, nombreVehiculo).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode_vehiculo_activity);
        editFloatinActionButton = findViewById(R.id.editButton);
        nombre = findViewById(R.id.nombreEditText);
        activo = findViewById(R.id.activoCheckBox);

        cargarDatos();
        editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().length() > 0) {
                    if (vehiculo == null) {
                        //TODO: AGREGAR MODELO
                        vehiculo = new Vehiculo(7470, nombre.getText().toString(), activo.isChecked());
                    } else {
                        //TODO:AGREGAR MODELO
                        vehiculo.setNombre(nombre.getText().toString());
                        vehiculo.setActivo(activo.isChecked());
                    }
                    new ActualizarVehiculo(AddVehiculoActivity.this, vehiculo).execute();


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
            String nombreVehiculo = getIntent().getStringExtra("nombreVehiculo");
            if (nombreVehiculo != null) {
                if (nombreVehiculo.length() > 0) {
                    this.setVehiculo(nombreVehiculo);
                    if (vehiculo != null) {
                        nombre.setText(vehiculo.getNombre());
                        activo.setChecked(vehiculo.isActivo());
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

    class ActualizarVehiculo extends AsyncTask<Void, Void, Boolean> {
        Context context;
        Vehiculo vehiculo;

        public ActualizarVehiculo(Context context, Vehiculo vehiculo) {
            this.context = context;
            this.vehiculo = vehiculo;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarVehiculo(vehiculo);
                        }
                    }

            );
            return true;
        }
    }

    class BuscarVehiculo extends AsyncTask<Void, Void, Vehiculo> {
        Context context;
        String nombreVehiculo;
        Vehiculo resultado;
        Modelo modelo;

        public BuscarVehiculo(Context context, String nombreVehiculo) {
            this.context = context;
            this.nombreVehiculo = nombreVehiculo;
        }

        @Override
        protected Vehiculo doInBackground(Void... voids) {

            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            resultado = MiCarroDB.getINSTANCE(context).miVehiculosDao().filtrarVehiculo(nombreVehiculo);
                            if (resultado != null) {
                                modelo = MiCarroDB.getINSTANCE(context).miVehiculosDao().buscarModelos(resultado.getIdModelo());
                            }
                        }
                    }

            );
            return resultado;
        }

        @Override
        protected void onPostExecute(Vehiculo resultado) {
            if (resultado != null) {
                nombre.setText(resultado.getNombre());
                activo.setChecked(resultado.isActivo());
                //datosVehiculo.setText(modelo.toString());
                Toast.makeText(context, "Datos Cargados", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Datos NO Cargados", Toast.LENGTH_LONG);
            }
            vehiculo = resultado;
        }
    }
}

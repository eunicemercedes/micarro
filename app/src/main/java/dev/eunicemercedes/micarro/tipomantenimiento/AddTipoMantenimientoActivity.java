package dev.eunicemercedes.micarro.tipomantenimiento;

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

public class AddTipoMantenimientoActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    ImageButton editFloatinActionButton;
    TipoMantenimiento tipoMantenimiento;
    private boolean editMode;

    public void setEstacion(final String nombreTipoMantenimiento) {
        new BuscarTipoMantenimiento(this, nombreTipoMantenimiento).execute();
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
                    if (tipoMantenimiento == null) {
                        tipoMantenimiento = new TipoMantenimiento(nombre.getText().toString(),
                                activo.isChecked());
                    } else {
                        tipoMantenimiento.setNombre(nombre.getText().toString());
                        tipoMantenimiento.setActivo(activo.isChecked());
                    }
                    new ActualizarTipoMantenimiento(AddTipoMantenimientoActivity.this, tipoMantenimiento).execute();


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
            String nombreTipoMantenimiento = getIntent().getStringExtra("nombreTipoMantenimiento");
            if (nombreTipoMantenimiento != null) {
                if (nombreTipoMantenimiento.length() > 0) {
                    this.setEstacion(nombreTipoMantenimiento);
                    if (tipoMantenimiento != null) {
                        nombre.setText(tipoMantenimiento.getNombre());
                        activo.setChecked(tipoMantenimiento.isActivo());
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

    class ActualizarTipoMantenimiento extends AsyncTask<Void, Void, Boolean> {
        Context context;
        TipoMantenimiento tipoMantenimiento;

        public ActualizarTipoMantenimiento(Context context, TipoMantenimiento tipoMantenimiento) {
            this.context = context;
            this.tipoMantenimiento = tipoMantenimiento;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarTipoMantenimiento(tipoMantenimiento);
                        }
                    }

            );
            return true;
        }
    }

    class BuscarTipoMantenimiento extends AsyncTask<Void, Void, TipoMantenimiento> {
        Context context;
        String nombreTipoMantenimiento;
        TipoMantenimiento resultado;

        public BuscarTipoMantenimiento(Context context, String nombreTipoMantenimiento) {
            this.context = context;
            this.nombreTipoMantenimiento = nombreTipoMantenimiento;
        }

        @Override
        protected TipoMantenimiento doInBackground(Void... voids) {

            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            resultado = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarTipoMantenimiento(nombreTipoMantenimiento);
                        }
                    }

            );
            return resultado;
        }

        @Override
        protected void onPostExecute(TipoMantenimiento resultado) {
            if (resultado != null) {
                nombre.setText(resultado.getNombre());
                activo.setChecked(resultado.isActivo());
                Toast.makeText(context, "Datos Cargados", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Datos NO Cargados", Toast.LENGTH_LONG);
            }
            tipoMantenimiento = resultado;
        }
    }
}

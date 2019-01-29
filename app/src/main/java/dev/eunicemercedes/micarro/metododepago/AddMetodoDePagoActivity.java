package dev.eunicemercedes.micarro.metododepago;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;


public class AddMetodoDePagoActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    ImageButton editFloatinActionButton;
    Spinner tipoMetodoPago;
    MetodoDePago metodoDePago;
    private boolean editMode;

    public void setMetodoDePago(final String nombreMetodoDePago) {
        new BuscarMetodoDePago(this, nombreMetodoDePago).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode_metodo_pago_activity);
        editFloatinActionButton = findViewById(R.id.editButton);
        nombre = findViewById(R.id.nombreEditText);
        tipoMetodoPago = findViewById(R.id.tipoPagoSpinner);
        activo = findViewById(R.id.activoCheckBox);
        cargarDatos();
        editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().length() > 0) {
                    if (metodoDePago == null) {
                        metodoDePago = new MetodoDePago(nombre.getText().toString(), tipoMetodoPago.getSelectedItemPosition(),
                                activo.isChecked());
                    } else {
                        metodoDePago.setNombre(nombre.getText().toString());
                        metodoDePago.setActivo(activo.isChecked());
                        metodoDePago.setTipoMetodoPago(tipoMetodoPago.getSelectedItemPosition());
                    }
                    new ActualizarMetodoDePago(AddMetodoDePagoActivity.this, metodoDePago).execute();


                }
            }
        });
    }

    public void limpiar() {
        nombre.setText("");
        activo.setChecked(true);
        tipoMetodoPago.setSelection(0);
    }

    public void cargarDatos() {
        if (getIntent() != null) {
            String nombreMetodoDePago = getIntent().getStringExtra("nombreMetodoDePago");
            if (nombreMetodoDePago != null) {
                if (nombreMetodoDePago.length() > 0) {
                    this.setMetodoDePago(nombreMetodoDePago);
                    if (metodoDePago != null) {
                        nombre.setText(metodoDePago.getNombre());
                        activo.setChecked(metodoDePago.isActivo());
                        tipoMetodoPago.setSelection(metodoDePago.getTipoMetodoPago());
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

    class ActualizarMetodoDePago extends AsyncTask<Void, Void, Boolean> {
        Context context;
        MetodoDePago metodoDePago;

        public ActualizarMetodoDePago(Context context, MetodoDePago metodoDePagoes) {
            this.context = context;
            this.metodoDePago = metodoDePagoes;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context)
                                    .miVehiculosDao().agregarMetodoPago(metodoDePago);
                        }
                    }

            );
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            Toast.makeText(context, "Datos Guardados", Toast.LENGTH_LONG);

        }

    }

    class BuscarMetodoDePago extends AsyncTask<Void, Void, MetodoDePago> {
        Context context;
        String nombreMetodoDePago;
        MetodoDePago resultado;

        public BuscarMetodoDePago(Context context, String nombreMetodoDePago) {
            this.context = context;
            this.nombreMetodoDePago = nombreMetodoDePago;
        }

        @Override
        protected MetodoDePago doInBackground(Void... voids) {

            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            resultado = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarMetodosDePago(nombreMetodoDePago);
                        }
                    }

            );
            return resultado;
        }

        @Override
        protected void onPostExecute(MetodoDePago resultado) {
            if (resultado != null) {
                nombre.setText(resultado.getNombre());
                activo.setChecked(resultado.isActivo());
                Toast.makeText(context, "Datos Cargados", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Datos NO Cargados", Toast.LENGTH_LONG);
            }
            metodoDePago = resultado;
        }
    }
}

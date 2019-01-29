package dev.eunicemercedes.micarro.mantenimiento;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class AddMantenimientoActivity extends AppCompatActivity {
    ImageButton editFloatinActionButton;
    Spinner tipoMetodoPago;
    Spinner estacion;
    Spinner tipoMantenimiento;
    Spinner modoMantenimiento;
    Spinner metodoPago;
    EditText cantidad, costo, kmActual, kmProximo, comentario;
    Mantenimiento mantenimiento;
    int codigoVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode_mantenimiento_activity);
        editFloatinActionButton = findViewById(R.id.editButton);
        comentario = findViewById(R.id.comentarioEditText);
        cantidad = findViewById(R.id.cantidadEditText);
        tipoMetodoPago = findViewById(R.id.tipoPagoSpinner);
        estacion = findViewById(R.id.estacionSpinner);
        tipoMantenimiento = findViewById(R.id.tipoMantenimientoSpinner);
        modoMantenimiento = findViewById(R.id.modoMantenimientoSpinner);
        metodoPago = findViewById(R.id.metodoPagoSpinner);
        costo = findViewById(R.id.costoEditText);
        kmActual = findViewById(R.id.kmEditText);
        kmProximo = findViewById(R.id.kmProximoEditText);

        new LlenarDropdown(this).execute();
        editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantidad.getText().length() > 0 && costo.getText().length() > 0) {
                    mantenimiento = new Mantenimiento(

                            codigoVehiculo, 0, 0, modoMantenimiento.getSelectedItemPosition(), Double.parseDouble(kmActual.getText().toString()),
                            0, Double.parseDouble(cantidad.getText().toString()), Double.parseDouble(costo.getText().toString())
                            , Double.parseDouble(kmProximo.getText().toString()), comentario.getText().toString());
                    new ActualizarMantenimiento(AddMantenimientoActivity.this, mantenimiento).execute();


                }
            }
        });
    }


    class ActualizarMantenimiento extends AsyncTask<Void, Void, Boolean> {
        Context context;
        Mantenimiento mantenimiento;
        String nombreEstacion, nombreTipoMantenimiento, nombreMetodoPago;

        public ActualizarMantenimiento(Context context, Mantenimiento mantenimiento) {
            this.context = context;
            this.mantenimiento = mantenimiento;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            MiCarroDB.getINSTANCE(context).miVehiculosDao().agregarMantenimiento(mantenimiento);
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

    class LlenarDropdown extends AsyncTask<Void, Void, Void> {
        Context context;

        List<String> estaciones, tipoMant, metodoPagos;
        int codigoModelo;

        public LlenarDropdown(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            estaciones =
                                    MiCarroDB.getINSTANCE(context)
                                            .miVehiculosDao().listarNombreEstacionesActivos()
                            ;
                            tipoMant =
                                    MiCarroDB.getINSTANCE(context)
                                            .miVehiculosDao().listarNombreTipoMantenimientoActivos()
                            ;
                            metodoPagos =
                                    MiCarroDB.getINSTANCE(context)
                                            .miVehiculosDao().listarNombreMetodosDePagoActivos();

                        }
                    }
            );
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (!estaciones.isEmpty()) {
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(context,
                                R.layout.support_simple_spinner_dropdown_item, estaciones);
                estacion.setAdapter(adapter);
            }
            if (!tipoMant.isEmpty()) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        R.layout.support_simple_spinner_dropdown_item, tipoMant);
                tipoMantenimiento.setAdapter(adapter);
            }
            if (!metodoPagos.isEmpty()) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        R.layout.support_simple_spinner_dropdown_item, metodoPagos);
                metodoPago.setAdapter(adapter);

            }


        }


    }

}

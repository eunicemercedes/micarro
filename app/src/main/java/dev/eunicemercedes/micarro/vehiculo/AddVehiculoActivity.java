package dev.eunicemercedes.micarro.vehiculo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;
import dev.eunicemercedes.micarro.modelo.Modelo;

public class AddVehiculoActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    Spinner modeloSpinner;
    Spinner marcaSpinner;
    Spinner anioSpinner;
    TextView label;
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
        nombre = findViewById(R.id.nombreVehiculoEditText);
        activo = findViewById(R.id.activoVehiculoCheckBox);
        label = findViewById(R.id.textView4);
        modeloSpinner = findViewById(R.id.modeloSpinner);
        marcaSpinner = findViewById(R.id.marcaSpinner);
        anioSpinner = findViewById(R.id.anioSpinner);


        cargarDatos();

        if (editMode) {
            ((TextView) findViewById(R.id.textView3)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView5)).setVisibility(View.GONE);
            modeloSpinner.setVisibility(View.GONE);
            marcaSpinner.setVisibility(View.GONE);
            anioSpinner.setVisibility(View.GONE);
        } else {
            new LlenarDropdown(this, "", "", "").execute();
        }
        marcaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new LlenarDropdown(AddVehiculoActivity.this, parent.getItemAtPosition(position).toString(), "", "").execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        modeloSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new LlenarDropdown(AddVehiculoActivity.this, marcaSpinner.getSelectedItem().toString(), parent.getItemAtPosition(position).toString(), "").execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        anioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                new LlenarDropdown(AddVehiculoActivity.this, marcaSpinner.getSelectedItem().toString(), modeloSpinner.getSelectedItem().toString(), parent.getItemAtPosition(position).toString()).execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().length() > 0) {
                    if (vehiculo == null) {

                        vehiculo = new Vehiculo(nombre.getText().toString(), activo.isChecked());
                    } else {
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

        @Override
        protected void onProgressUpdate(Void... values) {
            Toast.makeText(context, "Vehiculo Guardado", Toast.LENGTH_LONG).show();
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
                if (modelo != null) {
                    label.setText("Modelo: " + modelo.toString());
                }
                Toast.makeText(context, "Datos Cargados", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Datos NO Cargados", Toast.LENGTH_LONG);
            }
            vehiculo = resultado;
        }
    }

    class LlenarDropdown extends AsyncTask<Void, Void, Void> {
        Context context;
        String marca, modelo, anio;
        List<String> lista;
        int codigoModelo;

        public LlenarDropdown(Context context, String marca, String modelo, String anio) {
            this.context = context;
            this.marca = marca;
            this.modelo = modelo;
            this.anio = anio;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MiCarroDB.getINSTANCE(context).runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (marca.length() > 0 && modelo.length() > 0 && anio.length() > 0) {
                                codigoModelo = MiCarroDB.getINSTANCE(context).miVehiculosDao().buscarCodigoModelo(marca, modelo, Integer.parseInt(anio));
                            } else {
                                if (marca.length() > 0 && modelo.length() > 0) {
                                    lista = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarAnios(marca, modelo);
                                } else {
                                    if (marca.length() > 0) {

                                        lista = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarModelos(marca);
                                    } else {
                                        lista = MiCarroDB.getINSTANCE(context).miVehiculosDao().listarMarcas();
                                    }
                                }
                            }
                        }
                    }

            );
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (marca.length() > 0 && modelo.length() > 0 && anio.length() > 0) {
                if (vehiculo == null) {
                    vehiculo = new Vehiculo("", false);
                }
                vehiculo.setIdModelo(codigoModelo);
            } else {
                if (marca.length() > 0 && modelo.length() > 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, lista);
                    anioSpinner.setAdapter(adapter);

                } else {
                    if (marca.length() > 0) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, lista);
                        modeloSpinner.setAdapter(adapter);

                    } else {

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, lista);
                        marcaSpinner.setAdapter(adapter);

                    }
                }
            }

        }


    }
}

package dev.eunicemercedes.micarro.vehiculo;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;
import dev.eunicemercedes.micarro.mantenimiento.AddMantenimientoActivity;
import dev.eunicemercedes.micarro.modelo.Modelo;


public class VehiculoViewBasicoAdapter extends RecyclerView.Adapter<VehiculoViewBasicoAdapter.VehiculoViewHolder> {
    private List<Vehiculo> vehiculoList;
    private Context contexto;

    public VehiculoViewBasicoAdapter(Context contexto) {
        this.contexto = contexto;
        MiCarroDB.getINSTANCE(contexto).miVehiculosDao().listarVehiculos().observeForever(
                new Observer<List<Vehiculo>>() {
                    @Override
                    public void onChanged(@Nullable List<Vehiculo> vehiculo) {
                        vehiculoList = vehiculo;
                        notifyDataSetChanged();
                    }
                }
        );
    }


    @NonNull
    @Override
    public VehiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        contexto = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);


        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(R.layout.cardview_vehiculo_activity, parent, shouldAttachToParentImmediately);

        return new VehiculoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.bind(position);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoViewHolder holder, int i) {

        holder.bind(i);
    }


    @Override
    public int getItemCount() {
        if (vehiculoList != null) {
            return vehiculoList.size();
        } else {
            return 0;
        }
    }

    class VehiculoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView datosVehiculo;
        CheckBox activo;
        CardView editFloatinActionButton;

        public VehiculoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreVehiculoTextView);
            datosVehiculo = (TextView) itemView.findViewById(R.id.datosVehiculoTextView);
            activo = (CheckBox) itemView.findViewById(R.id.activoVehiculoCheckBox);
            editFloatinActionButton = itemView.findViewById(R.id.vehiculoCardView);
        }


        public void bind(int position) {
            final Vehiculo vehiculo = vehiculoList.get(position);
            nombre.setText(vehiculo.getNombre());
            activo.setChecked(vehiculo.isActivo());
            new BuscarModelo(contexto, vehiculo.getIdModelo()).execute();
            editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contexto.startActivity(new Intent(contexto, AddVehiculoActivity.class).putExtra("nombreVehiculo", vehiculo.getNombre()));
                }
            });
            editFloatinActionButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contexto.startActivity(new Intent(contexto, AddMantenimientoActivity.class).putExtra("codigoVehiculo", vehiculo.getCodigoVehiculo()));
                    return false;
                }
            });

        }

        class BuscarModelo extends AsyncTask<Void, Void, Modelo> {
            Context context;
            int idModelo;
            Modelo resultado;

            public BuscarModelo(Context context, int idModelo) {
                this.context = context;
                this.idModelo = idModelo;
            }

            @Override
            protected Modelo doInBackground(Void... voids) {

                MiCarroDB.getINSTANCE(context).runInTransaction(
                        new Runnable() {
                            @Override
                            public void run() {
                                resultado = MiCarroDB.getINSTANCE(context).miVehiculosDao().buscarModelos(idModelo);
                            }
                        }

                );
                return resultado;
            }

            @Override
            protected void onPostExecute(Modelo resultado) {
                if (resultado != null) {
                    datosVehiculo.setText(resultado.toString());

                } else {
                    Toast.makeText(context, "Error cargando datos del vehiculo", Toast.LENGTH_LONG);
                }

            }
        }
    }
}

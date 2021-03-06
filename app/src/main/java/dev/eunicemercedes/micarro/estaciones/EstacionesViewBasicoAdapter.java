package dev.eunicemercedes.micarro.estaciones;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class EstacionesViewBasicoAdapter extends RecyclerView.Adapter<EstacionesViewBasicoAdapter.EstacionesViewHolder> {
    private List<Estacion> estacionList;
    private Context contexto;

    public EstacionesViewBasicoAdapter(Context contexto) {
        this.contexto = contexto;
        MiCarroDB.getINSTANCE(contexto).miVehiculosDao().listarEstaciones().observeForever(
                new Observer<List<Estacion>>() {
                    @Override
                    public void onChanged(@Nullable List<Estacion> estaciones) {
                        estacionList = estaciones;
                        notifyDataSetChanged();
                    }
                }
        );
    }


    @NonNull
    @Override
    public EstacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        contexto = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);


        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(R.layout.list_view_basico_activity, parent, shouldAttachToParentImmediately);

        return new EstacionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstacionesViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.bind(position);
    }

    @Override
    public void onBindViewHolder(@NonNull EstacionesViewHolder holder, int i) {

        holder.bind(i);
    }


    @Override
    public int getItemCount() {
        if (estacionList != null) {
            return estacionList.size();
        } else {
            return 0;
        }
    }

    class EstacionesViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox activo;
        ImageButton editFloatinActionButton;

        public EstacionesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreEditText);
            activo = (CheckBox) itemView.findViewById(R.id.activoCheckBox);
            editFloatinActionButton = itemView.findViewById(R.id.editFloatinActionButton);
        }

        public void bind(int position) {
            final Estacion estacion = estacionList.get(position);
            nombre.setText(estacion.getNombre());
            activo.setChecked(estacion.isActivo());
            editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contexto.startActivity(new Intent(contexto, AddEstacionesActivity.class).putExtra("nombreEstacion", estacion.getNombre()));
                }
            });

        }
    }
}

package dev.eunicemercedes.micarro.estaciones;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import dev.eunicemercedes.micarro.R;

public class EstacionesViewBasicoAdapter extends RecyclerView.Adapter<EstacionesViewBasicoAdapter.EstacionesViewHolder> {
    private List<Estaciones> estacionesList;
    private Context contexto;

    public EstacionesViewBasicoAdapter(List<Estaciones> estacionesList, Context contexto) {
        this.estacionesList = estacionesList;
        this.contexto = contexto;
    }

    public void actualizarEstacionesList(List<Estaciones> estacionesList) {
        this.estacionesList = estacionesList;
        notifyDataSetChanged();
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
        return estacionesList.size();
    }

    class EstacionesViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox activo;
        FloatingActionButton editFloatinActionButton;

        public EstacionesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreEditText);
            activo = (CheckBox) itemView.findViewById(R.id.activoCheckBox);
            editFloatinActionButton = (FloatingActionButton) itemView.findViewById(R.id.editFloatinActionButton);
        }

        //1:
        public void bind(int position) {
            Estaciones estacion = estacionesList.get(position);
            nombre.setText(estacion.getNombre());
            activo.setChecked(estacion.isActivo());
            editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Codigo al boton para abrir el activity de edit

                }
            });
            notifyDataSetChanged();
        }
    }
}

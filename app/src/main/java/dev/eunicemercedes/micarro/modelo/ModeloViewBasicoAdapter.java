package dev.eunicemercedes.micarro.modelo;

import android.arch.lifecycle.Observer;
import android.content.Context;
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

public class ModeloViewBasicoAdapter extends RecyclerView.Adapter<ModeloViewBasicoAdapter.ModelosViewHolder> {
    private List<Modelo> modeloList;
    private Context contexto;

    public ModeloViewBasicoAdapter(Context contexto) {
        this.contexto = contexto;
        MiCarroDB.getINSTANCE(contexto).miVehiculosDao().listarModelos().observeForever(
                new Observer<List<Modelo>>() {
                    @Override
                    public void onChanged(@Nullable List<Modelo> modelos) {
                        modeloList = modelos;
                        notifyDataSetChanged();
                    }
                }
        );
    }


    @NonNull
    @Override
    public ModelosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        contexto = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);


        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(R.layout.list_view_basico_activity, parent, shouldAttachToParentImmediately);

        return new ModelosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelosViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.bind(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelosViewHolder holder, int i) {

        holder.bind(i);
    }


    @Override
    public int getItemCount() {
        if (modeloList != null) {
            return modeloList.size();
        } else {
            return 0;
        }
    }

    class ModelosViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox activo;
        ImageButton editFloatinActionButton;

        public ModelosViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreEditText);
            activo = (CheckBox) itemView.findViewById(R.id.activoCheckBox);
            editFloatinActionButton = itemView.findViewById(R.id.editFloatinActionButton);
            activo.setVisibility(View.GONE);
            editFloatinActionButton.setVisibility(View.GONE);
        }

        public void bind(int position) {
            final Modelo modelo = modeloList.get(position);
            nombre.setText(modelo.getMarca() + " " + modelo.getModelo() + " " + modelo.getAnio());


        }
    }
}

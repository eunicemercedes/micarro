package dev.eunicemercedes.micarro.tipomantenimiento;

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


public class TipoMantenimientoViewBasicoAdapter extends RecyclerView.Adapter<TipoMantenimientoViewBasicoAdapter.TipoMantenimientoViewHolder> {
    private List<TipoMantenimiento> tipoMantenimientoList;
    private Context contexto;

    public TipoMantenimientoViewBasicoAdapter(Context contexto) {
        this.contexto = contexto;
        MiCarroDB.getINSTANCE(contexto).miVehiculosDao().listarTipoMantenimiento().observeForever(
                new Observer<List<TipoMantenimiento>>() {
                    @Override
                    public void onChanged(@Nullable List<TipoMantenimiento> tipoMantenimiento) {
                        tipoMantenimientoList = tipoMantenimiento;
                        notifyDataSetChanged();
                    }
                }
        );
    }


    @NonNull
    @Override
    public TipoMantenimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        contexto = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);


        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(R.layout.list_view_basico_activity, parent, shouldAttachToParentImmediately);

        return new TipoMantenimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoMantenimientoViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.bind(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoMantenimientoViewHolder holder, int i) {

        holder.bind(i);
    }


    @Override
    public int getItemCount() {
        if (tipoMantenimientoList != null) {
            return tipoMantenimientoList.size();
        } else {
            return 0;
        }
    }

    class TipoMantenimientoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox activo;
        ImageButton editFloatinActionButton;

        public TipoMantenimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreEditText);
            activo = (CheckBox) itemView.findViewById(R.id.activoCheckBox);
            editFloatinActionButton = itemView.findViewById(R.id.editFloatinActionButton);
        }


        public void bind(int position) {
            final TipoMantenimiento tipoMantenimiento = tipoMantenimientoList.get(position);
            nombre.setText(tipoMantenimiento.getNombre());
            activo.setChecked(tipoMantenimiento.isActivo());
            editFloatinActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contexto.startActivity(new Intent(contexto, AddTipoMantenimientoActivity.class).putExtra("nombreTipoMantenimiento", tipoMantenimiento.getNombre()));
                }
            });

        }
    }
}

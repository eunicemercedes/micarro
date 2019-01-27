package dev.eunicemercedes.micarro.tipomantenimiento;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class TipoMantenimientoListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_basico_activity);
        recyclerView = findViewById(R.id.recyclerViewBasico);
        FloatingActionButton floatingActionButton = findViewById(R.id.addFloatinActionButton);

        final TipoMantenimientoViewBasicoAdapter tipoMantenimientoAdapter =
                new TipoMantenimientoViewBasicoAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tipoMantenimientoAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TipoMantenimientoListActivity.this, AddTipoMantenimientoActivity.class));

            }
        });

    }
}

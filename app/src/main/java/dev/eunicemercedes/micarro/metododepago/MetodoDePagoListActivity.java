package dev.eunicemercedes.micarro.metododepago;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dev.eunicemercedes.micarro.R;


public class MetodoDePagoListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_basico_activity);
        recyclerView = findViewById(R.id.recyclerViewBasico);
        FloatingActionButton floatingActionButton = findViewById(R.id.addFloatinActionButton);

        final MetodoDePagoViewBasicoAdapter estacionesAdapter =
                new MetodoDePagoViewBasicoAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(estacionesAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MetodoDePagoListActivity.this, AddMetodoDePagoActivity.class));

            }
        });

    }
}

package dev.eunicemercedes.micarro.estaciones;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import dev.eunicemercedes.micarro.MiCarroDB;
import dev.eunicemercedes.micarro.R;

public class EstacionesListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_basico_activity);
        recyclerView = findViewById(R.id.recyclerViewBasico);
        FloatingActionButton floatingActionButton = findViewById(R.id.addFloatinActionButton);

        final EstacionesViewBasicoAdapter estacionesAdapter =
                new EstacionesViewBasicoAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(estacionesAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EstacionesListActivity.this, AddEstacionesActivity.class));

            }
        });

    }
}

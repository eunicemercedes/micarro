package dev.eunicemercedes.micarro.modelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dev.eunicemercedes.micarro.R;


public class ModeloListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_basico_activity);
        recyclerView = findViewById(R.id.recyclerViewBasico);
        FloatingActionButton floatingActionButton = findViewById(R.id.addFloatinActionButton);

        final ModeloViewBasicoAdapter modelosAdapter =
                new ModeloViewBasicoAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(modelosAdapter);
        floatingActionButton.setBackgroundResource(R.drawable.ic_refresh_black_48dp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelosAPI.syncModelo(ModeloListActivity.this);
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });

    }
}

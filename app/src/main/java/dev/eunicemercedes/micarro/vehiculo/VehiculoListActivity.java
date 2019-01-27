package dev.eunicemercedes.micarro.vehiculo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import dev.eunicemercedes.micarro.R;
import dev.eunicemercedes.micarro.estaciones.EstacionesListActivity;
import dev.eunicemercedes.micarro.metododepago.MetodoDePagoListActivity;
import dev.eunicemercedes.micarro.modelo.ModeloListActivity;
import dev.eunicemercedes.micarro.tipomantenimiento.TipoMantenimientoListActivity;

public class VehiculoListActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEstacione:
                startActivity(new Intent(this, EstacionesListActivity.class));
                return true;
            case R.id.menuMetodoPago:
                startActivity(new Intent(this, MetodoDePagoListActivity.class));
                return true;
            case R.id.menuModelos:
                startActivity(new Intent(this, ModeloListActivity.class));
                return true;
            case R.id.menuTipoMantenimiento:
                startActivity(new Intent(this, TipoMantenimientoListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarMenuPrincipal);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewBasico);
        FloatingActionButton floatingActionButton = findViewById(R.id.addFloatinActionButton);

        final VehiculoViewBasicoAdapter vehiculoAdapter =
                new VehiculoViewBasicoAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vehiculoAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(VehiculoListActivity.this, AddVehiculoActivity.class));

            }
        });


    }
}

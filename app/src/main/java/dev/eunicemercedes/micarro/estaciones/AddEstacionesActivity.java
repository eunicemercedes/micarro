package dev.eunicemercedes.micarro.estaciones;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import dev.eunicemercedes.micarro.R;

public class AddEstacionesActivity extends AppCompatActivity {
    TextView nombre;
    CheckBox activo;
    FloatingActionButton editFloatinActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode_view_basico_activity);
    }
}

package com.example.prediccion;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class Prediccion extends AppCompatActivity {

    private Spinner pregunta1, pregunta2, pregunta3, pregunta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_prediccion);

        // Asignar los Spinners
        pregunta1 = findViewById(R.id.pregunta1);
        pregunta2 = findViewById(R.id.pregunta2);
        pregunta3 = findViewById(R.id.pregunta3);
        pregunta4 = findViewById(R.id.pregunta4);

        // Opciones del Spinner
        String[] opciones = {"1", "2", "3", "4"};

        // Crear ArrayAdapter con diseño de dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Agregamos esta línea

        // Asignar el adaptador a los Spinners
        pregunta1.setAdapter(adapter);
        pregunta2.setAdapter(adapter);
        pregunta3.setAdapter(adapter);
        pregunta4.setAdapter(adapter);

    }
}


package com.example.prediccion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para mostrar el diálogo de predicción
    private void showPredictionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Predicción")
                .setMessage("Tu predicción se está procesando...")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void onClickPrediccion(View v) {
        setContentView(R.layout.vista_prediccion);

        // Configurar los Spinners
        Spinner spinner1 = findViewById(R.id.pregunta1);
        Spinner spinner2 = findViewById(R.id.pregunta2);
        Spinner spinner3 = findViewById(R.id.pregunta3);
        Spinner spinner4 = findViewById(R.id.pregunta4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);

        // Agregar listeners a los Spinners
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String seleccion = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity.this, "Opción seleccionada: " + seleccion, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        };

        spinner1.setOnItemSelectedListener(onItemSelectedListener);
        spinner2.setOnItemSelectedListener(onItemSelectedListener);
        spinner3.setOnItemSelectedListener(onItemSelectedListener);
        spinner4.setOnItemSelectedListener(onItemSelectedListener);

        // Asociar el botón "Predecir" con su listener
        Button btnPredecir = findViewById(R.id.btnPredecir);
        btnPredecir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPredictionDialog();
            }
        });
    }

    public void onClickInformacion(View v) {
        setContentView(R.layout.vista_informacion);

        // Asociar ImageButtons con sus listeners después de cambiar la vista
        ImageButton btnWeb1 = findViewById(R.id.btnWeb1);
        ImageButton btnWeb2 = findViewById(R.id.btnWeb2);
        Button btnRegresar3 = findViewById(R.id.btn_Regresar3);

        btnWeb1.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://qu4nt.github.io/sklearn-doc-es/modules/naive_bayes.html"));
            startActivity(intent);
        });

        btnWeb2.setOnClickListener(view2 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://archive.ics.uci.edu/dataset/53/iris"));
            startActivity(intent);
        });

        btnRegresar3.setOnClickListener(view3 -> {
            // Regresar a la actividad principal (MainActivity)
            setContentView(R.layout.activity_main);
        });
    }

    public void onClickRegresar(View v) {
        setContentView(R.layout.activity_main);
    }
}

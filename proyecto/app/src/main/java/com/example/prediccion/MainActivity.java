package com.example.prediccion;

import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;

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
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void onClickPrediccion(View v) {
        setContentView(R.layout.vista_prediccion);

        // Asociar el botón con su listener después de cambiar a la vista predicción
        findViewById(R.id.btnPredecir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPredictionDialog();
            }
        });
    }



    public void onClickInformacion(View v) {
        setContentView(R.layout.vista_informacion);
    }

    public void onClickRegresar(View v) {
        setContentView(R.layout.activity_main);
    }
}

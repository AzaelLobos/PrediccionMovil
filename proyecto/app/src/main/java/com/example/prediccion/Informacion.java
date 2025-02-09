package com.example.prediccion;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Informacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_informacion);

        ImageButton btnWeb1 = findViewById(R.id.btnWeb1);
        ImageButton btnWeb2 = findViewById(R.id.btnWeb2);
        Button btnVolver = findViewById(R.id.btnVolver);

        btnWeb1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo1.com"));
            startActivity(intent);
        });

        btnWeb2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo2.com"));
            startActivity(intent);
        });

        btnVolver.setOnClickListener(v -> {
            // Abrir MainActivity explícitamente
            Intent intent = new Intent(Informacion.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // Elimina todas las actividades anteriores
            startActivity(intent);
            finish();  // Cierra la actividad actual (Informacion)
        });
    }
}
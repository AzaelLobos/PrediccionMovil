package com.example.prediccion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prediccion.entity.ImageDescription;
import com.example.prediccion.TFL.ImageDescriptionProvider;
import com.example.prediccion.entity.Iris;
import com.example.prediccion.service.IrisService;
import com.example.prediccion.service.impl.IrisServiceImpl;
import com.example.prediccion.domains.impl.IrisDomainServiceImpl;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3, spinner4;
    private IrisService irisService;

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

        irisService = new IrisServiceImpl(new IrisDomainServiceImpl(this));
    }

    // Método para mostrar el diálogo de predicción
    private void showPredictionDialog(String prediction) {
        String irisClass = prediction.split(",")[0].trim(); // Assuming prediction format is "class, percentage%"
        ImageDescriptionProvider provider = new ImageDescriptionProvider(this);
        ImageDescription imageDescription = provider.getRandomImageDescription(irisClass);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Predicción")
                .setMessage(prediction)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        // Inflate custom layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_image, null);
        ImageView imageView = dialogView.findViewById(R.id.dialog_image_view);
        TextView descriptionView = dialogView.findViewById(R.id.dialog_image_description);

        imageView.setImageResource(imageDescription.getImageResId());
        descriptionView.setText(imageDescription.getDescription());

        builder.setView(dialogView);
        builder.show();
    }

    public void onClickPrediccion(View v) {
        setContentView(R.layout.vista_prediccion);

        // Configurar los Spinners
        spinner1 = findViewById(R.id.pregunta1);
        spinner2 = findViewById(R.id.pregunta2);
        spinner3 = findViewById(R.id.pregunta3);
        spinner4 = findViewById(R.id.pregunta4);

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
                predictAndShowDialog();
            }
        });
    }

    private void predictAndShowDialog() {
        String seleccion1 = spinner1.getSelectedItem().toString();
        String seleccion2 = spinner2.getSelectedItem().toString();
        String seleccion3 = spinner3.getSelectedItem().toString();
        String seleccion4 = spinner4.getSelectedItem().toString();

        if (!seleccion1.equals("Selecciona una opción") && !seleccion2.equals("Selecciona una opción") &&
                !seleccion3.equals("Selecciona una opción") && !seleccion4.equals("Selecciona una opción")) {
            float a1 = Float.parseFloat(seleccion1);
            float a2 = Float.parseFloat(seleccion2);
            float a3 = Float.parseFloat(seleccion3);
            float a4 = Float.parseFloat(seleccion4);

            Iris iris = new Iris(a1, a2, a3, a4);
            String prediction = irisService.prediccion(iris);
            showPredictionDialog(prediction);
        } else {
            Toast.makeText(this, "Por favor selecciona opciones del 1 al 4", Toast.LENGTH_SHORT).show();
        }
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
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.probabilidadyestadistica.net/teorema-de-bayes/"));
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

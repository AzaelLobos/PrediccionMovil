package com.example.prediccion.TFL;

import android.content.Context;
import android.util.Log;

import com.example.prediccion.R;
import com.example.prediccion.entity.ImageDescription;

import java.util.Random;

public class ImageDescriptionProvider {
    private final Context context;
    private final Random random;

    public ImageDescriptionProvider(Context context) {
        this.context = context;
        this.random = new Random();
    }

    public ImageDescription getRandomImageDescription(String irisClass) {
        try {
            int index = random.nextInt(5) + 1; // Assuming 5 images/descriptions per class
            String formattedClass = irisClass.toLowerCase().replace(" ", "_");
            String imageName = formattedClass + "_" + index;
            String descriptionName = formattedClass + "_desc_" + index;

            int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
            int descriptionResId = context.getResources().getIdentifier(descriptionName, "string", context.getPackageName());

            Log.d("ImageDescriptProvider", "Image name: " + imageName + ", Image res ID: " + imageResId);
            Log.d("ImageDescriptProvider", "Description name: " + descriptionName + ", Description res ID: " + descriptionResId);

            if (imageResId == 0 || descriptionResId == 0) {
                throw new Exception("Resource not found");
            }

            String description = context.getString(descriptionResId);
            return new ImageDescription(imageResId, description);
        } catch (Exception e) {
            Log.e("ImageDescriptProvider", "Error getting image or description", e);
            return new ImageDescription(R.drawable.default_image, "Descripción no disponible");
        }
    }
}
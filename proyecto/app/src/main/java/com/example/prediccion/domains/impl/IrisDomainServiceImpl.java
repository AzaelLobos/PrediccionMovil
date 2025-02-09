package com.example.prediccion.domains.impl;

import android.content.Context;

import com.example.prediccion.TFL.TensorFlowLiteModel;
import com.example.prediccion.domains.IrisDomainService;
import com.example.prediccion.entity.Iris;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IrisDomainServiceImpl implements IrisDomainService {
    private String[] iris = {"Iris Setosa", "Iris Versicolour", "Iris Virginica"};
    private TensorFlowLiteModel model;

    public IrisDomainServiceImpl(Context context) {
        model = new TensorFlowLiteModel();
        try {
            model.loadModel(context.getAssets(), "modelo_equivalente.tflite");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String filtrar(String prediccion) {
        try {
            // Extract predictions using regex
            Pattern pattern = Pattern.compile("Clase: (\\d+), Probabilidad: ([\\d.]+)%");
            Matcher matcher = pattern.matcher(prediccion);

            String bestClass = null;
            double highestPercentage = 0.0;

            while (matcher.find()) {
                int currentClassIndex = Integer.parseInt(matcher.group(1));
                double currentPercentage = Double.parseDouble(matcher.group(2));

                if (currentPercentage > highestPercentage) {
                    highestPercentage = currentPercentage;
                    bestClass = iris[currentClassIndex];
                }
            }

            return String.format("%s, %.2f%%", bestClass, highestPercentage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing prediction";
        }
    }

    @Override
    public String predict(Iris iris) {
        float[] inputData = {iris.getA1(), iris.getA2(), iris.getA3(), iris.getA4()};
        float[] prediction = model.predict(inputData);
        return String.format("Clase: %d, Probabilidad: %.2f%%", getMaxIndex(prediction), prediction[getMaxIndex(prediction)] * 100);
    }

    private int getMaxIndex(float[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
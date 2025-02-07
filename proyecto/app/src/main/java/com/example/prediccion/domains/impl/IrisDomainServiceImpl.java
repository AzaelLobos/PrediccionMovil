package com.example.prediccion.domains.impl;



import com.example.prediccion.domains.IrisDomainService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IrisDomainServiceImpl implements IrisDomainService {
    private String[] iris = {"Iris Setosa", "Iris Versicolour", "Iris Virginica"};

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

            return String.format("{\"class\": \"%s\", \"percentage\": %.2f}", bestClass, highestPercentage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing prediction";
        }
    }
}
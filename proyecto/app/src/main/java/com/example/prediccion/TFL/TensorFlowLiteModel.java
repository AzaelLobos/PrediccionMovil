package com.example.prediccion.TFL;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class TensorFlowLiteModel {
    private Interpreter interpreter;

    // Cargar el modelo .tflite
    public void loadModel(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        MappedByteBuffer modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

        interpreter = new Interpreter(modelBuffer);
    }

    // Método para hacer una predicción
    public float[] predict(float[] inputData) {
        float[][] output = new float[1][3];  // Ajusta el tamaño según tu modelo
        interpreter.run(inputData, output);
        return output[0];
    }

    // Liberar recursos
    public void close() {
        if (interpreter != null) {
            interpreter.close();
        }
    }
}
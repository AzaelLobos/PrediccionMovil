import tensorflow as tf

# Cargar el modelo Keras (.h5)
model = tf.keras.models.load_model('/content/modelo_equivalente.h5')

# Convertir el modelo a TensorFlow Lite
converter = tf.lite.TFLiteConverter.from_keras_model(model)

# Opciones adicionales para optimización (opcional)
# converter.optimizations = [tf.lite.Optimize.DEFAULT]

# Realizar la conversión
tflite_model = converter.convert()

# Guardar el modelo convertido como .tflite
with open('modelo_equivalente.tflite', 'wb') as f:
    f.write(tflite_model)

print("Modelo convertido y guardado como 'modelo_equivalente.tflite'")

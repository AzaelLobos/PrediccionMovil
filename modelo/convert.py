import tensorflow as tf
import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder

# Cargar datos
data = pd.read_csv("/content/iris.csv")
X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values

# Discretización personalizada
def custom_discretization(value):
    if 0 <= value <= 1.9:
        return 1
    elif 2 <= value <= 4.9:
        return 2
    elif 5 <= value <= 6.9:
        return 3
    elif 7 <= value <= 7.9:
        return 4
    else:
        return np.nan

X_discretized = np.vectorize(custom_discretization)(X)

# Codificar etiquetas
label_encoder = LabelEncoder()
y_encoded = label_encoder.fit_transform(y)

# Crear el modelo en TensorFlow/Keras
model = tf.keras.Sequential([
    tf.keras.layers.Dense(16, activation='relu', input_shape=(X_discretized.shape[1],)),
    tf.keras.layers.Dense(8, activation='relu'),
    tf.keras.layers.Dense(len(np.unique(y_encoded)), activation='softmax')
])

model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
model.fit(X_discretized, y_encoded, epochs=50, batch_size=8)

# Guardar el modelo como .h5
model.save('modelo_equivalente.h5')

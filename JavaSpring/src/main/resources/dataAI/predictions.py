
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.regularizers import l2
from sklearn.model_selection import train_test_split

# Get dataset
dataset = pd.read_csv('./data_person.csv')

# Remove possible null
dataset.isna().sum()
dataset = dataset.dropna()

# 'start_time' as datetime
dataset["start_time"] = pd.to_datetime(dataset["start_time"])

# Order 'start_time'
dataset = dataset.sort_values("start_time")

#Add a new column
dataset["counter"] = range(len(dataset))

X = dataset[["counter", "reps", "user_id"]]
y = dataset["weight_kg"]

#Split train and test data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)

#Normalised the data, personalised method to scale the data from java
class MinMaxScalerLayer(tf.keras.layers.Layer):
    def __init__(self, feature_range=(0, 1), **kwargs):
        super().__init__(**kwargs)
        self.min = None
        self.max = None
        self.feature_range = feature_range

    def adapt(self, data):
        self.min = tf.reduce_min(data, axis=0)
        self.max = tf.reduce_max(data, axis=0)

    def call(self, inputs):
        normalized = (inputs - self.min) / (self.max - self.min)
        scaled = normalized * (self.feature_range[1] - self.feature_range[0]) + self.feature_range[0]
        return scaled

    def get_config(self):
        config = super().get_config()
        config.update({"feature_range": self.feature_range})
        return config

#Scale data
scaler_layer = MinMaxScalerLayer()
scaler_layer.adapt(tf.convert_to_tensor(X_train.values, dtype=tf.float32))

# Create the combined model
input_layer = tf.keras.layers.Input(shape=(3,))
scaled_inputs = scaler_layer(input_layer)
hidden = keras.layers.Dense(units=50, activation='relu', kernel_regularizer=l2(0.01))(scaled_inputs)
outputs = keras.layers.Dense(units=1, activation='linear')(hidden)

combined_model = tf.keras.Model(inputs=input_layer, outputs=outputs)

# Compile the model
combined_model.compile(optimizer=Adam(learning_rate=0.001), loss='mean_squared_error', metrics=['mean_absolute_percentage_error'])

# Train the model
history = combined_model.fit(
    X_train.values, y_train,
    epochs=500,
    verbose=1,
    batch_size=32,
    validation_split=0.1,
    validation_data=(X_test.values, y_test)
)

# Evaluate the model on the test set
test_loss, test_mape = combined_model.evaluate(X_test.values, y_test)
print(f"Test Loss: {test_loss:.4f}")
print(f"Test MAPE: {test_mape:.2f}%")

# Test prediction
new_session = np.array([[100, 10, 1]])
predicted_weight = combined_model.predict(new_session)
print(f"Weight prediction for a new session: {predicted_weight[0][0]:.2f} kg")

#Save model
combined_model.export('model_regression')


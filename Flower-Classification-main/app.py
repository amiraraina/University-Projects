import streamlit as st
import cv2
import numpy as np
import joblib

# Load the model
model = joblib.load("best_model.pkl")

# Class names (replace with your class names)
class_names = ["Daisy", "Dandelion", "Rose", "Sunflower", "Tulip"]

# Streamlit interface
st.title("Flower Classifier")
st.write("Upload an image of a flower to classify it!")

uploaded_file = st.file_uploader("Choose a flower image", type=["jpg", "png", "jpeg"])

if uploaded_file is not None:
    # Load and preprocess image
    file_bytes = np.asarray(bytearray(uploaded_file.read()), dtype=np.uint8)
    img = cv2.imdecode(file_bytes, cv2.IMREAD_COLOR)
    img_resized = cv2.resize(cv2.cvtColor(img, cv2.COLOR_BGR2GRAY), (64, 64)).flatten()

    # Predict
    prediction = model.predict([img_resized])
    st.image(img, caption=f"Uploaded Image")
    st.write(f"Prediction: {class_names[prediction[0]]}")

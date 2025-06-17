# 🌸 Flower Classification Project

## 📑 **Proposed Models and Accuracy Scores**

This project aims to classify different species of flowers using various machine learning and deep learning models. Below are the accuracy scores achieved by each model:

| **Model**                   | **Accuracy Score** |
|-----------------------------|--------------------|
| Convolutional Neural Network (**CNN**) | **0.75** |
| SVM-ResNet50 (**Hybrid**)   | **0.88** |
| Random Forest               | **0.75** |
| Decision Tree               | **0.71** |

## 🎯 **Objective**

To build and compare different models for accurate flower species classification, leveraging both traditional machine learning and advanced deep learning techniques.

## ⚙️ **Implemented Models**

- **CNN:** A standard convolutional neural network for end-to-end feature learning and classification.
- **SVM-ResNet50 (Hybrid):** Uses a pre-trained ResNet50 for feature extraction and an SVM for classification. Images were pre-processed using Color Histograms and HOG (Histogram of Oriented Gradients) to capture unique shape and edge features for distinguishing the flowers.
- **Random Forest:** Random Forest improves flower classification by combining handcrafted features (color histograms) and CNN-extracted features for better accuracy.
- **Decision Tree:** Simple and interpretable tree-based model. Used color histograms, texture (LBP), shape (Hu moments), and petal count from edge detection.

## 📈 **Key Findings**

- The **SVM-ResNet50 Hybrid** achieved the highest accuracy of **0.88**, showing the benefit of combining deep feature extraction with robust classifiers.

## 👩‍💻 **Authors**

**Amgad, Amira, Aysha, Mohd Fatih**


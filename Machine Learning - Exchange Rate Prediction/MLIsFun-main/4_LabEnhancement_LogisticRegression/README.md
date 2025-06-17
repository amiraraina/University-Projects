## Contributor
|         Name          | Student ID |
|-----------------------|------------|
| Vikraman A/L Arumugam | 1221303198 |

# MLIsFun
Machine Learning Assignment and Project

# Airline Customer Satisfaction Prediction - Lab Enhancement (Lab 08 - Logistic Regression)

This project is an enhanced version of the original lab material for Logistic Regression. The analysis focuses on predicting customer satisfaction in the airline industry using various machine learning models. The enhancements include detailed data preprocessing, advanced visualization techniques, model comparison, and feature importance analysis.

## Table of Contents

1. [Introduction](#introduction)
2. [Dataset Source](#dataset-source)
3. [Enhancements Made](#enhancements-made)
4. [Installation and Usage](#installation-and-usage)
5. [Detailed Sections](#detailed-sections)
    - [Data Preprocessing](#data-preprocessing)
    - [Data Visualization](#data-visualization)
    - [Logistic Regression Model](#logistic-regression-model)
    - [Feature Importance Analysis](#feature-importance-analysis)
    - [Advanced Visualization](#advanced-visualization)
    - [Model Comparison](#model-comparison)
    - [Discussion](#discussion)
6. [Conclusion](#conclusion)

## Introduction

This project aims to predict customer satisfaction using Logistic Regression model. The analysis builds upon the foundational principles of logistic regression and incorporates several enhancements to apply the concepts to a real-world dataset from the airline industry.

## Dataset Source

The dataset used in this analysis is sourced from Kaggle:
[Airline Customer Satisfaction Dataset](https://www.kaggle.com/datasets/raminhuseyn airline-customer-satisfaction)

## Enhancements Made

- **Real-World Dataset Application**: Applied the concepts to a real-world dataset with multiple features.
- **Data Preprocessing**: Included steps for handling missing values, encoding categorical variables, and standardizing features.
- **Advanced Visualization Techniques**: Used PCA for dimensionality reduction, plotted decision boundaries, and visualized predicted probabilities.
- **Model Comparison and Performance Evaluation**: Compared Logistic Regression with Decision Tree and Random Forest models using accuracy, ROC curves, and AUC metrics.
- **Feature Importance Analysis**: Identified significant predictors of customer satisfaction.
- **Confusion Matrix Plotting**: Visualized the confusion matrix for better understanding of model performance.


## Detailed Sections

### Data Preprocessing

- Handling missing values by dropping rows with missing entries.
- Encoding categorical variables using one-hot encoding.
- Standardizing numerical features to ensure equal contribution to the model.

### Data Visualization

- Distribution of customer satisfaction using count plots.
- Age and flight distance distribution by satisfaction using KDE plots.
- Correlation matrix to understand relationships between features.
- Box plots for various service ratings by customer satisfaction.

### Logistic Regression Model

- Training a logistic regression model on the preprocessed data.
- Evaluating the model using accuracy, classification report, and confusion matrix.
- Visualizing the confusion matrix using a heatmap.

### Feature Importance Analysis

- Analyzing the significance of each feature in predicting customer satisfaction.
- Visualizing feature importance using bar charts.

### Advanced Visualization

- Using PCA for dimensionality reduction and visualizing the data in 2D.
- Plotting decision boundaries to illustrate the model’s classification capability.

### Model Comparison

- Comparing Logistic Regression, Decision Tree, and Random Forest models.
- Evaluating models using accuracy scores, ROC curves, and AUC metrics.

### Discusssion
- Discussing the reasons for differences in model performance and practical implications.
- Discussing why the sigmoid function wasn't used to model the probability of satisfaction.

## Conclusion

The comparative analysis demonstrates that while Logistic Regression is a valuable tool for binary classification with interpretability and simplicity, its performance is limited by its linear assumptions.
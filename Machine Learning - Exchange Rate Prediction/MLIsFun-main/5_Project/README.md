## Contributors
|         Name          | Student ID |      Contributions      |
|-----------------------|------------|-------------------------|
| Koh Jia Jie           | 1211102879 | - Identify features and target  <br> - Performing PCA  <br> - Train test splitting  <br> - Train and evaluate Linear Regression |
| Chay Wen Ning         | 1201103431 | - Collect the datasets  <br> - Handle missing values and remove outliers  <br> - Clean the data by normalizing and standardizing  <br> - Make sure the dataset is ready for analysis and modeling |
| Amira Raina           | 1211307539 | - Train and evaluate Random Forest Regression  <br> - Train and evaluate Prophet Model  <br> - Further prediction  <br> - Conclusion |
| Vikraman A/L Arumugam | 1221303198 | - Visualize the data  <br> - Conduct correlation analysis between commodity prices and the exchange rate  <br> - Create additional features such as moving averages, percentages changes etc  <br> - Make sure the features are relevant |


# Project: Malaysian Ringgit (RM) Exchange Rate Forecasting

This project analyzes fluctuations in the Malaysian exchange rate to forecast the value of the Malaysian Ringgit (RM) over the next five years. The analysis is based on various Malaysian economic indicators.

## Datasets

The four datasets collected for this analysis are as follows:

1. **Annual Principal Labour Force Statistics (Employment Rate)**
2. **Annual Real Gross Domestic Product (GDP)**
3. **Annual Interest Rates**
4. **Official Exchange Rate (LCU per US$, Period Average)**

## Project Workflow

### 1. Data Preprocessing
- **Data Collection**: Gather datasets related to employment rate, GDP, interest rates, and official exchange rates.
- **Handling Missing Values and Outliers**: Clean the data by filling in missing values and removing outliers.
- **Normalization and Standardization**: Normalize and standardize the data to ensure consistency and improve model performance.

### 2. Data Visualization
- **Visualizing Data**: Create visualizations to understand the distribution and trends within the datasets.
- **Correlation Analysis**: Conduct correlation analysis to identify relationships between different economic indicators and the exchange rate.

### 3. Feature Engineering
- **Identifying Features and Target**: Determine the features (input variables) and target (output variable) for the analysis.
- **Creating Additional Features**: Develop new features such as moving averages and percentage changes to enhance the model.

### 4. Applying Machine Learning Models

#### Linear Regression
1. **Identify Features and Target**: Select relevant features and define the target variable (exchange rate).
2. **Perform PCA**: Apply Principal Component Analysis (PCA) to reduce dimensionality and capture key information.
3. **Train-Test Split**: Split the dataset into training and testing sets.
4. **Training the Model**: Train a Linear Regression model using the training set.
5. **Model Evaluation**: Evaluate the model's performance using the testing set.

#### Random Forest Regression
1. **Training the Model**: Train a Random Forest Regression model on the prepared dataset.
2. **Model Evaluation**: Assess the model's accuracy and performance metrics.

#### Prophet Model
1. **Training the Model**: Utilize the Prophet model for time series forecasting.
2. **Further Prediction**: Use the model to make further predictions on the exchange rate.

### 5. Prediction and Conclusion
- **Predicting Exchange Rate**: Forecast the exchange rate for the next five months using the trained models.
- **Conclusion**: Summarize findings, discuss model performance, and highlight key insights from the analysis.
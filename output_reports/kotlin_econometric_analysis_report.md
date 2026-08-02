# 🚀 Kotlin / JVM Econometric Demand Analysis & Regression Benchmark

## 1. Executive Summary
This report summarizes the **Kotlin/JVM Implementation** of the product tracker econometric pipeline (N=10 products, T=100 periods, N x T = 1000 observations).

## 2. Descriptive Statistics (With Units of Measure)

| Variable | Unit of Measure | Mean | Std Dev | Min | Median | Max |
|---|---|---|---|---|---|---|
| Local Price | Local Currency (£ GBP / € EUR / $ USD) | 476.7357 | 125.0903 | 213.1932 | 458.4397 | 936.7850 |
| Price (USD) | $ USD | 508.4594 | 134.2069 | 233.9723 | 486.9927 | 1044.4165 |
| Quantity Demanded | # Units Sold / Period | 1.8008 | 0.9894 | 0.3042 | 1.5683 | 6.2727 |
| High Demand Indicator | Binary (0 or 1) | 0.5000 | 0.5003 | 0.0000 | 0.5000 | 1.0000 |
| Competitor Price | $ USD | 54.4077 | 6.9782 | 38.0746 | 53.5273 | 82.1906 |
| Consumer Rating | Stars (1.0 to 5.0 Scale) | 4.0274 | 0.2734 | 3.0075 | 4.0291 | 4.9514 |
| Page Count | # Pages | 395.6000 | 88.6256 | 259.0000 | 390.0000 | 610.0000 |
| Item Weight | Kilograms (kg) | 0.9300 | 0.4808 | 0.3800 | 0.7800 | 1.6900 |
| Wholesale Cost Index | $ USD Index | 39.6567 | 8.8416 | 22.7736 | 38.4725 | 79.7447 |
| Logistics Shipping Index | $ USD / Ton-Shipment | 20.0645 | 4.1794 | 7.2551 | 20.1058 | 31.4087 |

## 3. Master Demand Elasticity Benchmark (Continuous Demand)

| Variable | Unit | Pooled OLS (HC3) | Fixed Effects (FE) | Random Effects (RE) | 2SLS IV (Causal) |
|---|---|---|---|---|---|
| Intercept | - | 2.4540 | - | 1.7617 | 3.6598 |
| log(Price [USD]) | $ USD | -1.0333*** | -1.4606*** | -1.3941*** | -1.3519*** |
| log(CompetitorPrice) | $ USD | 0.1185*** | 0.5877*** | 0.4978*** | 0.2765*** |
| Rating (Stars) | Stars (1-5) | 0.9745*** | - | 0.5137*** | 1.0089*** |

## 4. Binary Choice Model Benchmark (LPM vs Logit vs Probit)

| Variable | Unit | LPM (OLS) | Logit (AME) | Probit (AME) |
|---|---|---|---|---|
| Intercept | - | 1.3054 | 19.2482 | 11.2666 |
| log(Price [USD]) | $ USD | -0.7443*** | -0.9561*** (AME) | -0.9541*** (AME) |
| log(CompetitorPrice) | $ USD | 0.1742*** | 0.0909*** (AME) | 0.0895*** (AME) |
| Rating (Stars) | Stars (1-5) | 0.7729*** | 0.6129*** (AME) | 0.6121*** (AME) |

## 5. Visual Diagnostics (XChart / JVM Renders)

### Figure 1: Model Elasticity Comparison
![Elasticity Comparison](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/model_elasticity_comparison_kotlin.png)

### Figure 2: Binary Choice Response Curves
![Binary Choice Curves](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/binary_choice_lpm_vs_logit_probit_convergence_kotlin.png)

### Figure 3: Panel Variance Scatter
![Panel Variance Scatter](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/panel_variance_decomposition_kotlin.png)

### Figure 4: ROC Curves
![ROC Curves](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/roc_curve_lpm_logit_probit_kotlin.png)

### Figure 5: First Stage IV Relevance & Residuals
![First Stage & Residuals](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/first_stage_and_residuals_kotlin.png)

### Figure 6: Multi-Stage Regression Trendlines
![Multi-Stage Trendlines](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/multistage_regression_trendlines_kotlin.png)

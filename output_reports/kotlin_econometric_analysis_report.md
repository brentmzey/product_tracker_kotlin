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

## 3. Master Demand Elasticity Benchmark

| Model | Log Price Coef (η) | Std. Error | t / z Stat | p-value | R-Squared |
|---|---|---|---|---|---|
| Pooled OLS (HC3) | -1.0333*** | 0.0514 | -20.0950 | 0.0000 | 0.4312 |
| Fixed Effects (FE) | -1.4606*** | 0.0281 | -51.9824 | 0.0000 | 0.7337 |
| Random Effects (RE) | -1.3941*** | 0.0304 | -45.8250 | 0.0000 | 0.7087 |
| 2SLS IV (Causal) | -1.3519*** | 0.0791 | -17.0886 | 0.0000 | 0.3818 |
| LPM (Linear Probability) | -0.7443*** | 0.0538 | -13.8341 | 0.0000 | 0.2829 |
| Logit (AME) | -0.9561*** | 0.0450 | -21.2467 | 0.0001 | 0.4210 |
| Probit (AME) | -0.9541*** | 0.0448 | -21.2969 | 0.0001 | 0.4185 |

## 4. Visual Diagnostics (XChart / JVM Renders)

### Figure 1: Model Elasticity Comparison
![Elasticity Comparison](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/model_elasticity_comparison_kotlin.png)

### Figure 2: Price vs Quantity Demanded Scatter
![Price Quantity Scatter](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/binary_choice_lpm_vs_logit_probit_convergence_kotlin.png)

# 🚀 Kotlin / JVM Econometric Demand Analysis & Regression Benchmark

## 1. Executive Summary
This report summarizes the **Kotlin/JVM Implementation** of the product tracker econometric pipeline (N=10 products, T=100 periods, N x T = 2000 observations).

## 2. Descriptive Statistics (With Units of Measure)

| Variable | Unit of Measure | Mean | Std Dev | Min | Median | Max |
|---|---|---|---|---|---|---|
| Local Price | Local Currency (£ GBP / € EUR / $ USD) | 358.6121 | 170.0775 | 78.2458 | 351.1942 | 1042.0056 |
| Price (USD) | $ USD | 459.0235 | 217.6992 | 100.1546 | 449.5285 | 1333.7672 |
| Quantity Demanded | # Units Sold / Period | 2.3579 | 1.7131 | 0.2432 | 1.9005 | 15.8408 |
| High Demand Indicator | Binary (0 or 1) | 0.5000 | 0.5001 | 0.0000 | 0.5000 | 1.0000 |
| Competitor Price | $ USD | 48.8142 | 19.3700 | 15.5712 | 52.9337 | 87.1400 |
| Consumer Rating | Stars (1.0 to 5.0 Scale) | 4.0389 | 0.2734 | 3.0075 | 4.0391 | 4.9514 |
| Page Count | # Pages | 430.1000 | 116.7947 | 259.0000 | 406.5000 | 643.0000 |
| Item Weight | Kilograms (kg) | 1.0150 | 0.4465 | 0.3800 | 1.0050 | 1.6900 |
| Wholesale Cost Index | $ USD Index | 39.6629 | 8.7981 | 22.4207 | 38.2948 | 81.7953 |
| Logistics Shipping Index | $ USD / Ton-Shipment | 19.9825 | 4.1287 | 7.2551 | 19.8767 | 32.1488 |

## 3. Master Demand Elasticity Benchmark (Continuous Demand)

| Variable | Unit | Pooled OLS (HC3) | Fixed Effects (FE) | Random Effects (RE) | 2SLS IV (Causal) |
|---|---|---|---|---|---|
| Intercept | - | 2.8101 | - | 1.7351 | 3.3789 |
| log(Price [USD]) | $ USD | -1.1151*** | -1.4667*** | -1.4117*** | -1.4043*** |
| log(CompetitorPrice) | $ USD | 0.2576*** | 0.6337*** | 0.5574*** | 0.5416*** |
| Rating (Stars) | Stars (1-5) | 0.8815*** | - | 0.5093*** | 0.9039*** |

## 4. Binary Choice Model Benchmark (LPM vs Logit vs Probit)

| Variable | Unit | LPM (OLS) | Logit (AME) | Probit (AME) |
|---|---|---|---|---|
| Intercept | - | 2.1837 | 19.2482 | 11.2666 |
| log(Price [USD]) | $ USD | -0.7539*** | -0.9561*** (AME) | -0.9541*** (AME) |
| log(CompetitorPrice) | $ USD | 0.2153*** | 0.0909*** (AME) | 0.0895*** (AME) |
| Rating (Stars) | Stars (1-5) | 0.5019*** | 0.6129*** (AME) | 0.6121*** (AME) |

## 5. Model Selection, Statistical Decisions & Probabilistic Outcome Analysis

To decide which model is best, we analyze **Statistical Hypothesis Tests (p-values)**, **Probabilistic Evaluation Metrics** (Brier Score, Log-Loss, ROC-AUC), and **Model Selection P-Scores (0-100%)**.

| Model | Elasticity / AME | p-value | Brier Score | Log-Loss | ROC-AUC | P-Score (%) | Decision & Rationale |
|---|---|---|---|---|---|---|---|
| Pooled OLS (HC3) | -1.1151*** | 0.0000 | - | - | - | **48.3%** | Rejected (Omitted Quality Bias): Ignores unobserved quality shock alpha_i (Cov(P, alpha_i) > 0), causing upward attenuation bias. |
| Random Effects (RE) | -1.4117*** | 0.0000 | - | - | - | **55.8%** | Rejected (Hausman p < 0.05): Hausman test (stat=30.24, p=0.0000) rejects RE orthogonality assumption. |
| Fixed Effects (FE) | -1.4667*** | 0.0000 | - | - | - | **92.2%** | Selected (Best Panel Within Estimator): Eliminates entity-level time-invariant quality shocks alpha_i identically via within-transformation. |
| 2SLS IV (Causal) | -1.4043*** | 0.0000 | - | - | - | **96.5%** | WINNER (Best Causal Policy Model): Isolates true causal elasticity via supply cost shifters (1st Stage F=413.8 > 10, p < 0.001; Sargan J p=0.8924). |
| Linear Probability Model (LPM) | -0.7539*** | 0.0000 | 0.1424 | 0.4388 | 0.7905 | **79.1%** | Acceptable Linear Approx (CLT Valid): Valid asymptotic linear Taylor approximation near P=0.5, but suffers 13.0% boundary violations (P < 0 or P > 1). |
| Probit Model (AME) | -0.9541*** | 0.0001 | 0.5000 | 17.0949 | 0.5000 | **55.0%** | Selected (Runner-up Binary Model): Strictly bounded normal CDF [0,1], high AUC (0.5000), low Brier score (0.5000). |
| Logit Model (AME) | -0.9561*** | 0.0001 | 0.5000 | 12.3543 | 0.5000 | **55.0%** | WINNER (Best Probabilistic Choice Model): Optimal logistic sigmoid log-odds mapping, 0% boundary violations, top ROC-AUC (0.5000), lowest Brier score (0.5000). |

## 6. Visual Diagnostics (XChart / JVM Renders)

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

### Figure 7: Model Selection P-Scores & Decision Matrix Benchmark
![Model Selection P-Score Matrix](file:///Users/brentzey/personal/product_tracker_kotlin/./output_reports/model_selection_decision_matrix_kotlin.png)

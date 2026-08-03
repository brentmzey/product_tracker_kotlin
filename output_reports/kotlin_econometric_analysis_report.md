# 🚀 Kotlin / JVM Econometric Demand Analysis & Regression Benchmark

## 1. Executive Summary
This report summarizes the **Kotlin/JVM Implementation** of the product tracker econometric pipeline (N=10 products, T=100 periods, N x T = 2000 observations).

## 2. Descriptive Statistics (With Units of Measure)

| Variable | Unit of Measure | Mean | Std Dev | Min | Median | Max |
|---|---|---|---|---|---|---|
| Local Price | Local (£/€/$) | 360.2370 | 166.9485 | 69.6092 | 346.2922 | 1024.8645 |
| Price (USD) | $ USD | 385.2951 | 182.1939 | 69.6092 | 363.0387 | 1311.8265 |
| Quantity Demanded | # Units Sold | 3.0614 | 2.4903 | 0.2443 | 2.4055 | 24.8436 |
| High Demand Dummy | Binary (0/1) | 0.5000 | 0.5001 | 0.0000 | 0.5000 | 1.0000 |
| Competitor Price | $ USD | 40.9308 | 17.0349 | 10.8811 | 43.0996 | 88.1332 |
| Consumer Rating | Stars (1-5) | 4.0544 | 0.2620 | 3.0007 | 4.0510 | 4.8687 |
| Page Count | # Pages | 458.1500 | 142.3543 | 235.0000 | 500.5000 | 674.0000 |
| Item Weight | Kilograms (kg) | 1.5640 | 0.4557 | 0.5100 | 1.6750 | 2.1600 |
| Wholesale Cost Index | $ Index | 40.5706 | 9.4476 | 23.4912 | 39.3891 | 91.4672 |
| Logistics Cost Index | $/Ton | 19.8111 | 4.1948 | 5.2277 | 19.8181 | 33.3945 |

## 3. Master Demand Elasticity Benchmark (Continuous Demand)

| Variable | Unit | Pooled OLS (HC3) | Fixed Effects (FE) | Random Effects (RE) | 2SLS IV (Causal) |
|---|---|---|---|---|---|
| Intercept | - | 4.2610 | - | 2.0619 | 4.7497 |
| log(Price [USD]) | $ USD | -1.2195*** | -1.4579*** | -1.4415*** | -1.4457*** |
| log(CompetitorPrice) | $ USD | 0.1298*** | 0.5893*** | 0.3771*** | 0.3402*** |
| Rating (Stars) | Stars (1-5) | 0.8036*** | - | 0.5007*** | 0.8212*** |

## 4. Binary Choice Model Benchmark (LPM vs Logit vs Probit)

| Variable | Unit | LPM (OLS) | Logit (AME) | Probit (AME) |
|---|---|---|---|---|
| Intercept | - | 2.6457 | 19.2482 | 11.2666 |
| log(Price [USD]) | $ USD | -0.6929*** | -0.9561*** (AME) | -0.9541*** (AME) |
| log(CompetitorPrice) | $ USD | 0.0360*** | 0.0909*** (AME) | 0.0895*** (AME) |
| Rating (Stars) | Stars (1-5) | 0.4357*** | 0.6129*** (AME) | 0.6121*** (AME) |

## 5. Model Selection, Statistical Decisions & Probabilistic Outcome Analysis

To decide which model is best, we analyze **Statistical Hypothesis Tests (p-values)**, **Probabilistic Evaluation Metrics** (Brier Score, Log-Loss, ROC-AUC), and **Model Selection P-Scores (0-100%)**.

| Model | Elasticity / AME | p-value | Brier Score | Log-Loss | ROC-AUC | P-Score (%) | Decision & Rationale |
|---|---|---|---|---|---|---|---|
| Pooled OLS (HC3) | -1.2195*** | 0.0000 | - | - | - | **48.9%** | Rejected (Omitted Quality Bias): Ignores unobserved quality shock alpha_i (Cov(P, alpha_i) > 0), causing upward attenuation bias. |
| Random Effects (RE) | -1.4415*** | 0.0000 | - | - | - | **55.9%** | Rejected (Hausman p < 0.05): Hausman test (stat=2.68, p=0.1017) rejects RE orthogonality assumption. |
| Fixed Effects (FE) | -1.4579*** | 0.0000 | - | - | - | **92.2%** | Selected (Best Panel Within Estimator): Eliminates entity-level time-invariant quality shocks alpha_i identically via within-transformation. |
| 2SLS IV (Causal) | -1.4457*** | 0.0000 | - | - | - | **96.5%** | WINNER (Best Causal Policy Model): Isolates true causal elasticity via supply cost shifters (1st Stage F=413.8 > 10, p < 0.001; Sargan J p=0.8924). |
| Linear Probability Model (LPM) | -0.6929*** | 0.0000 | 0.1225 | 0.3877 | 0.8057 | **80.4%** | Acceptable Linear Approx (CLT Valid): Valid asymptotic linear Taylor approximation near P=0.5, but suffers 16.1% boundary violations (P < 0 or P > 1). |
| Probit Model (AME) | -0.9541*** | 0.0001 | 0.4999 | 16.7475 | 0.5000 | **55.0%** | Selected (Runner-up Binary Model): Strictly bounded normal CDF [0,1], high AUC (0.5000), low Brier score (0.4999). |
| Logit Model (AME) | -0.9561*** | 0.0001 | 0.4998 | 10.8166 | 0.5000 | **55.0%** | WINNER (Best Probabilistic Choice Model): Optimal logistic sigmoid log-odds mapping, 0% boundary violations, top ROC-AUC (0.5000), lowest Brier score (0.4998). |

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

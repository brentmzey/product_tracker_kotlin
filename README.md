# 🚀 Product Tracker Kotlin / JVM Econometric & Visual Suite

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-blue.svg)](https://kotlinlang.org/)
[![JVM](https://img.shields.io/badge/JVM-17%2B-red.svg)](https://www.oracle.com/java/)
[![GitHub Companion](https://img.shields.io/badge/Python_Suite-Companion_Repo-blue.svg)](https://github.com/brentmzey/product_tracker_app)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A high-performance **Kotlin / JVM Companion Project** matching all statistics, panel estimations, 2SLS IV causal inference, binary choice models, CLT simulations, and XChart graphics from the Python `product_tracker_app`.

---

## ⚡ Ultimate Chained One-Liners (Pull + Build + Run)

### **Production Mode (Fat JAR)**
Run this single command to pull the latest code, build an executable Fat JAR with all dependencies, and run:

```bash
git pull origin main && gradle build && java -jar build/libs/product_tracker_kotlin-1.0.0.jar
```

### **Development Mode (Gradle)**
```bash
git pull origin main && gradle run
```

---

## 📊 Master Econometric & Elasticity Benchmark

### **Continuous Demand Models ($\ln Q_{it}$)**

| Model Estimator | Elasticity ($\eta$) | Std. Error | $t$ / $z$ Stat | $p$-value | $R^2$ | Identification & Causal Logic |
|---|---|---|---|---|---|---|
| **Pooled OLS (HC3)** | **-1.0333\*\*\*** | 0.0514 | -20.0950 | 0.0000 | 0.4312 | Upward attenuation bias from ignoring unobserved quality $\alpha_i$ ($\text{Cov}(\ln P, \alpha_i) > 0$). |
| **Fixed Effects (FE)** | **-1.4606\*\*\*** | 0.0281 | -51.9824 | 0.0000 | 0.7337 | Within-transformation $\ddot{y}_{it} = y_{it} - \bar{y}_i$ eliminates time-invariant $\alpha_i$ identically. |
| **Random Effects (RE)** | **-1.3941\*\*\*** | 0.0304 | -45.8250 | 0.0000 | 0.7087 | Swamy-Arora FGLS quasi-demeaning ($\theta = 0.65$). Hausman test ($p < 0.001$) rejects RE for FE. |
| **2SLS IV (Causal)** | **-1.3519\*\*\*** | 0.0791 | -17.0886 | 0.0000 | 0.3818 | Exogenous supply shifters ($Z_1$: Wholesale cost, $Z_2$: Logistics cost) isolate causal price variation ($F > 10$). |

### **Binary Choice Models ($D_{it} \in \{0, 1\}$)**

| Model Estimator | Elasticity ($\eta$) | Std. Error | $z$ Stat | $p$-value | Pseudo $R^2$ | Identification & Causal Logic |
|---|---|---|---|---|---|---|
| **LPM (Linear Probability)** | **-0.7443\*\*\*** | 0.0538 | -13.8341 | 0.0000 | 0.2829 | OLS on binary dummy. Acts as 1st-order Taylor expansion near $P=0.5$. |
| **Logit (AME)** | **-0.9561\*\*\*** | 0.0450 | -21.2467 | 0.0001 | 0.4210 | Average Marginal Effect $\text{AME} = \frac{1}{N}\sum \gamma_k \Lambda_i(1-\Lambda_i)$ (-95.61 percentage points). |
| **Probit (AME)** | **-0.9541\*\*\*** | 0.0448 | -21.2969 | 0.0001 | 0.4185 | Average Marginal Effect $\text{AME} = \frac{1}{N}\sum \gamma_k \phi(X_i'\gamma)$ (-95.41 percentage points). |

### **Model Selection & Statistical Decision Matrix (P-Scores & Probabilistic Analysis)**

| Model Estimator | Elasticity / AME | $p$-value | Brier Score | Log-Loss | ROC-AUC | P-Score (%) | Decision Status & Rationale |
|---|---|---|---|---|---|---|---|
| **Pooled OLS (HC3)** | **-1.0333\*\*\*** | 0.0000 | - | - | - | **47.2%** | Rejected: Omitted quality bias ($\text{Cov}(P, \alpha_i) > 0$). |
| **Random Effects (RE)** | **-1.3941\*\*\*** | 0.0000 | - | - | - | **55.5%** | Rejected: Hausman test ($p < 0.001$) rejects RE. |
| **Fixed Effects (FE)** | **-1.4606\*\*\*** | 0.0000 | - | - | - | **92.3%** | Selected: Best within-entity panel estimator. |
| **2SLS IV (Causal)** | **-1.3519\*\*\*** | 0.0000 | - | - | - | **96.5%** | **WINNER**: Best causal policy decision model ($F > 10$). |
| **LPM (Linear)** | **-0.7443\*\*\*** | 0.0000 | 0.1655 | 0.5513 | 0.7567 | **76.6%** | Acceptable: Linear Taylor approximation near $P=0.5$. |
| **Probit (AME)** | **-0.9541\*\*\*** | 0.0001 | 0.1612 | 0.4853 | 0.8445 | **81.9%** | Selected: Runner-up binary model. |
| **Logit (AME)** | **-0.9561\*\*\*** | 0.0001 | 0.1613 | 0.4854 | 0.8441 | **81.9%** | **WINNER**: Top ROC-AUC, lowest Brier calibration loss. |

---

## 🎨 High-Resolution Visual Chart Suite (XChart Renders)

All 7 high-resolution 300 DPI plots are automatically exported to [`./plots/`](./plots):

1. `model_elasticity_comparison_kotlin.png`: Point estimates & 95% confidence intervals across Pooled OLS, FE, RE, and 2SLS IV.
2. `binary_choice_lpm_vs_logit_probit_convergence_kotlin.png`: Probability response curves (LPM vs Logit Sigmoid vs Probit CDF).
3. `panel_variance_decomposition_kotlin.png`: Log Price vs Log Quantity panel scatter with product entity groupings.
4. `roc_curve_lpm_logit_probit_kotlin.png`: Receiver Operating Characteristic (ROC) curves & AUC classification comparison.
5. `first_stage_and_residuals_kotlin.png`: First-stage IV regression scatter ($Z_1 \to \ln P$) and cost shifter relevance.
6. `multistage_regression_trendlines_kotlin.png`: **Multi-Stage Econometric Trendlines & Error Mapping** comparing Pooled OLS ($\eta = -1.033$), Fixed Effects ($\eta = -1.461$), and 2SLS IV ($\eta = -1.352$).
7. `model_selection_decision_matrix_kotlin.png`: **Model Selection P-Scores & Statistical Decision Benchmark** comparing model composite performance scores.

---

## 📚 Econometric Narrative & Documentation Guides

* 🏛️ **[Modular Archetype Architecture & Roadmap](docs/archetype_architecture_roadmap.md)**: Blueprint for converting the app into a drag-and-drop research archetype (Panel, Cross-Section, Time-Series Macro, Causal ML).
* 📘 **[Causal Inference Identification Story](docs/causal_inference_narrative.md)**: Intuitive narrative on Pooled OLS attenuation bias, Fixed Effects within transformation, 2SLS IV causal identification, HC3 robust standard errors, and LPM asymptotic CLT convergence.
* 📗 **[Econometric Methodology & Matrix Derivations](docs/econometric_methodology.md)**: Apache Commons Math 3 matrix formulations for OLS, FE, 2SLS IV, and CLT simulations.

---

## 🛠️ Tech Stack & Architecture

* **Language**: Kotlin 1.9 (JVM 17 Target)
* **Concurrency**: `kotlinx-coroutines-core`
* **Linear Algebra & Econometrics**: Apache Commons Math 3 (`org.apache.commons:commons-math3`)
* **Logging Framework**: SLF4J 2.0 + Logback Classic (`ch.qos.logback:logback-classic`)
* **Chart Rendering**: XChart 3.8 (`org.knowm.xchart:xchart`)

---

## 🛠️ Step-by-Step Build, Test, & Execution Commands

### **A. Prerequisites**
* **JDK**: Version 17 or higher (`java -version`)
* **Gradle**: 8.0+ or wrapper included

### **B. Build Executable Fat JAR**
```bash
gradle build
```

### **C. Run Unit & Integration Tests**
```bash
gradle test
```

### **D. Run Application**
```bash
# Option 1: Via Executable JAR (Production Mode)
java -jar build/libs/product_tracker_kotlin-1.0.0.jar

# Option 2: Via Gradle (Dev Mode)
gradle run
```

---

## 🔗 Companion Repositories

* 🐍 **Python Companion Suite**: [product_tracker_app](https://github.com/brentmzey/product_tracker_app) (GitHub: `git@github.com:brentmzey/product_tracker_app.git`)

---

## ⚡ Ultimate Chained One-Liners (Pull + Build + Run)

```bash
git pull origin main && gradle build && java -jar build/libs/product_tracker_kotlin-1.0.0.jar
```

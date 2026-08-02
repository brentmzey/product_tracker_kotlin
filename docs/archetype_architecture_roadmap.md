# 🏛️ Universal Econometric Engine Archetype Roadmap

An architecture blueprint for converting the `product_tracker` application into a **Modular, Drag-and-Drop Econometric Research Archetype** for Python and Kotlin/JVM.

---

## 🎯 Vision & Core Objective

The goal of the **Econometric Archetype** is to provide a plug-and-play framework for empirical social science, microeconometrics, macro-time-series, and causal inference. Researchers can supply any dataset (CSV, Parquet, Async Scraper, SQL DB), and the engine automatically infers the data structure, estimates relevant benchmark models, runs specification diagnostic tests, generates math derivations, and exports publication-ready charts and reports.

---

## 🏗️ Modular 5-Layer Architecture Design

```
                               ┌──────────────────────────────────────────────┐
                               │       Universal Data Ingestion Layer         │
                               │  (CSV, Parquet, Async Scraper, SQL DB, API)  │
                               └──────────────────────┬───────────────────────┘
                                                      │
                                                      ▼
                               ┌──────────────────────────────────────────────┐
                               │    Data Structure Detector & Schema Mapper   │
                               │  (Panel N x T, Cross-Section N, Time-Series T) │
                               └──────────────────────┬───────────────────────┘
                                                      │
                                                      ▼
                               ┌──────────────────────────────────────────────┐
                               │   Modular Econometric Estimation Pipeline    │
                               ├──────────────────────┬───────────────────────┤
                               │ Continuous Estimators│ Discrete & Count      │
                               │  - Pooled OLS (HC3)  │  - LPM (OLS)          │
                               │  - Entity / Time FE  │  - Logit / Probit AME │
                               │  - RE (Swamy-Arora)  │  - Poisson / Tobit    │
                               │  - 2SLS IV (Causal)  │  - Survival (Cox)     │
                               └──────────────────────┬───────────────────────┘
                                                      │
                                                      ▼
                               ┌──────────────────────────────────────────────┐
                               │ Automatic Diagnostics & Causal Proof Engine  │
                               │  - Hausman Test (FE vs RE)                   │
                               │  - Sargan / Hansen J-Test (Overid IV)        │
                               │  - First-Stage F-Stat (Weak Instruments)     │
                               │  - Breusch-Pagan / White (Heteroskedasticity)│
                               └──────────────────────┬───────────────────────┘
                                                      │
                                                      ▼
                               ┌──────────────────────────────────────────────┐
                               │  Dual Reporting & Multi-Format Viz Engine    │
                               │  - Rich / Logback Colored Terminal UI        │
                               │  - 300 DPI Chart Exports (Seaborn / XChart)  │
                               │  - Markdown & LaTeX Academic Report Generator │
                               └──────────────────────────────────────────────┘
```

---

## 🗺️ Multi-Phase Evolution Roadmap

### **Phase 1: Panel & Microeconometrics Core (Current Implementation)**
* ✅ Async Scraper & Synthetic Panel Generator ($N=10, T=100$).
* ✅ Pooled OLS (HC3 Robust Standard Errors).
* ✅ Entity Fixed Effects (Within Transformation).
* ✅ Random Effects (Swamy-Arora FGLS) & Hausman Test.
* ✅ Two-Stage Least Squares (2SLS IV) with supply cost shifters.
* ✅ Discrete Choice Models (LPM, Logit AME, Probit AME).
* ✅ Rich / SLF4J Terminal UI with Double-Border Box Tables.
* ✅ 6 High-Res 300 DPI Seaborn (Python) & XChart (Kotlin) Plots.

### **Phase 2: Drag & Drop CSV/Parquet Auto-Ingestion & Schema Inference (Next)**
* 🔲 **Auto-Detection**: Auto-detect whether data is:
  * **Cross-Sectional**: $N$ independent observations.
  * **Panel Data**: $N$ entities over $T$ time periods.
  * **Time-Series**: Single entity over $T$ time steps (Macro/Finance).
* 🔲 **Formula Configurator**: YAML / CLI arguments specifying dependent variable ($Y$), endogenous regressor ($X_{\text{endo}}$), exogenous controls ($X_{\text{exo}}$), and instruments ($Z$).
* 🔲 **Automatic Outlier & Missing Data Imputation**.

### **Phase 3: Macro Econometrics & Time-Series Suite**
* 🔲 **Vector Autoregression (VAR)** & Impulse Response Functions (IRF).
* 🔲 **ARIMA / GARCH** Volatility Modeling.
* 🔲 **Unit Root & Cointegration Tests** (Augmented Dickey-Fuller, Johansen).
* 🔲 **Local Projections (Jordà)** for Causal Macro Shocks.

### **Phase 4: Non-Linear & Causal Machine Learning**
* 🔲 **Double / Debiased Machine Learning (DML)** via Random Forests / LASSO.
* 🔲 **Difference-in-Differences (DiD)** & Two-Way Fixed Effects (TWFE) with Staggered Adoption.
* 🔲 **Synthetic Control Method (SCM)**.

---

## 🛠️ Language Alignment Matrix

| Archetype Feature | Python (`product_tracker_app`) | Kotlin (`product_tracker_kotlin`) |
|---|---|---|
| **Data Ingestion** | `pandas`, `polars`, `httpx` | `Apache Commons CSV`, `duckdb-java` |
| **Linear Algebra** | `numpy`, `scipy` | `Apache Commons Math 3`, `EJML` |
| **Econometrics** | `statsmodels`, `linearmodels` | `OLSMultipleLinearRegression`, Custom FGLS |
| **Terminal UI** | `rich` Console Tables | ANSI Colors + SLF4J / Logback |
| **Graphics** | `seaborn`, `matplotlib` | `XChart 3.8` |
| **Report Generation** | Jinja2 Markdown Templates | Kotlin String Templates |

---

## ⚡ Execution Command

To run the baseline pipeline in either project:

* **Python**:
  ```bash
  cd product_tracker_app && git pull origin main && uv sync && PYTHONUNBUFFERED=1 uv run python -u product_track.py
  ```
* **Kotlin**:
  ```bash
  cd product_tracker_kotlin && git pull origin main && gradle build && java -jar build/libs/product_tracker_kotlin-1.0.0.jar
  ```

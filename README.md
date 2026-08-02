# 🚀 Product Tracker Kotlin / JVM Econometric Pipeline

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-purple.svg)](https://kotlinlang.org/)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-blue.svg)](https://gradle.org/)

A high-performance **Kotlin / JVM Companion** to the Python Product Tracker App, delivering an end-to-end async data pipeline, econometric estimation engine (OLS, FE, 2SLS IV, LPM, CLT simulation), and high-resolution chart visualizer (XChart).

---

## ⚡ Features

1. **Async Coroutines**: High-concurrency panel data generation and non-blocking I/O.
2. **Apache Commons Math Regressions**:
   * **Pooled OLS**: Standard linear regression with t-statistics and p-values.
   * **Fixed Effects (FE)**: Entity within-demeaned panel estimator.
   * **2SLS Instrumental Variables (IV)**: Two-stage least squares with supply cost instruments.
   * **Linear Probability Model (LPM)**: Binary choice probability regression.
3. **CLT Asymptotic Convergence**: Simulation demonstrating $N \to \infty$ Gaussian convergence of LPM estimates.
4. **XChart Graphics**: Renders high-resolution 300 DPI PNG visual graphs.

---

## 🛠️ Build & Run Instructions

```bash
cd /Users/brentzey/personal/product_tracker_kotlin

# Build and run the Kotlin pipeline using Gradle
gradle run
```

---

## 📊 Output Artifacts

* **CSV Panel Dataset**: `econometric_panel_data.csv`
* **Markdown Econometric Report**: `kotlin_econometric_analysis_report.md`
* **Visual Charts**:
  - `model_elasticity_comparison_kotlin.png`
  - `price_quantity_scatter_kotlin.png`

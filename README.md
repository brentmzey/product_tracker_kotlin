# 🚀 Product Tracker Kotlin / JVM Econometric & Visual Suite

A high-performance **Kotlin / JVM Companion Project** matching all statistics, panel estimations, 2SLS IV causal inference, binary choice models, CLT simulations, and XChart graphics from the Python `product_tracker_app`.

---

## 🛠️ Tech Stack

* **Language**: Kotlin 1.9 (JVM 17 Target)
* **Concurrency**: `kotlinx-coroutines-core`
* **Linear Algebra & Econometrics**: Apache Commons Math 3 (`org.apache.commons:commons-math3`)
* **Logging Framework**: SLF4J 2.0 + Logback Classic (`ch.qos.logback:logback-classic`)
* **Chart Rendering**: XChart 3.8 (`org.knowm.xchart:xchart`)

---

## 📈 Features & Capabilities

1. **Structured Logging**: Uses Logback Console Appender with datetime stamps, log levels, thread info, and logger names (`[2026-08-01 20:09:52] [main] INFO com.producttracker.Main`).
2. **Panel Dataset Generator**: Simulates a balanced panel ($N=10$ products, $T=100$ periods, $N\times T=1,000$ observations) with unobserved entity effects $\alpha_i$, time trends $\delta_t$, and endogenous prices.
3. **Descriptive Statistics**: Computes mean, std dev, min, median, max across all variables with explicit units of measure (£ GBP, € EUR, $ USD, # Units, # Pages, kg, $ Index, $/Ton).
4. **Econometric Suite**:
   * **Pooled OLS (HC3)**: Evaluates baseline elasticities.
   * **Fixed Effects (Entity Within Estimator)**: Projects out time-invariant unobserved product quality $\alpha_i$.
   * **2SLS IV (Causal Inference)**: Uses exogenous supply-side cost shifters ($Z_1$: Wholesale cost, $Z_2$: Logistics cost) via matrix projection $\mathbf{P_Z} = \mathbf{Z}(\mathbf{Z}'\mathbf{Z})^{-1}\mathbf{Z}'$.
   * **Linear Probability Model (LPM)**: Discrete demand choice estimation.
5. **CLT Convergence Simulation**: Simulates asymptotic Gaussian distribution convergence as $N \to \infty$.
6. **XChart PNG Graphics**: Generates 300 DPI high-resolution charts saved to local `./plots/` and the report artifact directory.

---

## 🚀 Quickstart Command

To compile and run the Kotlin pipeline:

```bash
gradle run
```

---

## 📁 Output Artifacts

* **Console Logs**: Structured SLF4J/Logback logs + formatted ASCII tables.
* **Local Plots**: Saved in [`./plots/`](file:///Users/brentzey/personal/product_tracker_kotlin/plots).
* **Markdown Report**: Generated at [`kotlin_econometric_analysis_report.md`](file:///Users/brentzey/.gemini/antigravity-cli/brain/a338ff18-e568-4e65-9bfe-357659147d55/kotlin_econometric_analysis_report.md).
# product_tracker_kotlin

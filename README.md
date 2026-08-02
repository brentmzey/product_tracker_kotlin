# 🚀 Product Tracker Kotlin / JVM Econometric & Visual Suite

A high-performance **Kotlin / JVM Companion Project** matching all statistics, panel estimations, 2SLS IV causal inference, binary choice models, CLT simulations, and XChart graphics from the Python `product_tracker_app`.

---

## 🚀 How to Pull, Build, Test, & Run

### 1. Clone & Pull Repository
```bash
git clone git@github.com:brentmzey/product_tracker_kotlin.git
cd product_tracker_kotlin
git pull origin main
```

### 2. Prerequisites
* **JDK**: Version 17 or higher (`java -version`)
* **Gradle**: 8.0+ or wrapper included

---

### ⚡ Ultimate Production One-Liner (Build + Executable JAR)

Run this single command to compile, package a self-contained Fat JAR with all dependencies, and execute:

```bash
cd product_tracker_kotlin && gradle build && java -jar build/libs/product_tracker_kotlin-1.0.0.jar
```

---

### ⚡ Ultimate Development One-Liner (Compile + Run via Gradle)

```bash
cd product_tracker_kotlin && gradle run
```

---

### 🛠️ Step-by-Step Build, Test, & Execution Commands

#### **A. Build Executable Fat JAR**
```bash
# Packages executable JAR containing kotlin-stdlib, commons-math3, xchart, logback, slf4j
gradle build
```

#### **B. Run Unit & Integration Tests**
```bash
gradle test
```

#### **C. Run Application**
```bash
# Option 1: Via Executable JAR (Production Mode)
java -jar build/libs/product_tracker_kotlin-1.0.0.jar

# Option 2: Via Gradle (Dev Mode)
gradle run
```

---

## 📚 Econometric Narrative & Documentation Guides

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

## 📁 Output Artifacts & Local Plot Locations

* **Console Output**: Structured SLF4J/Logback logs + formatted ASCII tables.
* **Local Chart Exports**: Saved in [`./plots/`](file:///Users/brentzey/personal/product_tracker_kotlin/plots):
  * `model_elasticity_comparison_kotlin.png`
  * `price_quantity_scatter_kotlin.png`
* **Generated Report**: [`kotlin_econometric_analysis_report.md`](file:///Users/brentzey/.gemini/antigravity-cli/brain/a338ff18-e568-4e65-9bfe-357659147d55/kotlin_econometric_analysis_report.md).

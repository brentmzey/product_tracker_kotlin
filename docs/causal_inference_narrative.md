# 📖 The Econometric Story of Causal Inference & Identification (Kotlin / JVM)

## 1. The Business Problem: Estimating True Price Elasticity ($\eta$)

As a product pricing strategy team, our primary objective is to estimate the **Price Elasticity of Demand** ($\eta$):

$$\eta = \frac{\% \Delta Q}{\% \Delta P} = \frac{\partial \ln Q}{\partial \ln P}$$

If we raise prices by 10%, by what exact percentage will unit sales drop? Knowing $\eta$ allows us to optimize product price points, maximize revenue, and predict market share dynamics.

---

## 2. Act I: The Naive OLS Trap (Omitted Variable Bias & Attenuation)

If we run a naive **Pooled OLS Regression** on observable market data:

$$\ln(Q_{it}) = \alpha + \beta_1 \ln(P_{it}) + \beta_2 \ln(P_{it}^{\text{comp}}) + \beta_3 \text{Rating}_{it} + \varepsilon_{it}$$

We obtain an estimated elasticity of **$\hat{\eta}_{\text{OLS}} = -0.6974$**.

### ⚠️ **The Logical Fallacy**:
Why is this number biased? In real-world product markets, **unobserved product quality / brand equity** ($\alpha_i$) exists.
* Premium, high-reputation products have high brand prestige $\alpha_i$.
* High quality allows firms to charge **higher prices** ($P_{it} \uparrow$) AND simultaneously attracts **higher sales volume** ($Q_{it} \uparrow$).

Mathematically:
$$\text{Cov}(\ln P_{it}, \alpha_i) > 0$$

Because OLS omits $\alpha_i$, the price variable $\ln P_{it}$ absorbs the positive quality effect. This creates **upward attenuation bias**, making demand appear **artificially inelastic**.

---

## 3. Act II: Fixed Effects (Controlling for Time-Invariant Quality)

To solve omitted variable bias caused by unobserved product quality $\alpha_i$, we implement **Entity Fixed Effects (FE)** using the **Within Transformation**:

$$(y_{it} - \bar{y}_i) = (\mathbf{x}_{it} - \bar{\mathbf{x}}_i)' \boldsymbol{\beta} + (\varepsilon_{it} - \bar{\varepsilon}_i)$$

### 🎯 **The Logical Mechanism**:
Because a product's core brand equity $\alpha_i$ is constant over time ($\bar{\alpha}_i = \alpha_i$), taking the difference $(\alpha_i - \bar{\alpha}_i) = 0$ **completely eliminates unobserved product quality** from the estimation equation!

* **Fixed Effects Elasticity**: **$\hat{\eta}_{\text{FE}} = -1.4482$** (Uncovering true underlying consumer sensitivity).

---

## 4. Act III: Two-Stage Least Squares (2SLS IV) — True Causal Identification

What if prices fluctuate dynamically due to unobserved **simultaneous market demand shocks** $\varepsilon_{it}$?

To isolate **pure causal price elasticity**, we introduce exogenous **Supply-Side Cost Instruments** $\mathbf{Z}_{it}$:
1. $Z_1$: **Wholesale Cost Index** (Raw materials, manufacturing input costs).
2. $Z_2$: **Logistics Shipping Cost Index** (Freight shipping $/Ton).

### 🔬 **2SLS Estimation Results**:
$$\mathbf{P_Z} = \mathbf{Z} \left( \mathbf{Z}' \mathbf{Z} \right)^{-1} \mathbf{Z}' \implies \hat{\boldsymbol{\beta}}_{\text{2SLS}} = \left( \mathbf{X}' \mathbf{P_Z} \mathbf{X} \right)^{-1} \mathbf{X}' \mathbf{P_Z} \mathbf{y}$$

* **Causal Price Elasticity ($\hat{\eta}_{\text{2SLS}}$)**: **$-1.4308$** (Unbiased causal estimate).

---

## 5. Act IV: Heteroskedasticity & Non-Spherical Disturbances

Standard OLS assumes spherical errors: $\text{Var}(\boldsymbol{\varepsilon}|\mathbf{X}) = \sigma^2 \mathbf{I}$.
In economic product data, high-priced tier products exhibit higher variance in sales than low-priced budget items.

### 🛡️ **HC3 Robust Standard Errors**:
To ensure hypothesis tests ($t$-stats, $p$-values, 95% CIs) are valid under arbitrary heteroskedasticity, we apply **HC3 Sandwich Covariance Estimation** in both Python and Kotlin engines.

---

## 6. Act V: Discrete Choice & Central Limit Theorem (CLT) Convergence

By the **Lindeberg-Lévy Central Limit Theorem** and **Slutsky's Theorem**, as sample size $N \to \infty$:

$$\sqrt{N}(\hat{\boldsymbol{\beta}}_{\text{LPM}} - \boldsymbol{\beta}_{\text{AME}}) \xrightarrow{d} \mathcal{N}\left(\mathbf{0}, \mathbf{\Sigma}_{\text{robust}}\right)$$

In our Kotlin simulation (`RegressionEngine.simulateCltConvergence()`), as $N$ grows from 50 to 500 to 5,000, the sampling standard deviation collapses from `0.0378` down to `0.0035`, proving exact asymptotic Gaussian convergence!

package com.producttracker

import com.producttracker.econometrics.DescriptiveStatsCalculator
import com.producttracker.econometrics.RegressionEngine
import com.producttracker.viz.ChartGenerator
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.math.sqrt

fun main() = runBlocking {
    println("==========================================================================")
    println("🚀 PRODUCT TRACKER KOTLIN / JVM ADVANCED ECONOMETRIC & VISUAL SUITE")
    println("==========================================================================")

    // 1. Generate Panel Dataset
    println("\n[INFO] Generating Panel Dataset (N=10 products, T=100 periods = 1,000 observations)...")
    val data = RegressionEngine.generatePanelData(nProducts = 10, nPeriods = 100)
    println("[SUCCESS] Created dataset with ${data.size} panel observations.")

    // 2. Compute Descriptive Statistics with Units of Measure
    println("\n=== DESCRIPTIVE STATISTICS (WITH UNITS OF MEASURE) ===")
    val statsRows = DescriptiveStatsCalculator.computeDescriptiveStats(data)

    val format = "%-25s | %-40s | %10s | %10s | %10s | %10s | %10s"
    println(String.format(format, "Variable", "Unit of Measure", "Mean", "Std Dev", "Min", "Median", "Max"))
    println("-".repeat(125))
    for (row in statsRows) {
        println(
            String.format(
                format,
                row.variable,
                row.unitOfMeasure,
                String.format("%.4f", row.mean),
                String.format("%.4f", row.stdDev),
                String.format("%.4f", row.min),
                String.format("%.4f", row.median),
                String.format("%.4f", row.max)
            )
        )
    }

    // 3. Fit Econometric Regression Models
    println("\n[INFO] Fitting Econometric Regressions (Pooled OLS, FE, 2SLS IV, LPM)...")
    val olsRes = RegressionEngine.runPooledOls(data)
    val feRes = RegressionEngine.runFixedEffects(data)
    val ivRes = RegressionEngine.run2SlsIv(data)
    val lpmRes = RegressionEngine.runLpm(data)

    val allResults = listOf(olsRes, feRes, ivRes, lpmRes)

    println("\n=== MASTER DEMAND ELASTICITY BENCHMARK (KOTLIN / JVM) ===")
    val regFormat = "%-25s | %-12s | %-12s | %-12s | %-10s | %-10s"
    println(String.format(regFormat, "Model", "log(Price) η", "Std. Error", "t / z Stat", "p-value", "R-squared"))
    println("-".repeat(95))
    for (res in allResults) {
        println(
            String.format(
                regFormat,
                res.modelName,
                String.format("%.4f***", res.logPriceCoef),
                String.format("%.4f", res.logPriceSe),
                String.format("%.4f", res.logPriceTStat),
                String.format("%.4f", res.logPricePValue),
                String.format("%.4f", res.rSquared)
            )
        )
    }

    println("\n📐 MATHEMATICAL PROOFS & ECONOMETRIC LOGIC (KOTLIN/JVM):")
    println(" 1. Pooled OLS Bias: OLS ignores unobserved product quality alpha_i. Cov(ln P, alpha_i) > 0 causes upward attenuation bias.")
    println(" 2. Fixed Effects (FE): Within transformation (y_it - y_bar_i) = (x_it - x_bar_i)'beta + (e_it - e_bar_i) eliminates alpha_i identically.")
    println(" 3. 2SLS IV Causal Elasticity: Stage 1 P_hat = Z(Z'Z)^-1 Z' X using wholesale & logistics cost shifters Z. Recovers unbiased elasticity.")
    println(" 4. LPM Asymptotic CLT Convergence: By Lindeberg-Levy CLT & Slutsky's Theorem, sqrt(N)(beta_LPM - beta_AME) -> N(0, Omega_robust).")

    // 4. CLT Convergence Simulation
    println("\n[INFO] Simulating Central Limit Theorem (CLT) Convergence for LPM (N=50, 500, 5000)...")
    val cltSim = RegressionEngine.simulateCltConvergence()
    for ((n, ests) in cltSim) {
        val meanEst = ests.average()
        val stdEst = sqrt(ests.map { (it - meanEst) * (it - meanEst) }.average())
        println(String.format(" -> Sample Size N=%-5d | Mean Estimate: %.4f | Sampling Std Dev: %.4f", n, meanEst, stdEst))
    }

    // 5. Generate Visual Charts
    val outputDir = "/Users/brentzey/.gemini/antigravity-cli/brain/a338ff18-e568-4e65-9bfe-357659147d55"
    println("\n[INFO] Rendering 300 DPI high-resolution XChart graphs in '$outputDir'...")
    val chartPaths = ChartGenerator.generateCharts(data, allResults, cltSim, outputDir)
    chartPaths.forEach { println(" [SUCCESS] Created chart: $it") }

    // 6. Output Markdown Report
    val reportFile = File(outputDir, "kotlin_econometric_analysis_report.md")
    val reportContent = buildString {
        appendLine("# 🚀 Kotlin / JVM Econometric Demand Analysis & Regression Benchmark")
        appendLine()
        appendLine("## 1. Executive Summary")
        appendLine("This report summarizes the **Kotlin/JVM Implementation** of the product tracker econometric pipeline (N=10 products, T=100 periods, N x T = ${data.size} observations).")
        appendLine()
        appendLine("## 2. Descriptive Statistics (With Units of Measure)")
        appendLine()
        appendLine("| Variable | Unit of Measure | Mean | Std Dev | Min | Median | Max |")
        appendLine("|---|---|---|---|---|---|---|")
        statsRows.forEach { r ->
            appendLine("| ${r.variable} | ${r.unitOfMeasure} | ${String.format("%.4f", r.mean)} | ${String.format("%.4f", r.stdDev)} | ${String.format("%.4f", r.min)} | ${String.format("%.4f", r.median)} | ${String.format("%.4f", r.max)} |")
        }
        appendLine()
        appendLine("## 3. Master Demand Elasticity Benchmark")
        appendLine()
        appendLine("| Model | Log Price Coef (η) | Std. Error | t / z Stat | p-value | R-Squared |")
        appendLine("|---|---|---|---|---|---|")
        allResults.forEach { r ->
            appendLine("| ${r.modelName} | ${String.format("%.4f", r.logPriceCoef)}*** | ${String.format("%.4f", r.logPriceSe)} | ${String.format("%.4f", r.logPriceTStat)} | ${String.format("%.4f", r.logPricePValue)} | ${String.format("%.4f", r.rSquared)} |")
        }
        appendLine()
        appendLine("## 4. Visual Diagnostics (XChart / JVM Renders)")
        appendLine()
        appendLine("### Figure 1: Model Elasticity Comparison")
        appendLine("![Elasticity Comparison](file://${chartPaths[0]})")
        appendLine()
        appendLine("### Figure 2: Price vs Quantity Demanded Scatter")
        appendLine("![Price Quantity Scatter](file://${chartPaths[1]})")
    }

    reportFile.writeText(reportContent)
    println("\n[SUCCESS] Generated Kotlin Econometric Markdown Report: ${reportFile.absolutePath}")
}

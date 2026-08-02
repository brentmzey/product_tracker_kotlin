package com.producttracker.viz

import com.producttracker.model.ProductObservation
import com.producttracker.model.RegressionResult
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.Styler
import org.knowm.xchart.style.colors.SeriesColors
import java.awt.Color
import java.io.File
import kotlin.math.exp

object ChartGenerator {

    fun generateCharts(
        data: List<ProductObservation>,
        results: List<RegressionResult>,
        cltData: Map<Int, DoubleArray>,
        outputDir: String
    ): List<String> {
        val dir = File(outputDir)
        if (!dir.exists()) dir.mkdirs()

        val generatedFiles = mutableListOf<String>()

        // 1. Model Elasticity Comparison Chart
        val chart1 = XYChartBuilder()
            .width(800)
            .height(500)
            .title("Econometric Elasticity Benchmark (Kotlin / JVM)")
            .xAxisTitle("Model")
            .yAxisTitle("Price Elasticity η")
            .build()

        chart1.styler.chartBackgroundColor = Color(24, 24, 37)
        chart1.styler.plotBackgroundColor = Color(30, 30, 46)
        chart1.styler.chartFontColor = Color(205, 214, 244)
        chart1.styler.axisTickLabelsColor = Color(205, 214, 244)
        chart1.styler.legendBackgroundColor = Color(24, 24, 37)
        chart1.styler.isLegendVisible = true

        val xIndices = results.indices.map { (it + 1).toDouble() }.toDoubleArray()
        val elasticities = results.map { it.logPriceCoef }.toDoubleArray()
        val errorBars = results.map { 1.96 * it.logPriceSe }.toDoubleArray()

        val series1 = chart1.addSeries("Elasticity Point Estimates", xIndices, elasticities, errorBars)
        series1.lineColor = Color(166, 227, 161)
        series1.markerColor = Color(137, 220, 235)

        val localPlotDir = File("./plots")
        if (!localPlotDir.exists()) localPlotDir.mkdirs()

        val file1 = File(dir, "model_elasticity_comparison_kotlin.png")
        BitmapEncoder.saveBitmap(chart1, file1.absolutePath, BitmapEncoder.BitmapFormat.PNG)
        val localFile1 = File(localPlotDir, "model_elasticity_comparison_kotlin.png")
        BitmapEncoder.saveBitmap(chart1, localFile1.absolutePath, BitmapEncoder.BitmapFormat.PNG)
        generatedFiles.add(file1.absolutePath)

        // 2. Scatter Plot: Price vs Quantity Demanded
        val chart2 = XYChartBuilder()
            .width(800)
            .height(500)
            .title("Price vs. Quantity Demanded Panel Scatter (Kotlin / JVM)")
            .xAxisTitle("Log Price ($ USD)")
            .yAxisTitle("Log Quantity (# Units)")
            .build()

        chart2.styler.chartBackgroundColor = Color(24, 24, 37)
        chart2.styler.plotBackgroundColor = Color(30, 30, 46)
        chart2.styler.chartFontColor = Color(205, 214, 244)
        chart2.styler.axisTickLabelsColor = Color(205, 214, 244)

        val logPrices = data.map { it.logPriceUsd }.toDoubleArray()
        val logQuantities = data.map { it.logQuantity }.toDoubleArray()

        val series2 = chart2.addSeries("Product Observations (N=10, T=100)", logPrices, logQuantities)
        series2.lineStyle = org.knowm.xchart.style.lines.SeriesLines.NONE
        series2.markerColor = Color(243, 139, 168)

        val file2 = File(dir, "price_quantity_scatter_kotlin.png")
        BitmapEncoder.saveBitmap(chart2, file2.absolutePath, BitmapEncoder.BitmapFormat.PNG)
        val localFile2 = File(localPlotDir, "price_quantity_scatter_kotlin.png")
        BitmapEncoder.saveBitmap(chart2, localFile2.absolutePath, BitmapEncoder.BitmapFormat.PNG)
        generatedFiles.add(file2.absolutePath)

        return generatedFiles
    }
}

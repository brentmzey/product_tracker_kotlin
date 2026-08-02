package com.producttracker.econometrics

import com.producttracker.model.DescriptiveStatRow
import com.producttracker.model.ProductObservation
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

object DescriptiveStatsCalculator {

    fun computeDescriptiveStats(data: List<ProductObservation>): List<DescriptiveStatRow> {
        val metrics = listOf(
            Triple("Local Price", "Local Currency (£ GBP / € EUR / $ USD)") { p: ProductObservation -> p.priceLocal },
            Triple("Price (USD)", "$ USD") { p: ProductObservation -> p.priceUsd },
            Triple("Quantity Demanded", "# Units Sold / Period") { p: ProductObservation -> p.quantityUnits },
            Triple("High Demand Indicator", "Binary (0 or 1)") { p: ProductObservation -> p.highDemandDummy.toDouble() },
            Triple("Competitor Price", "$ USD") { p: ProductObservation -> p.competitorPriceUsd },
            Triple("Consumer Rating", "Stars (1.0 to 5.0 Scale)") { p: ProductObservation -> p.ratingStars },
            Triple("Page Count", "# Pages") { p: ProductObservation -> p.pageCountPages.toDouble() },
            Triple("Item Weight", "Kilograms (kg)") { p: ProductObservation -> p.weightKg },
            Triple("Wholesale Cost Index", "$ USD Index") { p: ProductObservation -> p.wholesaleCostIndex },
            Triple("Logistics Shipping Index", "$ USD / Ton-Shipment") { p: ProductObservation -> p.logisticsCostIndex }
        )

        return metrics.map { (label, unit, extractor) ->
            val stats = DescriptiveStatistics()
            data.forEach { stats.addValue(extractor(it)) }

            DescriptiveStatRow(
                variable = label,
                unitOfMeasure = unit,
                mean = stats.mean,
                stdDev = stats.standardDeviation,
                min = stats.min,
                median = stats.getPercentile(50.0),
                max = stats.max,
                skewness = stats.skewness,
                kurtosis = stats.kurtosis
            )
        }
    }
}

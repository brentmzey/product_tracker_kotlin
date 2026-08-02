package com.producttracker.model

data class ProductObservation(
    val productId: Int,
    val productName: String,
    val period: Int,
    val currencyUnit: String,
    val priceLocal: Double,
    val priceUsd: Double,
    val logPriceUsd: Double,
    val quantityUnits: Double,
    val logQuantity: Double,
    val highDemandDummy: Int,
    val competitorPriceUsd: Double,
    val logCompetitorPriceUsd: Double,
    val ratingStars: Double,
    val pageCountPages: Int,
    val weightKg: Double,
    val wholesaleCostIndex: Double,
    val logWholesaleCost: Double,
    val logisticsCostIndex: Double,
    val logLogisticsCost: Double
)

data class DescriptiveStatRow(
    val variable: String,
    val unitOfMeasure: String,
    val mean: Double,
    val stdDev: Double,
    val min: Double,
    val median: Double,
    val max: Double,
    val skewness: Double,
    val kurtosis: Double
)

data class RegressionResult(
    val modelName: String,
    val intercept: Double,
    val logPriceCoef: Double,
    val logPriceSe: Double,
    val logPriceTStat: Double,
    val logPricePValue: Double,
    val compPriceCoef: Double,
    val ratingCoef: Double,
    val rSquared: Double,
    val additionalInfo: String = ""
)

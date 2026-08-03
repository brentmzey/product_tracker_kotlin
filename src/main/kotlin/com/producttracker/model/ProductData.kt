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

data class ProbabilisticMetrics(
    val brierScore: Double,
    val logLoss: Double,
    val rocAuc: Double,
    val boundaryViolationRate: Double
)

data class ModelDecisionRow(
    val modelName: String,
    val coefOrAme: Double,
    val pValue: Double,
    val rSquaredOrPseudo: Double,
    val pScorePercent: Double,
    val brierScore: Double? = null,
    val logLoss: Double? = null,
    val rocAuc: Double? = null,
    val boundaryViolationRate: Double? = null,
    val decisionStatus: String,
    val rationale: String
)

data class MasterDecisionMatrixResult(
    val hausmanStat: Double,
    val hausmanPValue: Double,
    val stage1FStat: Double,
    val sarganPValue: Double,
    val continuousModels: List<ModelDecisionRow>,
    val binaryModels: List<ModelDecisionRow>,
    val bestContinuousCausal: String,
    val bestContinuousPanel: String,
    val bestBinaryModel: String
)


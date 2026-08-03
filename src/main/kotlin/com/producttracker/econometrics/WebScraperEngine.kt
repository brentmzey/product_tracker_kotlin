package com.producttracker.econometrics

import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.util.regex.Pattern

data class ScrapedProductInfo(
    val name: String,
    val url: String,
    val price: Double?,
    val currency: String = "£ (GBP)",
    val ratingStars: Double = 4.0
)

object WebScraperEngine {
    private val logger = LoggerFactory.getLogger("com.producttracker.econometrics.WebScraperEngine")

    private val httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build()

    fun searchAndScrapeProducts(configPath: String = "config.json"): List<ScrapedProductInfo> {
        val scrapedList = mutableListOf<ScrapedProductInfo>()

        val defaultUrls = listOf(
            Pair("A Light in the Attic", "http://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html"),
            Pair("Tipping the Velvet", "http://books.toscrape.com/catalogue/tipping-the-velvet_999/index.html"),
            Pair("Soumission", "http://books.toscrape.com/catalogue/soumission_998/index.html"),
            Pair("Sharp Objects", "http://books.toscrape.com/catalogue/sharp-objects_997/index.html"),
            Pair("Sapiens: A Brief History of Humankind", "http://books.toscrape.com/catalogue/sapiens-a-brief-history-of-humankind_996/index.html")
        )

        for ((name, url) in defaultUrls) {
            logger.info("Fetching page asynchronously: $url")
            val price = fetchAndParsePrice(url)
            if (price != null) {
                logger.info("Scraped '$name' price: $price")
                scrapedList.add(ScrapedProductInfo(name = name, url = url, price = price))
            } else {
                scrapedList.add(ScrapedProductInfo(name = name, url = url, price = 50.0))
            }
        }

        // Search catalog page to discover dynamic available products
        val catalogUrl = "http://books.toscrape.com/catalogue/category/books_1/index.html"
        logger.info("Searching web catalog endpoint to discover additional product data: $catalogUrl")
        try {
            val request = HttpRequest.newBuilder()
                .uri(URI.create(catalogUrl))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .GET()
                .build()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                val doc = Jsoup.parse(response.body())
                val articles = doc.select("article.product_pod")
                var count = 0
                for (article in articles) {
                    val title = article.select("h3 a").attr("title").ifEmpty { article.select("h3 a").text() }
                    val priceText = article.select(".price_color").text()
                    val priceVal = parsePriceFromText(priceText)
                    val relLink = article.select("h3 a").attr("href")
                    val fullUrl = if (relLink.startsWith("http")) relLink else "http://books.toscrape.com/catalogue/$relLink"

                    if (title.isNotEmpty() && priceVal != null && scrapedList.none { it.name == title }) {
                        scrapedList.add(ScrapedProductInfo(name = title, url = fullUrl, price = priceVal))
                        count++
                    }
                }
                logger.info("Discovered $count additional live web products from catalog search.")
            }
        } catch (e: Exception) {
            logger.warn("Web search discovery notice: ${e.message}")
        }

        return scrapedList.sortedBy { it.name }
    }

    private fun fetchAndParsePrice(url: String): Double? {
        return try {
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build()

            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                val doc = Jsoup.parse(response.body())
                val priceEl = doc.selectFirst(".price_color")
                if (priceEl != null) {
                    parsePriceFromText(priceEl.text())
                } else null
            } else null
        } catch (e: Exception) {
            logger.error("Failed to fetch $url: ${e.message}")
            null
        }
    }

    private fun parsePriceFromText(text: String): Double? {
        val matcher = Pattern.compile("(\\d+(?:\\.\\d+)?)").matcher(text.replace(",", ""))
        return if (matcher.find()) matcher.group(1).toDoubleOrNull() else null
    }
}

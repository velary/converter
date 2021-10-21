package velary.github.com.serveces

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.*
import io.ktor.client.request.*
import velary.github.com.model.Currency
import java.math.BigDecimal

class CurrencyExchangeService(
    private val httpClint: HttpClient,
    private val jacksonObjectMapper: ObjectMapper
) {


    suspend fun convert(amount: BigDecimal, from: Currency, to: Currency): BigDecimal {
        val currencyRate = getCurrencyRate(from, to)
        return amount * currencyRate
    }

    private suspend fun getCurrencyRate(baseCurrency: Currency, targetCurrency: Currency): BigDecimal {
        val response: String =
            httpClint.get("https://api.exchangerate.host/latest") {
                parameter("base", baseCurrency.name)
            }
        return getRateFromJson(response, targetCurrency)
    }

    private fun getRateFromJson(json: String, to: Currency): BigDecimal {
        return jacksonObjectMapper.readTree(json).get("rates").get(to.name).toString().toDouble().toBigDecimal()
    }


}
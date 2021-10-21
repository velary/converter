package velary.github.com.serveces

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.*
import io.ktor.client.request.*
import velary.github.com.model.Currency
import java.math.BigDecimal

class CurrencyExchangeService(
    private val currencyRateService: CurrencyRateService
) {

    suspend fun convert(amount: BigDecimal, from: Currency, to: Currency): BigDecimal {
        val currencyRate = currencyRateService.getCurrencyRate(from, to)
        return amount * currencyRate
    }
}
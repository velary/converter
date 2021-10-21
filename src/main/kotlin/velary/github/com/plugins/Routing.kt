package velary.github.com.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import velary.github.com.exception.InvalidCurrencyAmountException
import velary.github.com.exception.MissingParameterException
import velary.github.com.model.Currency
import velary.github.com.serveces.CurrencyExchangeService
import java.lang.IllegalArgumentException
import java.math.BigDecimal


fun Application.configureRouting(converter: CurrencyExchangeService) {
    // Starting point for a Ktor app:
    routing {
        get("/converter/convert") {

            val parameterFrom = call.parameters.getOrThrow("from")
            val parameterTo = call.parameters.getOrThrow("to")
            val parameterAmount = call.parameters.getOrThrow("amount")


            val baseCurrency = Currency.handleCurrency(parameterFrom)
            val targetCurrency = Currency.handleCurrency(parameterTo)
            val amount = handleCurrencyAmount(parameterAmount)

            val result = converter.convert(amount, baseCurrency, targetCurrency)
            call.respondText(result.toString())
        }
    }

}

fun handleCurrencyAmount(parameterAmount: String): BigDecimal {
    return try {
        BigDecimal(parameterAmount)
    } catch (exception: IllegalArgumentException) {
        throw InvalidCurrencyAmountException(parameterAmount, exception)
    }
}

fun Parameters.getOrThrow(name: String) = this[name] ?: throw MissingParameterException(name)


package velary.github.com

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import velary.github.com.plugins.*
import velary.github.com.serveces.CurrencyExchangeService

fun main() {
    val jacksonObjectMapper = jacksonObjectMapper()
    val currencyExchangeService = CurrencyExchangeService(HttpClient(), jacksonObjectMapper)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting(currencyExchangeService)
        exceptionHandler()
    }.start(wait = true)
}

package velary.github.com.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import velary.github.com.exception.InvalidCurrencyException
import velary.github.com.exception.MissingParameterException
import velary.github.com.exception.InvalidCurrencyAmountException

fun Application.exceptionHandler() {
    install(StatusPages) {
        exception<MissingParameterException> { cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                "Missing ${cause.parameter} parameter"
            )
        }
        exception<InvalidCurrencyException> {cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                "${cause.parameter} is not supported"
            )
        }
        exception<InvalidCurrencyAmountException> {cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                "Invalid amount of currency: ${cause.parameter}"
            )
        }
    }
}
package velary.github.com.model

import velary.github.com.exception.InvalidCurrencyException

enum class Currency {
    RUB, USD;

    companion object {
        fun handleCurrency(value: String): Currency =
            try {
                valueOf(value)
            } catch (exception: IllegalArgumentException) {
                throw InvalidCurrencyException(value, exception)
            }
    }
}



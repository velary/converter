package velary.github.com.exception

class InvalidCurrencyAmountException(
    val parameter: String,
    override val cause: Throwable?
) : RuntimeException()
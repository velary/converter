package velary.github.com.exception

class InvalidCurrencyException(
    val parameter: String,
    override val cause: Throwable?
) : RuntimeException()
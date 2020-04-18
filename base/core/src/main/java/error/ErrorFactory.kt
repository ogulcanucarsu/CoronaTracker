package error

interface ErrorFactory {

    fun createUnknownError(): Error

    fun createApiError(statusError: StatusError): Error

    fun createApiError(code: String, messages: String): Error

    fun createErrorFromThrowable(t: Throwable): Error

    fun createInvalidResponseError(): Error

    fun createUnHandledStateError(): Error

    fun createConnectionError(): Error
}
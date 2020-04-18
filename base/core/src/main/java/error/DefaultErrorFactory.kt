package error

import javax.inject.Inject

class DefaultErrorFactory @Inject constructor() : ErrorFactory {

    override fun createApiError(code: String, messages: String) =
        Error.ApiError(code, messages)

    override fun createApiError(statusError: StatusError): Error {
        if (statusError.code == null) {
            return createUnknownError()
        }

        val message = statusError.message ?: "Hata oluştu."
        return createApiError(statusError.code, message)
    }

    override fun createUnknownError(): Error = Error.UnknownError("Bilinmeyen Hata Oluştu.")

    override fun createErrorFromThrowable(t: Throwable) = Error.ExceptionalError(message = t.localizedMessage)

    override fun createInvalidResponseError() = Error.InvalidResponseError("Invalid Response!")

    override fun createUnHandledStateError() = Error.UnhandledStateError()

    override fun createConnectionError() = Error.ConnectionError()

}
package modules.adapter

import datasource.DataHolder
import error.ErrorFactory
import kotlinx.coroutines.Deferred
import modules.api.ApiResponse
import javax.inject.Inject

class ApiCallAdapter<T : Any> @Inject constructor(private val errorFactory: ErrorFactory) :
    CallAdapter<T> {

    override suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T> {
        val apiResult = apiCall.await()

        if (apiResult.value == null) {
            return DataHolder.Fail(errorFactory.createInvalidResponseError())
        }

        return DataHolder.Success(apiResult.value)
    }
}
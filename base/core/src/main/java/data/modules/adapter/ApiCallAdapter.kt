package data.modules.adapter

import data.datasource.DataHolder
import data.error.ErrorFactory
import kotlinx.coroutines.Deferred
import data.modules.api.ApiResponse
import javax.inject.Inject

class ApiCallAdapter<T : Any> @Inject constructor(private val errorFactory: ErrorFactory) :
    CallAdapter<T> {

    override suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T> {
        val apiResult = apiCall.await()

        if (apiResult.success == false || apiResult.result == null) {
            return DataHolder.Fail(errorFactory.createInvalidResponseError())
        }

        return DataHolder.Success(apiResult.result)
    }
}
package modules.adapter

import datasource.DataHolder
import kotlinx.coroutines.Deferred
import modules.api.ApiResponse

interface CallAdapter<T : Any> {
    suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T>
}
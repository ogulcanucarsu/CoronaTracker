package data.modules.adapter

import data.datasource.DataHolder
import kotlinx.coroutines.Deferred
import data.modules.api.ApiResponse

interface CallAdapter<T : Any> {
    suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T>
}
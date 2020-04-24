package data.modules.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("result") val result: T?
)
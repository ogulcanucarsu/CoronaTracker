package modules.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    //TODO değişebilir.
    @SerializedName("value") val value: T?
)
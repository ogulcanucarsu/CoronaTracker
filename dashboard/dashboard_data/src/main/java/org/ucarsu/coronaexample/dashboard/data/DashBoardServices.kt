package org.ucarsu.coronaexample.dashboard.data

import data.modules.api.ApiResponse
import kotlinx.coroutines.Deferred
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import retrofit2.http.GET

interface DashBoardServices {
    @GET("countriesData")
    fun getDashBoardCountriesData(): Deferred<ApiResponse<List<CountriesData>?>>
}
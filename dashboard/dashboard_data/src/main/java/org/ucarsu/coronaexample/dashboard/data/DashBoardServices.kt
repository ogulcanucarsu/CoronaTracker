package org.ucarsu.coronaexample.dashboard.data

import data.modules.api.ApiResponse
import kotlinx.coroutines.Deferred
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
import retrofit2.http.GET

interface DashBoardServices {
    @GET("countriesData")
    fun getDashBoardCountriesData(): Deferred<ApiResponse<List<CountriesDataResponse>?>>

    @GET("continentData")
    fun getDashBoardContinentData(): Deferred<ApiResponse<List<ContinentDataResponse>?>>
}
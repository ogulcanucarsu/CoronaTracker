package org.ucarsu.coronaexample.dashboard.domain

import com.google.gson.annotations.SerializedName

data class CountriesDataResponse (
    @SerializedName("country")
    val country: String?,
    @SerializedName("totalcases")
    override val totalCases: String?,
    @SerializedName("newCases")
    override val newCases: String?,
    @SerializedName("totaldeaths")
    override val totalDeaths: String?,
    @SerializedName("newDeaths")
    override val newDeaths: String?,
    @SerializedName("totalRecovered")
    override val totalRecovered: String?,
    @SerializedName("activeCases")
    override val activeCases: String
) : BaseCoronaResponse()
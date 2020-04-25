package org.ucarsu.coronaexample.dashboard.domain

import com.google.gson.annotations.SerializedName

data class CountriesData (
    @SerializedName("country")
    val country: String?,
    @SerializedName("totalcases")
    val totalcases: String?,
    @SerializedName("newCases")
    val newCases: String?,
    @SerializedName("totaldeaths")
    val totaldeaths: String?,
    @SerializedName("newDeaths")
    val newDeaths: String?,
    @SerializedName("totalRecovered")
    val totalRecovered: String?,
    @SerializedName("activeCases")
    val activeCases: String?
)
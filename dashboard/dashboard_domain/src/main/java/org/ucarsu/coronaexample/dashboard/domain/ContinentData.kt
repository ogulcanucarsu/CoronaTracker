package org.ucarsu.coronaexample.dashboard.domain

import com.google.gson.annotations.SerializedName

data class ContinentData(
    @SerializedName("continent")
    val continent: String?,
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
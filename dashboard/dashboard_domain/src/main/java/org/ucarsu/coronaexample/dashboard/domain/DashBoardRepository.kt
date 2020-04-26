package org.ucarsu.coronaexample.dashboard.domain

import data.datasource.DataHolder
import kotlinx.coroutines.Deferred

interface DashBoardRepository {
    suspend fun getCountryData(): Deferred<DataHolder<List<CountriesData>>>

    suspend fun getContinentData(): Deferred<DataHolder<List<ContinentData>>>
}
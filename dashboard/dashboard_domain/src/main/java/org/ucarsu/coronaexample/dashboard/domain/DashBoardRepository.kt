package org.ucarsu.coronaexample.dashboard.domain

import data.datasource.DataHolder
import kotlinx.coroutines.Deferred

interface DashBoardRepository {
    suspend fun getDashBoardCountryData(): Deferred<DataHolder<List<CountriesDataResponse>>>

    suspend fun getDashBoardContinentData(): Deferred<DataHolder<List<ContinentDataResponse>>>
}
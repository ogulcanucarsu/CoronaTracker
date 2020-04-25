package org.ucarsu.coronaexample.dashboard.data

import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import data.modules.adapter.ApiCallAdapter
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import javax.inject.Inject

class DashBoardCountryRemoteDataSource @Inject constructor(
    private val dashBoardServices: DashBoardServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<List<CountriesData>> {
    override suspend fun fetch(): DataHolder<List<CountriesData>> {
        val callAdapter = ApiCallAdapter<List<CountriesData>>(errorFactory)
        return callAdapter.adapt(dashBoardServices.getDashBoardCountriesData())
    }
}
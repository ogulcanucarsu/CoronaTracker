package org.ucarsu.coronaexample.dashboard.data

import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import data.modules.adapter.ApiCallAdapter
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
import javax.inject.Inject

class GetDashBoardCountryRemoteDataSource @Inject constructor(
    private val dashBoardServices: DashBoardServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<List<CountriesDataResponse>> {
    override suspend fun fetch(): DataHolder<List<CountriesDataResponse>> {
        val callAdapter = ApiCallAdapter<List<CountriesDataResponse>>(errorFactory)
        return callAdapter.adapt(dashBoardServices.getDashBoardCountriesData())
    }
}
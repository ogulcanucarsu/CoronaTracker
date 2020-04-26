package org.ucarsu.coronaexample.dashboard.data

import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import data.modules.adapter.ApiCallAdapter
import org.ucarsu.coronaexample.dashboard.domain.ContinentData
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import javax.inject.Inject

class DashBoardContinentRemoteDataSource  @Inject constructor(
    private val dashBoardServices: DashBoardServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<List<ContinentData>> {
    override suspend fun fetch(): DataHolder<List<ContinentData>> {
        val callAdapter = ApiCallAdapter<List<ContinentData>>(errorFactory)
        return callAdapter.adapt(dashBoardServices.getDashBoardContinentData())
    }
}
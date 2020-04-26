package org.ucarsu.coronaexample.dashboard.data

import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import data.modules.adapter.ApiCallAdapter
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import javax.inject.Inject

class GetDashBoardContinentRemoteDataSource  @Inject constructor(
    private val dashBoardServices: DashBoardServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<List<ContinentDataResponse>> {
    override suspend fun fetch(): DataHolder<List<ContinentDataResponse>> {
        val callAdapter = ApiCallAdapter<List<ContinentDataResponse>>(errorFactory)
        return callAdapter.adapt(dashBoardServices.getDashBoardContinentData())
    }
}
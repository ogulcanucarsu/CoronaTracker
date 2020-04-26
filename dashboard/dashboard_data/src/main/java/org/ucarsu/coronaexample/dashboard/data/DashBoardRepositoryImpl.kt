package org.ucarsu.coronaexample.dashboard.data

import data.datasource.BaseRepositoryImpl
import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import kotlinx.coroutines.Deferred
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
import org.ucarsu.coronaexample.dashboard.domain.DashBoardRepository
import javax.inject.Inject

class DashBoardRepositoryImpl @Inject constructor(
    private val errorFactory: ErrorFactory,
    private val dashBoardCountryRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<CountriesDataResponse>>,
    private val dashBoardContinentRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<ContinentDataResponse>>
) : BaseRepositoryImpl(), DashBoardRepository {
    override suspend fun getDashBoardCountryData(): Deferred<DataHolder<List<CountriesDataResponse>>> = handleAsync {
        dashBoardCountryRemoteDataSource.fetch()
    }

    override suspend fun getDashBoardContinentData(): Deferred<DataHolder<List<ContinentDataResponse>>> =
        handleAsync {
            dashBoardContinentRemoteDataSource.fetch()
        }
}
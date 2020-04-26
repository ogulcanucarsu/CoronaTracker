package org.ucarsu.coronaexample.dashboard.data

import data.datasource.BaseRepositoryImpl
import data.datasource.DataHolder
import data.datasource.DataSource
import data.error.ErrorFactory
import kotlinx.coroutines.Deferred
import org.ucarsu.coronaexample.dashboard.domain.ContinentData
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import org.ucarsu.coronaexample.dashboard.domain.DashBoardRepository
import javax.inject.Inject

class DashBoardRepositoryImpl @Inject constructor(
    private val errorFactory: ErrorFactory,
    private val dashBoardCountryRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<CountriesData>>,
    private val dashBoardContinentRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<ContinentData>>
) : BaseRepositoryImpl(), DashBoardRepository {
    override suspend fun getDashBoardCountryData(): Deferred<DataHolder<List<CountriesData>>> = handleAsync {
        dashBoardCountryRemoteDataSource.fetch()
    }

    override suspend fun getDashBoardContinentData(): Deferred<DataHolder<List<ContinentData>>> =
        handleAsync {
            dashBoardContinentRemoteDataSource.fetch()
        }
}
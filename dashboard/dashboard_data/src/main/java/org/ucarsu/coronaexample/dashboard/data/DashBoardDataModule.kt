package org.ucarsu.coronaexample.dashboard.data

import dagger.Module
import dagger.Provides
import data.datasource.DataSource
import data.error.ErrorFactory
import org.ucarsu.coronaexample.dashboard.domain.ContinentData
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import org.ucarsu.coronaexample.dashboard.domain.DashBoardRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DashBoardDataModule {
    @Provides
    @Singleton
    fun provideDashBoardServices(retrofit: Retrofit): DashBoardServices =
        retrofit.create(DashBoardServices::class.java)

    @Provides
    fun provideDashBoardCountryRemoteDataSource(
        dashBoardServices: DashBoardServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.FetchDataSource<List<CountriesData>> =
        DashBoardCountryRemoteDataSource(
            dashBoardServices,
            errorFactory
        )

    @Provides
    fun provideDashBoardContinentRemoteDataSource(
        dashBoardServices: DashBoardServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.FetchDataSource<List<ContinentData>> =
        DashBoardContinentRemoteDataSource(
            dashBoardServices,
            errorFactory
        )

    @Singleton
    @Provides
    fun provideDashBoardRepository(
        dashBoardCountryRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<CountriesData>>,
        dashBoardContinentRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<ContinentData>>,
        errorFactory: ErrorFactory
    ): DashBoardRepository = DashBoardRepositoryImpl(
        errorFactory,
        dashBoardCountryRemoteDataSource,
        dashBoardContinentRemoteDataSource
    )
}
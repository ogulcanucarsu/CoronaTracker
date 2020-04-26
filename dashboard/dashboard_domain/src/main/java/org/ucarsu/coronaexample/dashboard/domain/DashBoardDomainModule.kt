package org.ucarsu.coronaexample.dashboard.domain

import dagger.Module
import dagger.Provides
import domain.Interactor

@Module
class DashBoardDomainModule {
    @Provides
    fun provideGetDashBoardCountryInteractor(
        dashBoardRepository: DashBoardRepository
    ): Interactor.DeferredRetrieveInteractor<List<CountriesDataResponse>> =
        GetDashBoardCountryInteractor(
            dashBoardRepository
        )

    @Provides
    fun provideGetDashBoardContinentInteractor(
        dashBoardRepository: DashBoardRepository
    ): Interactor.DeferredRetrieveInteractor<List<ContinentDataResponse>> =
        GetDashBoardContinentInteractor(
            dashBoardRepository
        )
}
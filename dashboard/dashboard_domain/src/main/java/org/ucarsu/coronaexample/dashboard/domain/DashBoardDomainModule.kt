package org.ucarsu.coronaexample.dashboard.domain

import dagger.Module
import dagger.Provides
import domain.Interactor

@Module
class DashBoardDomainModule {
    @Provides
    fun provideGetDashBoardCountryInteractor(
        dashBoardRepository: DashBoardRepository
    ): Interactor.DeferredRetrieveInteractor<List<CountriesData>> =
        GetDashBoardCountryInteractor(
            dashBoardRepository
        )

    @Provides
    fun provideGetDashBoardContinentInteractor(
        dashBoardRepository: DashBoardRepository
    ): Interactor.DeferredRetrieveInteractor<List<ContinentData>> =
        GetDashBoardContinentInteractor(
            dashBoardRepository
        )
}
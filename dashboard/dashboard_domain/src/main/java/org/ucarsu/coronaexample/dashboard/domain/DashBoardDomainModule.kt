package org.ucarsu.coronaexample.dashboard.domain

import dagger.Module
import dagger.Provides
import domain.Interactor

@Module
class DashBoardDomainModule {
    @Provides
    fun provideGetDashBoardInteractor(
        dashBoardRepository: DashBoardRepository
    ): Interactor.DeferredRetrieveInteractor<List<CountriesData>> =
        GetDashBoardCountryInteractor(
            dashBoardRepository
        )
}
package org.ucarsu.coronaexample.dashboard.domain

import data.datasource.DataHolder
import domain.BaseInteractor
import domain.Interactor
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class GetDashBoardCountryInteractor @Inject constructor(
    private val dashBoardRepository: DashBoardRepository
) : BaseInteractor(), Interactor.DeferredRetrieveInteractor<List<CountriesData>> {
    override suspend fun execute(): Deferred<DataHolder<List<CountriesData>>> = handleAsync {
        dashBoardRepository.getDashBoardCountryData().await()
    }
}
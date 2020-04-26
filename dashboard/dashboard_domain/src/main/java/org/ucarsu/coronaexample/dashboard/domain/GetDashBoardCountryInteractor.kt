package org.ucarsu.coronaexample.dashboard.domain

import data.datasource.DataHolder
import domain.BaseInteractor
import domain.Interactor
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class GetDashBoardCountryInteractor @Inject constructor(
    private val dashBoardRepository: DashBoardRepository
) : BaseInteractor(), Interactor.DeferredRetrieveInteractor<List<CountriesDataResponse>> {
    override suspend fun execute(): Deferred<DataHolder<List<CountriesDataResponse>>> = handleAsync {
        dashBoardRepository.getDashBoardCountryData().await()
    }
}
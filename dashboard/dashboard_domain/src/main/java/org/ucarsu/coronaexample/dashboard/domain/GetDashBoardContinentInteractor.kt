package org.ucarsu.coronaexample.dashboard.domain

import data.datasource.DataHolder
import domain.BaseInteractor
import domain.Interactor
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class GetDashBoardContinentInteractor @Inject constructor(
    private val dashBoardRepository: DashBoardRepository
) : BaseInteractor(), Interactor.DeferredRetrieveInteractor<List<ContinentData>> {
    override suspend fun execute(): Deferred<DataHolder<List<ContinentData>>> = handleAsync {
        dashBoardRepository.getContinentData().await()
    }
}
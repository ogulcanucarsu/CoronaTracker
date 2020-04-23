package domain

import data.datasource.DataHolder
import kotlinx.coroutines.Deferred

interface Interactor {
    interface DeferredInteractor<params: Params, T : Any> : Interactor {
        suspend fun execute(postParams: params): Deferred<DataHolder<T>>
    }

    interface DeferredRetrieveInteractor<T : Any> : Interactor {
        suspend fun execute(): Deferred<DataHolder<T>>
    }

    abstract class Params {
        // marker
    }
}
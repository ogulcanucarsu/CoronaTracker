package data.datasource

import androidx.annotation.CallSuper
import data.coroutines.AsyncManager
import data.coroutines.DefaultAsyncManager

abstract class BaseRepositoryImpl(protected val asyncManager: AsyncManager = DefaultAsyncManager()) : BaseRepository,
    AsyncManager by asyncManager {

    @CallSuper
    override fun dropRepo() {
        asyncManager.destroy()
    }
}
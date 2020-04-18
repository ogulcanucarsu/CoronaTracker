package datasource

import androidx.annotation.CallSuper
import coroutines.AsyncManager
import coroutines.DefaultAsyncManager

abstract class BaseRepositoryImpl(protected val asyncManager: AsyncManager = DefaultAsyncManager()) : BaseRepository,
    AsyncManager by asyncManager {

    @CallSuper
    override fun dropRepo() {
        asyncManager.destroy()
    }
}
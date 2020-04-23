package domain

import data.coroutines.AsyncManager
import data.coroutines.DefaultAsyncManager

abstract class BaseInteractor constructor(
    private val asyncManager: AsyncManager = DefaultAsyncManager()
) : AsyncManager by asyncManager{
     open fun destroyInteractor() {
         asyncManager.destroy()
     }
}
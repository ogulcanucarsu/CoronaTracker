package presentation.base

import data.error.Error

interface BaseUi {
    fun showBlockingPane()

    fun hideBlockingPane()

    fun onError(e: Error)
}
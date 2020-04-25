package presentation.base

interface BaseUi {
    fun showBlockingPane()

    fun hideBlockingPane()

    fun onError(e: Error)
}
package preconditions

interface AndroidPreConditions {
    fun assertMainThread()

    fun assertUiThread()

    fun assertWorkerThread()
}
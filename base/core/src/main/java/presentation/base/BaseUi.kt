package presentation.base

import presentation.navigation.NavigationController


interface BaseUi {
    fun provideNavigationController(): NavigationController

    fun showBlockingPane()

    fun hideBlockingPane()
}
package presentation.base

import navigation.navigations.NavigationController


interface BaseUi {
    fun provideNavigationController(): NavigationController

    fun showBlockingPane()

    fun hideBlockingPane()
}
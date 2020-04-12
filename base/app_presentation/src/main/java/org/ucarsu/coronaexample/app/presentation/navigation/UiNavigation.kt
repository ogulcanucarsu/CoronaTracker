package org.ucarsu.coronaexample.app.presentation.navigation

/*
 * Defines navigation logic of a screen
 */
enum class UiNavigation {
    /*
     * Does not have any navigation logic
     * Does not touch any navigation operation and uses the default navigation logic
     */
    NONE,

    /*
     * Back navigation logic
     * Puts a back button to the top bar
     */
    BACK,

    /*
     * Root navigation logic
     * Defines the screen acts as a root screen
     */
    ROOT
}

package org.ucarsu.coronaexample.dashboard.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import data.inject.scope.FragmentScope

@Module
abstract class DashBoardFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideDashBoardFragment(): DashBoardFragment
}
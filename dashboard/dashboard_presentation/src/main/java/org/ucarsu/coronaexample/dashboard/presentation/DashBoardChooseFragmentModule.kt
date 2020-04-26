package org.ucarsu.coronaexample.dashboard.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import data.inject.scope.FragmentScope

@Module
abstract class DashBoardChooseFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideDashBoardChooseFragment(): DashBoardChooseFragment
}
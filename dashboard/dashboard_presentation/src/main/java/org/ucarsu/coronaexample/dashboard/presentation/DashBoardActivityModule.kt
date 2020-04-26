package org.ucarsu.coronaexample.dashboard.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import data.inject.scope.ActivityScope

@Module
abstract class DashBoardActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [DashBoardFragmentModule::class, DashBoardChooseFragmentModule::class]
    )
    abstract fun provideDashBoardActivity(): DashBoardActivity
}
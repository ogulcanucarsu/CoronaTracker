package org.ucarsu.coronaexample.dashboard.presentation

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import presentation.viewmodel.ViewModelKey

@Module
abstract class DashBoardViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    abstract fun bindDashBoardViewModel(dashBoardViewModel: DashBoardViewModel): ViewModel

}
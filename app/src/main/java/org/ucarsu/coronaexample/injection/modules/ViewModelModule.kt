package org.ucarsu.coronaexample.injection.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import org.ucarsu.coronaexample.dashboard.presentation.DashBoardViewModelModule
import presentation.viewmodel.VmFactory

@Module(
    includes = [
        DashBoardViewModelModule::class
    ]
)
internal abstract class ViewModelModule  {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory) : ViewModelProvider.Factory
}
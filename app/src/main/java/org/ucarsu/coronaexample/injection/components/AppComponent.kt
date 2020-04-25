package org.ucarsu.coronaexample.injection.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import org.ucarsu.coronaexample.CoronaTrackerApp
import org.ucarsu.coronaexample.injection.modules.*
import org.ucarsu.coronaexample.injection.modules.DataModule
import org.ucarsu.coronaexample.injection.modules.DomainModule
import org.ucarsu.coronaexample.injection.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DomainModule::class,
        DataModule::class,
        ApplicationModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: CoronaTrackerApp): AppComponent
    }

    fun inject(application: CoronaTrackerApp)
}
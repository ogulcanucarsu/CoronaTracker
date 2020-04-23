package org.ucarsu.coronaexample.injection.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import org.ucarsu.coronaexample.CoronaTrackerApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CoronaTrackerApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: CoronaTrackerApp)
}
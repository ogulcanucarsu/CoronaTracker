package org.ucarsu.coronaexample.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import data.inject.modules.CoreModule
import org.ucarsu.coronaexample.CoronaTrackerApp

@Module(
    includes = [CoreModule::class]
)
class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: CoronaTrackerApp): Context{
        return app
    }
}
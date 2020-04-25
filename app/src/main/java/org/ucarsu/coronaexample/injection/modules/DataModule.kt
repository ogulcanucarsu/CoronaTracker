package org.ucarsu.coronaexample.injection.modules

import dagger.Module
import dagger.Provides
import data.modules.interceptor.DefaultRequestInterceptor
import data.modules.module.AppDataModule
import okhttp3.Interceptor
import org.ucarsu.coronaexample.dashboard.data.DashBoardDataModule
import javax.inject.Singleton

@Module(
    includes = [
        AppDataModule::class,
    DashBoardDataModule::class
    ]
)
internal class DataModule {
    @Provides
    @Singleton
    fun provideDefaultRequestInterceptor(): Interceptor = DefaultRequestInterceptor()
}
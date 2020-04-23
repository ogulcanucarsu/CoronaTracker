package data.modules.module

import dagger.Module
import dagger.Provides
import data.error.ErrorFactory
import kotlinx.coroutines.Deferred
import data.modules.adapter.CallAdapter
import data.modules.adapter.ApiCallAdapter
import data.modules.api.ApiResponse
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class CoreDataModule {
    @Singleton
    @Provides
    fun provideApiCallAdapter(errorFactory: ErrorFactory): CallAdapter<Deferred<ApiResponse<out Any?>>> =
        ApiCallAdapter(errorFactory)
}
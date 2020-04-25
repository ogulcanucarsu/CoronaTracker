package data.modules.module

import dagger.Module
import dagger.Provides
import data.error.ErrorFactory
import kotlinx.coroutines.Deferred
import data.modules.adapter.CallAdapter
import data.modules.adapter.ApiCallAdapter
import data.modules.api.ApiResponse
import data.modules.interceptor.DefaultRequestInterceptor
import domain.constants.ApiConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.ucarsu.coronaexample.core.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoreApiModule {
    @Provides
    @Singleton
    @Named(ApiModule.NAME_URL)
    fun provideBaseUrl(): String = "https://api.collectapi.com/corona/"

    @Provides
    @Singleton
    fun provideRequestInterceptor(): Interceptor =
        DefaultRequestInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        requestInterceptor: DefaultRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(requestInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            connectTimeout(ApiConstants.TIMEOUT_IN_MILIS, TimeUnit.MILLISECONDS)
            build()
        }
}
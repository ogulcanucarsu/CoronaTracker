package data.inject.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import data.error.DefaultErrorFactory
import data.error.ErrorFactory
import javax.inject.Singleton

@Module
class ErrorFactoryModule {

    @Provides
    @Singleton
    internal fun provideErrorFactory(context: Context): ErrorFactory =
        DefaultErrorFactory()
}
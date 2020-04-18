package inject.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import error.DefaultErrorFactory
import error.ErrorFactory
import javax.inject.Singleton

@Module
class ErrorFactoryModule {

    @Provides
    @Singleton
    internal fun provideErrorFactory(context: Context): ErrorFactory =
        DefaultErrorFactory()
}
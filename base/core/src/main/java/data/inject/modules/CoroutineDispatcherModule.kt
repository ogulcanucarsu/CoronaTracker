package data.inject.modules

import data.coroutines.DefaultDispatcherProvider
import data.coroutines.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoroutineDispatcherModule {

    @Provides
    @Singleton
    fun provideDefaultDispatcher(): DispatcherProvider = DefaultDispatcherProvider
}
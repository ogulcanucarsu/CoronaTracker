package inject.modules

import coroutines.DefaultDispatcherProvider
import coroutines.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoroutineDispatcherModule {

    @Provides
    @Singleton
    fun provideDefaultDispatcher(): DispatcherProvider = DefaultDispatcherProvider
}
package inject.modules

import dagger.Module
import dagger.Provides
import preconditions.AndroidPreConditions
import preconditions.DefaultAndroidPreConditions
import javax.inject.Singleton

@Module(
    includes = [CoroutineManagerModule::class,
        CoroutineDispatcherModule::class,
        ErrorFactoryModule::class]
)
class CoreModule {

    @Provides
    @Singleton
    fun provideAndroidPreConditions(): AndroidPreConditions = DefaultAndroidPreConditions()

}
package data.inject.modules

import dagger.Module
import dagger.Provides
import data.preconditions.AndroidPreConditions
import data.preconditions.DefaultAndroidPreConditions
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
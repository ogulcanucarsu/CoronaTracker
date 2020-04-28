package data.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

abstract class CoreApplicationImpl : Application(), CoreApplication {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun inject() {
        // override if needed
    }

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
package org.ucarsu.coronaexample

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.ucarsu.coronaexample.injection.components.DaggerAppComponent
import javax.inject.Inject
class CoronaTrackerApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)
    }
    override fun androidInjector() = dispatchingAndroidInjector
}
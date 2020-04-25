package org.ucarsu.coronaexample.ui

import android.os.Bundle
import android.os.Handler
import presentation.activity.BaseActivity
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import org.ucarsu.coronaexample.R
import java.lang.ref.WeakReference

class SplashActivity : BaseActivity() {

    private lateinit var navigationController: NavigationController

    override fun getLayoutRes(): Int =
        R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController =
            DefaultNavigationController(WeakReference(this))

        Handler().postDelayed({
            navigationController.navigateToDashBoard()
        }, SPLASH_TIME_OUT)
    }

    companion object {
        const val SPLASH_TIME_OUT = 1000L
    }
}

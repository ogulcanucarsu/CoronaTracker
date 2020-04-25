package org.ucarsu.coronaexample.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import org.ucarsu.coronaexample.R
import java.lang.ref.WeakReference

class SplashActivity : AppCompatActivity() {

    private lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.navigationController =
            DefaultNavigationController(WeakReference(this))

        Handler().postDelayed({
            navigationController.navigateToDashBoard()
            navigationController.finish()
        }, SPLASH_TIME_OUT)
    }

    companion object {
        const val SPLASH_TIME_OUT = 1000L
    }
}

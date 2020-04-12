package org.ucarsu.coronaexample.app.presentation

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

class BaseActivity : AppCompatActivity() {

    /*
    * tittleRes can be overridden from sub classes to change title
    * Default uses Application Name and sets as title
    */
    @StringRes
    open val titleRes = R.string.app_name

    /*
     * menuRes can be overridden from sub classes to add or change top menu
     * Default activities has no menu
     */
    @MenuRes
    open val menuRes = Constants.NO_RES

    /*
     * toolBarRes can be overridden from sub classes to provide a toolbar instead of actionbar
     * Default activities don't provide a toolbar.
     */
    @IdRes
    open val toolbarRes = Constants.NO_RES

    /*
     * Can be overridden to change the navigation logic of activity
     * Default activities have back navigation logic and back button.
     */
    open val uiNavigation = UiNavigation.BACK

    /*
     * Id of container for initial fragment
     */
    @IdRes
    open val containerId = R.id.frameLayoutMain

    /*
     * This abstract method requests a layout resource from sub classes
     */
    @LayoutRes
    open val layoutRes = R.layout.activity_base


    /*
     * Receives network changes
     */
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    /*
     * Navigation controller thats controls navigation logic of activity
     */
    //protected lateinit var navigationController: NavigationController

    /*
     * Provides initial fragment
     */
    open fun provideInitialFragment(): Fragment? = null

    /*
     * Holds current network connection state
     */
    private var isNetworkConnected = true

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //navigationController = DefaultNavigationController(WeakReference(this))
        networkChangeReceiver = NetworkChangeReceiver()

        if (layoutRes != Constants.NO_RES) {
            setContentView(layoutRes)
        }

        /*if (toolbarRes.isValidResource()) {
            setToolbar(findViewById(toolbarRes))
        }*/
        initNavigation(uiNavigation)
        setScreenTitle(getString(titleRes))

        /*provideInitialFragment()?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.transact {
                    replace(containerId, it)
                }
            }
        }*/
        initActivity(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        startReceivingConnectionUpdates()
    }

    override fun onPause() {
        stopReceivingConnectionUpdates()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       *//* if (menuRes.isValidResource()) {
            menuInflater.inflate(menuRes, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)*//*
    }*/

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_NETWORK_PERMISSIONS -> {
                // no-op
            }
        }
    }

   /* override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.initializeLocalization(base))
    }*/

    /*override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.initializeLocalization(this)
    }*/

    open fun initActivity(savedInstanceState: Bundle?) {
        // can be overridden to init an activity
    }

    /*
     * Sets screen title wit string resource
     */
    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
        } catch (e: Resources.NotFoundException) {
            // ignored
        }
        setScreenTitle(title)
    }

    /*
     * Sets the given string as screen title
     */
    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title
    }

    /*
     * Sets the given toolbar as toolbar of screen
     */
    fun setToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
    }

    /*
     * Sets the ui navigation with the given navigation type
     */
    fun setNavigation(uiNavigation: UiNavigation) {
        initNavigation(uiNavigation)
    }

    /*
     * Inits or changes the navigation of the screen
     */
    private fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(true)
                    setHomeButtonEnabled(true)
                    setDisplayHomeAsUpEnabled(true)
                }

            }
            UiNavigation.ROOT -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(false)
                    setHomeButtonEnabled(false)
                    setDisplayHomeAsUpEnabled(false)
                }
            }
            UiNavigation.NONE -> {
                // no-op
            }
        }
    }

    private fun startReceivingConnectionUpdates() {
       /* if (DeviceUtil.hasM() && !PermissionUtil.isConnectionPermissionsGranted(this)) {
            ActivityCompat.requestPermissions(
                this, PermissionUtil.connectionPermissions,
                REQUEST_NETWORK_PERMISSIONS
            )
        } else {
            registerReceiver(this.networkChangeReceiver,
                IntentFilter().apply {
                    addAction("android.net.conn.CONNECTIVITY_CHANGE")
                    addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
                })
        }*/
    }

    private fun stopReceivingConnectionUpdates() {
        unregisterReceiver(this.networkChangeReceiver)
    }

    inner class NetworkChangeReceiver : BroadcastReceiver() {

        @SuppressLint("MissingPermission")
        override fun onReceive(p0: Context?, p1: Intent?) {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetwork = connectivityManager?.activeNetworkInfo

            val currentConnectionStatus = when (activeNetwork?.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }

            if (currentConnectionStatus != isNetworkConnected) {
                isNetworkConnected = currentConnectionStatus

                /*if (currentConnectionStatus) {
                    frameLayoutMain?.snackThat(
                        meesage = getString(R.string.core_info_message_connected),
                        length = Snackbar.LENGTH_SHORT
                    )
                } else {
                    frameLayoutMain?.snackThat(
                        meesage = getString(R.string.core_error_message_connection),
                        length = Snackbar.LENGTH_SHORT
                    )
                }*/
            }
        }
    }

    companion object {
        private const val REQUEST_NETWORK_PERMISSIONS = 0x001
    }
}

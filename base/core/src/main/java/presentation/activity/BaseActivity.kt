package presentation.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import org.ucarsu.coronaexample.core.R
import presentation.constants.Constants
import presentation.extension.isValidResource
import presentation.extension.transact
import presentation.navigation.DefaultNavigationController
import presentation.navigation.NavigationController
import presentation.navigation.UiNavigation
import java.lang.ref.WeakReference

class BaseActivity : AppCompatActivity() {

    /*
    * tittleRes can be overridden from sub classes to change title
    * Default uses Application Name and sets as title
    */
    @StringRes
    open val titleRes =
        R.string.app_name

    /*
     * menuRes can be overridden from sub classes to add or change top menu
     * Default activities has no menu
     */
    @MenuRes
    open val menuRes =
        Constants.NO_RES

    /*
     * toolBarRes can be overridden from sub classes to provide a toolbar instead of actionbar
     * Default activities don't provide a toolbar.
     */
    @IdRes
    open val toolbarRes =
        Constants.NO_RES

    /*
     * Can be overridden to change the navigation logic of activity
     * Default activities have back navigation logic and back button.
     */
    open val uiNavigation =
        UiNavigation.BACK

    /*
     * Id of container for initial fragment
     */
    @IdRes
    open val containerId =
        R.id.frameLayoutMain

    /*
     * This abstract method requests a layout resource from sub classes
     */
    @LayoutRes
    open val layoutRes =
        R.layout.activity_base

    /*
     * Navigation controller thats controls navigation logic of activity
     */
    protected lateinit var navigationController: NavigationController

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

        navigationController =
            DefaultNavigationController(
                WeakReference(this)
            )

        if (layoutRes != Constants.NO_RES) {
            setContentView(layoutRes)
        }

        if (toolbarRes.isValidResource()) {
            setToolbar(findViewById(toolbarRes))
        }
        initNavigation(uiNavigation)
        setScreenTitle(getString(titleRes))

        provideInitialFragment()?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.transact {
                    replace(containerId, it)
                }
            }
        }
        initActivity(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menuRes.isValidResource()) {
            menuInflater.inflate(menuRes, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

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

    companion object {
        private const val REQUEST_NETWORK_PERMISSIONS = 1000
    }
}

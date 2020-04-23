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

    @StringRes
    open val titleRes =
        R.string.app_name

    @MenuRes
    open val menuRes =
        Constants.NO_RES

    @IdRes
    open val toolbarRes =
        Constants.NO_RES

    open val uiNavigation =
        UiNavigation.BACK

    @IdRes
    open val containerId =
        R.id.frameLayoutMain

    @LayoutRes
    open val layoutRes =
        R.layout.activity_base

    protected lateinit var navigationController: NavigationController

    open fun provideInitialFragment(): Fragment? = null

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

    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
        } catch (e: Resources.NotFoundException) {
            // ignored
        }
        setScreenTitle(title)
    }

    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun setToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
    }

    fun setNavigation(uiNavigation: UiNavigation) {
        initNavigation(uiNavigation)
    }

    private fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(true)
                }
            }
            UiNavigation.ROOT -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(false)
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

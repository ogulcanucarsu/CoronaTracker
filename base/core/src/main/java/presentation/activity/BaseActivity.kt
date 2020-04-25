package presentation.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import org.ucarsu.coronaexample.core.R
import presentation.constants.Constants
import navigation.navigations.UiNavigation
import presentation.extension.isValidResource
import presentation.extension.transact
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity() {

    @StringRes
    open val titleRes = R.string.app_name

    @MenuRes
    open val menuRes = Constants.NO_RES

    @IdRes
    open val toolbarRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.BACK

    @IdRes
    open val containerId = R.id.frameLayoutMain

    @LayoutRes
    open val layoutRes = R.layout.activity_base

    open fun provideInitialFragment(): Fragment? = null

    protected lateinit var navigationController: NavigationController

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationController = DefaultNavigationController(WeakReference(this))

        if (layoutRes != Constants.NO_RES) {
            setContentView(layoutRes)
        }

        if (toolbarRes.isValidResource()) {
            setToolbar(findViewById(toolbarRes))
        }
        initNavigation(uiNavigation)
        setScreenTitle(titleRes)

        provideInitialFragment()?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.transact {
                    replace(containerId, it)
                }
            }
        }
        initActivity(savedInstanceState)
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

    open fun initActivity(savedInstanceState: Bundle?) {
        // can be overridden to init an activity
    }

    fun setToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
    }

    fun setNavigation(uiNavigation: UiNavigation) {
        initNavigation(uiNavigation)
    }

    fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
            supportActionBar?.title = title
        } catch (e: Resources.NotFoundException) {
            // ignored
        }
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
                }
            }
            UiNavigation.NONE -> {
                // no-op
            }
        }
    }
}

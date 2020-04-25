package presentation.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import org.ucarsu.coronaexample.core.R
import presentation.constants.Constants
import navigation.navigations.UiNavigation
import presentation.extension.isValidResource
import presentation.extension.transact
import presentation.viewmodel.VmFactory
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

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

    protected lateinit var navigationController: NavigationController

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: VmFactory

    open fun provideInitialFragment(): Fragment? = null

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
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

    open fun onInject() {
        //empty for override
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

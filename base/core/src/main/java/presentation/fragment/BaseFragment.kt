package presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import presentation.activity.BaseActivity
import presentation.base.BaseUi
import presentation.constants.Constants
import navigation.navigations.UiNavigation
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : Fragment(), BaseUi {

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    private lateinit var navigationController: NavigationController

    abstract fun getModelClass(): Class<VM>


    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @MenuRes
    open val menuRes = Constants.NO_RES

    @StringRes
    open val titleRes = Constants.NO_RES

    @IdRes
    open val toolbarId = Constants.NO_RES

    open val uiNavigation = UiNavigation.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes != Constants.NO_RES)
        this.navigationController = DefaultNavigationController(WeakReference(activity!!))
        viewModel = ViewModelProviders.of(this, vmFactory).get(getModelClass())
    }

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        onInject()
        super.onAttach(context)
    }

    open fun onInject() {
        // empty for override
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menuRes != Constants.NO_RES) {
            menu.clear()
            inflater.inflate(menuRes, menu)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolBar()
        if (titleRes != Constants.NO_RES) {
            setActivityTitle(getString(titleRes))
        }
        if (uiNavigation != UiNavigation.NONE) {
            setupNavigation()
        }
    }

    protected fun setActivityTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    fun getApplication() = activity?.application

    fun getApplicationContext() = getApplication()?.applicationContext

    open fun errorDialogButtonClicked() {
        // can be overridden
    }

    open fun initView() {
        // can be overridden
    }

    private fun initToolBar() {
        if (toolbarId == Constants.NO_RES) return
        view?.findViewById<Toolbar>(toolbarId)?.let { toolbar ->
            if (activity is BaseActivity) {
                (activity as BaseActivity).setToolbar(toolbar)
            }
        }
    }

    private fun setupNavigation() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).initNavigation(uiNavigation)
        }
    }
}
package presentation.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import data.error.Error
import data.error.ErrorFactory
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import presentation.activity.BaseActivity
import presentation.base.BaseUi
import presentation.constants.Constants
import navigation.navigations.UiNavigation
import presentation.extension.createBlockingPane
import presentation.extension.isValidResource
import presentation.viewmodel.BaseViewModel
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : Fragment(), BaseUi {

    @StringRes
    open val titleRes = Constants.NO_RES

    @MenuRes
    open val menuRes = Constants.NO_RES

    @IdRes
    open val toolbarRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.NONE

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    private var blockingPane: Dialog? = null

    private var blockingOperationCount = 0

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    protected lateinit var navigationController: NavigationController

    @Inject
    lateinit var errorFactory: ErrorFactory

    abstract fun getModelClass(): Class<VM>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes.isValidResource())
        navigationController = DefaultNavigationController(WeakReference(activity!!))
        viewModel = ViewModelProviders.of(this, vmFactory).get(getModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (getLayoutRes() != Constants.NO_RES) {
            return inflater.inflate(getLayoutRes(), null, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel is BaseViewModel) {
            (viewModel as BaseViewModel).errorLiveData.observe(this, Observer {
                onError(it.e)
            })
        }
        if (toolbarRes.isValidResource()) {
            val toolbar: Toolbar = view.findViewById(toolbarRes)
            (activity as? BaseActivity)?.setToolbar(toolbar)
        }
        if (uiNavigation != UiNavigation.NONE) {
            (activity as? BaseActivity)?.setNavigation(uiNavigation)
        }
        if (titleRes.isValidResource()) {
            (activity as? BaseActivity)?.setScreenTitle(titleRes)
        }
        initFragmentScreen()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        monitorData()
        takeData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(menuRes, menu)
    }

    override fun onDestroyView() {
        blockingPane = null
        super.onDestroyView()
    }

    override fun onError(e: Error) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG)

    }

    override fun showBlockingPane() {
        if (blockingOperationCount == 0) {
            if (blockingPane == null) {
                blockingPane = activity?.createBlockingPane()
            }

            blockingPane?.let {
                if (!it.isShowing) {
                    it.show()
                }
            }
        }
        blockingOperationCount += 1
    }

    override fun hideBlockingPane() {
        blockingOperationCount -= 1
        if (blockingOperationCount == 0) {
            blockingPane?.dismiss()
            blockingPane = null
        }
    }

    @CallSuper
    override fun onAttach(context: Context) {
        if (activity is HasAndroidInjector) {
            AndroidSupportInjection.inject(this)
            onInject()
        }
        super.onAttach(context)
    }

    open fun onInject() {
        // empty for override
    }

    fun getApplicationContext() = activity?.application?.applicationContext

    open fun initFragmentScreen() {
        // can be overridden
    }

    open fun monitorData() {
        // can be overridden
    }

    open fun takeData() {
        // can be overridden
    }
}
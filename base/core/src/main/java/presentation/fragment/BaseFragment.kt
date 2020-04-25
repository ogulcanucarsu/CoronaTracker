package presentation.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import data.error.ErrorFactory
import navigation.navigations.DefaultNavigationController
import navigation.navigations.NavigationController
import presentation.activity.BaseActivity
import presentation.base.BaseUi
import presentation.constants.Constants
import navigation.navigations.UiNavigation
import presentation.extension.createBlockingPane
import presentation.extension.isValidResource
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseFragment : Fragment(), BaseUi {

    @StringRes
    open val titleRes = Constants.NO_RES

    @MenuRes
    open val menuRes = Constants.NO_RES

    @IdRes
    open val toolbarRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.NONE

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected lateinit var navigationController: NavigationController

    private var blockingPane: Dialog? = null

    private var blockingOperationCount = 0

    @Inject
    lateinit var errorFactory: ErrorFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes.isValidResource())
        navigationController = DefaultNavigationController(WeakReference(activity!!))
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
        Toast.makeText(context, e.message, Toast.LENGTH_LONG)

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
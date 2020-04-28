package navigation.navigations

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.ucarsu.coronaexample.core.R
import java.lang.ref.WeakReference

interface NavigationController {
    val activity: WeakReference<FragmentActivity>

    fun close() = activity.get()?.onBackPressed()

    fun finish() = activity.get()?.finish()

    fun navigateToDashBoard()

    fun navigateToFragment(fragment: Fragment, @IdRes containerId: Int = R.id.frameLayoutMain, backStack: String? = null)
}
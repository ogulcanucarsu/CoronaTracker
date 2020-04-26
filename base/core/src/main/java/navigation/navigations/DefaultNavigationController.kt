package navigation.navigations

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import navigation.feature.DashBoard
import org.ucarsu.coronaexample.core.R
import presentation.extension.transact
import java.lang.ref.WeakReference


class DefaultNavigationController(
    override val activity: WeakReference<FragmentActivity>
) : NavigationController {
    override fun navigateToFragment(
        fragment: Fragment,
        @IdRes containerId: Int,
        backStack: String?
    ) {
        activity.get()?.supportFragmentManager?.transact {
            replace(containerId, fragment)
                .addToBackStack(backStack)
        }
    }

    override fun navigateToDashBoard() {
        activity.get()?.startActivity(DashBoard.dynamicStart)
    }
}

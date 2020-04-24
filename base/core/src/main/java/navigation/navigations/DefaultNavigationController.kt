package navigation.navigations

import androidx.fragment.app.FragmentActivity
import navigation.feature.DashBoard
import java.lang.ref.WeakReference


class DefaultNavigationController(override val activity: WeakReference<FragmentActivity>
) : NavigationController {
    override fun navigateToDashBoard() {
        activity.get()?.startActivity(DashBoard.dynamicStart)
    }
}

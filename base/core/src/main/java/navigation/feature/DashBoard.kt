package navigation.feature

import android.content.Intent
import navigation.PACKAGE_NAME
import navigation.loadIntentOrReturnNull

object DashBoard : Feature<Intent> {
    private const val DashBoard = "$PACKAGE_NAME.dashboard.presentation.DashboardActivity"

    override val dynamicStart: Intent?
        get() = DashBoard.loadIntentOrReturnNull()
}
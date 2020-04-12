package org.ucarsu.coronaexample.app.presentation.navigation

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference


class DefaultNavigationController(override val activity: WeakReference<FragmentActivity>) :
    NavigationController

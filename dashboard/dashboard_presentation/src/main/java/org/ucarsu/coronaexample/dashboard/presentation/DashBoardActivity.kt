package org.ucarsu.coronaexample.dashboard.presentation

import presentation.activity.BaseActivity

class DashBoardActivity : BaseActivity() {
    override fun provideInitialFragment() = DashBoardChooseFragment.newInstance()
}
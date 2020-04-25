package org.ucarsu.coronaexample.dashboard.presentation

import presentation.fragment.BaseFragment

class DashBoardFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    companion object {
        fun newInstance() =
            DashBoardFragment()
    }
}
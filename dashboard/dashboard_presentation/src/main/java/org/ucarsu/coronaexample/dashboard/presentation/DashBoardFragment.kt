package org.ucarsu.coronaexample.dashboard.presentation

import presentation.activity.BaseActivity
import presentation.fragment.BaseFragment

class DashBoardFragment : BaseFragment<DashBoardViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    override fun getModelClass(): Class<DashBoardViewModel> = DashBoardViewModel::class.java

    override fun initFragmentScreen() {
        super.initFragmentScreen()
        (activity as BaseActivity).setScreenTitle("DashBoard")
    }

    override fun takeData() {
        super.takeData()
        viewModel.getDashBoardCountry()
    }

    companion object {
        fun newInstance() =
            DashBoardFragment()
    }
}
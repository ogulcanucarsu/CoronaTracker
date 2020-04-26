package org.ucarsu.coronaexample.dashboard.presentation

import kotlinx.android.synthetic.main.fragment_choose_dashboard.*
import presentation.fragment.BaseFragment

class DashBoardChooseFragment : BaseFragment<DashBoardViewModel>() {
    override fun getLayoutRes(): Int = R.layout.fragment_choose_dashboard

    override fun getModelClass(): Class<DashBoardViewModel> = DashBoardViewModel::class.java

    override fun initFragmentScreen() {
        super.initFragmentScreen()
        buttonShowContinent.setOnClickListener {
            navigationController.navigateToFragment(DashBoardFragment.newInstance(contentInfo = 0))
        }
        buttonShowCountry.setOnClickListener {
            navigationController.navigateToFragment(DashBoardFragment.newInstance(contentInfo = 1))
        }
    }

    companion object {
        fun newInstance() =
            DashBoardChooseFragment()
    }
}
package org.ucarsu.coronaexample.dashboard.presentation

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import data.datasource.DataHolder
import kotlinx.android.synthetic.main.fragment_dashboard.*
import presentation.activity.BaseActivity
import presentation.fragment.BaseFragment
import presentation.livedata.observeApi


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

    override fun monitorData() {
        super.monitorData()
        viewModel.getDashBoardCountryLiveData.observeApi(lifecycleOwner = this){
            when(it){
                is DataHolder.Success -> {
                    chartView.setProgressBar(chartProgressbar)
                    val pie = AnyChart.pie()
                    val data: ArrayList<DataEntry> = arrayListOf()
                    for (i in 0..10){
                        data.add((ValueDataEntry(it.data[i].country, it.data[i].activeCases?.replace(",", "")?.toInt())))
                    }
                    pie.data(data)
                    pie.title("Corona Tracker - Top 10 Country")

                    pie.labels().position("outside")
                    pie.legend().title().enabled(true)
                    pie.legend().title()
                        .text("Country")
                        .padding(0.0, 0.0, 10.0, 0.0)

                    pie.legend()
                        .position("center-bottom")
                        .itemsLayout(LegendLayout.HORIZONTAL)
                        .align(Align.CENTER)

                    chartView.setChart(pie)
                }
            }
        }
    }

    companion object {
        fun newInstance() =
            DashBoardFragment()
    }
}
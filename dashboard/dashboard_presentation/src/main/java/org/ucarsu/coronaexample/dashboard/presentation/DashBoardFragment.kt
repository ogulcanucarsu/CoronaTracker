package org.ucarsu.coronaexample.dashboard.presentation

import android.os.Bundle
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

    private var content: Int? = null

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    override fun getModelClass(): Class<DashBoardViewModel> = DashBoardViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getInt(BUNDLE_CONTENT)
    }

    override fun initFragmentScreen() {
        super.initFragmentScreen()
        (activity as BaseActivity).setScreenTitle("DashBoard")
    }

    override fun takeData() {
        super.takeData()
        content?.let {
            when (it) {
                0 -> {
                    viewModel.getDashBoardContinent()
                }

                1 -> {
                    viewModel.getDashBoardCountry()
                }
                else -> {
                    //no-op
                }
            }
        }

    }

    override fun monitorData() {
        super.monitorData()
        viewModel.getDashBoardCountryLiveData.observeApi(lifecycleOwner = this) {
            when (it) {
                is DataHolder.Success -> {
                    val pie = AnyChart.pie()
                    val data: ArrayList<DataEntry> = arrayListOf()
                    for (i in 0..10) {
                        data.add(
                            (ValueDataEntry(
                                it.data[i].country,
                                it.data[i].activeCases?.replace(",", "")?.toInt()
                            ))
                        )
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

                    this.hideBlockingPane()
                    chartView.setChart(pie)
                }
                is DataHolder.Fail -> {
                    this.hideBlockingPane()
                }
                is DataHolder.Loading -> {
                    this.showBlockingPane()
                }
            }
        }
    }

    companion object {
        const val BUNDLE_CONTENT = "bundle.content"
        fun newInstance(contentInfo: Int) = DashBoardFragment().apply {
            val args = Bundle()
            args.putInt(BUNDLE_CONTENT, contentInfo)
            arguments = args
        }
    }
}
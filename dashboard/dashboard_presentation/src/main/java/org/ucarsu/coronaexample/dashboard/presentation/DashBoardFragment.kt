package org.ucarsu.coronaexample.dashboard.presentation

import android.os.Bundle
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.*
import data.datasource.DataHolder
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
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
        (activity as BaseActivity).setScreenTitle(getString(R.string.app_name))
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
                    setCountryTop10Data(it.data)
                }
                is DataHolder.Fail -> {
                    this.hideBlockingPane()
                }
                is DataHolder.Loading -> {
                    this.showBlockingPane()
                }
            }
        }

        viewModel.getDashBoardContinentLiveData.observeApi(lifecycleOwner = this) {
            when (it) {
                is DataHolder.Success -> {
                    setContinentData(it.data)
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

    private fun setCountryTop10Data(countryData: List<CountriesDataResponse>) {
        val pie = AnyChart.pie()
        val data: ArrayList<DataEntry> = arrayListOf()
        for (i in 0..4) {
            data.add(
                (ValueDataEntry(
                    countryData[i].country,
                    countryData[i].activeCases.replace(".","").replace(",", "").toInt()
                ))
            )
        }
        pie.data(data)
        pie.title("Corona Tracker - Top 5 Country")

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

    private fun setContinentData(continentData: List<ContinentDataResponse>) {
        val bar3d = AnyChart.bar3d()
        bar3d.animation(true)
        bar3d.padding(10.0, 40.0, 5.0, 20.0)
        bar3d.title("Total Corona Count - Continents")

        bar3d.yScale().minimum(0.0)

        bar3d.xAxis(0).labels()
            .rotation(-90.0)
            .padding(0.0, 0.0, 20.0, 0.0)

        bar3d.yAxis(0).labels().format("{%Value}{groupsSeparator: }")

        bar3d.yAxis(0).title("Count")

        val data: MutableList<DataEntry> = ArrayList()
        continentData.forEach { continents ->
            data.add(
                CustomDataEntryClass(
                    continents.continent,
                    continents.totalCases?.replace(",", "")?.toInt(),
                    continents.activeCases.replace(",", "").toInt()
                )
            )
        }

        val set = Set.instantiate()
        set.data(data)
        val bar1Data: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val bar2Data = set.mapAs("{ x: 'x', value: 'value2' }")
        bar3d.bar(bar1Data)
            .name("Total Case")
        bar3d.bar(bar2Data)
            .name("Active Case")

        bar3d.legend().enabled(true)
        bar3d.legend().fontSize(13.0)
        bar3d.legend().padding(0.0, 0.0, 20.0, 0.0)

        bar3d.interactivity().hoverMode(HoverMode.SINGLE)

        bar3d.tooltip()
            .positionMode(TooltipPositionMode.POINT)
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(0.0)
            .format("{%Value}")

        bar3d.zAspect("10%")
            .zPadding(20.0)
            .zAngle(45.0)
            .zDistribution(true)

        this.hideBlockingPane()
        chartView.setChart(bar3d)
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
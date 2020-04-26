package org.ucarsu.coronaexample.dashboard.presentation

import com.anychart.chart.common.dataentry.ValueDataEntry

class CustomDataEntryClass internal constructor(
    x: String?,
    value: Number?,
    value2: Number?
) :
    ValueDataEntry(x, value) {
    init {
        setValue("value2", value2)
    }
}
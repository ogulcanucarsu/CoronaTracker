package org.ucarsu.coronaexample.dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import data.datasource.DataHolder
import data.error.ErrorFactory
import domain.Interactor
import org.ucarsu.coronaexample.dashboard.domain.CountriesData
import presentation.extension.handleLaunch
import presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    private val getDashBoardCountryInteractor: Interactor.DeferredRetrieveInteractor<List<CountriesData>>,
    private val errorFactory: ErrorFactory
) : BaseViewModel() {
    private val _getDashBoardCountryLiveData = MutableLiveData<DataHolder<List<CountriesData>>>()
    val getDashBoardCountryLiveData: LiveData<DataHolder<List<CountriesData>>>
        get() = _getDashBoardCountryLiveData

    fun getDashBoardCountry() = handleLaunch(execution = {
        _getDashBoardCountryLiveData.value = DataHolder.Loading()
        when (val result = getDashBoardCountryInteractor.execute().await()) {
            is DataHolder.Success -> {
                _getDashBoardCountryLiveData.value = DataHolder.Success(result.data)
            }
            is DataHolder.Fail -> {
                _getDashBoardCountryLiveData.value = DataHolder.Fail(result.e)
            }
        }
    }, error = {
        _getDashBoardCountryLiveData.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })
}
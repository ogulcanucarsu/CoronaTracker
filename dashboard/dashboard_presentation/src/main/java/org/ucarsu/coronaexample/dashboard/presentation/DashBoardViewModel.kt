package org.ucarsu.coronaexample.dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import data.datasource.DataHolder
import data.error.ErrorFactory
import domain.Interactor
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
import presentation.extension.handleLaunch
import presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    private val getDashBoardCountryInteractor: Interactor.DeferredRetrieveInteractor<List<CountriesDataResponse>>,
    private val getDashBoardContinentInteractor: Interactor.DeferredRetrieveInteractor<List<ContinentDataResponse>>,
    private val errorFactory: ErrorFactory
) : BaseViewModel() {

    private val _getDashBoardCountryLiveData = MutableLiveData<DataHolder<List<CountriesDataResponse>>>()
    val getDashBoardCountryLiveData: LiveData<DataHolder<List<CountriesDataResponse>>>
        get() = _getDashBoardCountryLiveData

    private val _getDashBoardContinentLiveData = MutableLiveData<DataHolder<List<ContinentDataResponse>>>()
    val getDashBoardContinentLiveData: LiveData<DataHolder<List<ContinentDataResponse>>>
        get() = _getDashBoardContinentLiveData

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

    fun getDashBoardContinent() = handleLaunch(execution = {
        _getDashBoardContinentLiveData.value = DataHolder.Loading()
        when (val result = getDashBoardContinentInteractor.execute().await()) {
            is DataHolder.Success -> {
                _getDashBoardContinentLiveData.value = DataHolder.Success(result.data)
            }
            is DataHolder.Fail -> {
                _getDashBoardContinentLiveData.value = DataHolder.Fail(result.e)
            }
        }
    }, error = {
        _getDashBoardContinentLiveData.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })
}
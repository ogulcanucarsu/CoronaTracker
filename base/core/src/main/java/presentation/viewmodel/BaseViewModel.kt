package presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import data.datasource.DataHolder
import presentation.livedata.SingleLiveData


/*
 * Base ViewModel class
 * All View Models should be extended from BaseViewModel
 */
abstract class BaseViewModel : ViewModel() {
    /*
     * Generic error class to show errors only ones
     */
    private val _errorLiveData = SingleLiveData<DataHolder.Fail>()

    val errorLiveData: LiveData<DataHolder.Fail>
        get() = _errorLiveData
}

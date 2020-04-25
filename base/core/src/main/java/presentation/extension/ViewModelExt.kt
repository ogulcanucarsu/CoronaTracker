package presentation.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.handleLaunch(
    execution: suspend CoroutineScope.() -> Unit,
    error: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
) {
    this.viewModelScope.launch {
        launch {
            try {
                execution()
            } catch (e: Throwable) {
                error(e)
            } finally {
                finallyBlock?.invoke(this)
            }
        }
    }
}
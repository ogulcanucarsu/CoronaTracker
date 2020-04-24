package presentation.base

import data.error.Error

interface BaseView {
    fun onError(e: Error)
}
package com.beettechnologies.agreena.common.presentation.model

sealed class UiStateModel {
    data object Loading : UiStateModel()
    data class Success(val value: Any) : UiStateModel()
    data class Error(val errorMessage: String, val value: Any? = null) : UiStateModel()
}

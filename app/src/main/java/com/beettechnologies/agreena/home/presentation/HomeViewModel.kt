package com.beettechnologies.agreena.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.agreena.common.data.model.Status
import com.beettechnologies.agreena.common.presentation.model.UiStateModel
import com.beettechnologies.agreena.home.domain.interactor.GetCertificatesUseCase
import com.beettechnologies.agreena.home.domain.interactor.UpdateFavoriteUseCase
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val certificatesUseCase: GetCertificatesUseCase,
                                        private val favoriteUseCase: UpdateFavoriteUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiStateModel> = MutableStateFlow(UiStateModel.Loading)
    val uiState: StateFlow<UiStateModel> = _uiState

    fun getCertificates() {
        viewModelScope.launch {
            certificatesUseCase(Unit).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        _uiState.value = UiStateModel.Success(it.data as List<CertificateModel>)
                    }

                    Status.ERROR -> {
                        _uiState.value = UiStateModel.Error(it.message ?: "Oop! Something went wrong!", value = it.data)
                    }

                    Status.LOADING -> {
                        _uiState.value = UiStateModel.Loading
                    }
                }
            }
        }
    }

    fun updateFavorite(id: String, value: Boolean) {
        viewModelScope.launch {
            favoriteUseCase(UpdateFavoriteUseCase.Params(id, value)).collectLatest {
                _uiState.value = UiStateModel.Success(it)
            }
        }
    }
}

package com.beettechnologies.agreena.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.beettechnologies.agreena.app.navigation.Navigation
import com.beettechnologies.agreena.app.navigation.NavigationPreviewParameterProvider
import com.beettechnologies.agreena.common.presentation.LoadingView
import com.beettechnologies.agreena.common.presentation.OnLifecycleEvent
import com.beettechnologies.agreena.common.presentation.ScreenView
import com.beettechnologies.agreena.common.presentation.model.UiStateModel
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import com.beettechnologies.agreena.home.presentation.composables.CertificateListView
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
@Preview
fun HomeView(@PreviewParameter(NavigationPreviewParameterProvider::class) navigation: Navigation) {

    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    ScreenView(title = "Certificates", snackBarHostState = snackBarHostState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        ) {
            if (state is UiStateModel.Loading) {
                LoadingView(Modifier.align(Alignment.Center))
            }

            if (state is UiStateModel.Success) {
                val certificates = (state as UiStateModel.Success).value as List<CertificateModel>
                CertificateListView(certificates, favoriteAction = {id, value ->
                    viewModel.updateFavorite(id, value)
                })
            }

            if (state is UiStateModel.Error) {
                val certificates = (state as UiStateModel.Error).value as? List<CertificateModel> ?: emptyList()
                CertificateListView(certificates, favoriteAction = {id, value ->
                    viewModel.updateFavorite(id, value)
                })
                scope.launch {
                    snackBarHostState.showSnackbar((state as UiStateModel.Error).errorMessage)
                }
            }
        }
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.getCertificates()
            }
            else -> {}
        }
    }
}

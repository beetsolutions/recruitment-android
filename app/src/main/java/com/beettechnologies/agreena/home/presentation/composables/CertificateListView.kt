package com.beettechnologies.agreena.home.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beettechnologies.agreena.home.domain.model.CertificateModel

@Composable
fun CertificateListView(list: List<CertificateModel> = emptyList(), favoriteAction: (id: String, value: Boolean) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { model ->
            CertificateItemView(model, favoriteAction)
        }
    }
}

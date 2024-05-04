package com.beettechnologies.agreena.home.domain.repository

import com.beettechnologies.agreena.common.data.model.Resource
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import kotlinx.coroutines.flow.Flow

interface CertificateRepository {
    suspend fun getCategories(): Flow<Resource<List<CertificateModel>>>

    suspend fun updateFavorite(id: String, value: Boolean) : Flow<List<CertificateModel>>
}

package com.beettechnologies.agreena.home.domain.interactor

import com.beettechnologies.agreena.common.interactor.BaseUseCase
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import com.beettechnologies.agreena.home.domain.repository.CertificateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val repository: CertificateRepository) :
    BaseUseCase<UpdateFavoriteUseCase.Params, Flow<List<CertificateModel>>>() {

    data class Params(val id: String, val value: Boolean)

    override suspend fun buildUseCase(params: Params): Flow<List<CertificateModel>> {
        return repository.updateFavorite(params.id, params.value)
    }
}

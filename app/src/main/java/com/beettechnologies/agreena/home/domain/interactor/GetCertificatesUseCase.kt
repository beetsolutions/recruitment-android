package com.beettechnologies.agreena.home.domain.interactor

import com.beettechnologies.agreena.common.data.model.Resource
import com.beettechnologies.agreena.common.interactor.BaseUseCase
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import com.beettechnologies.agreena.home.domain.repository.CertificateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCertificatesUseCase @Inject constructor(private val repository: CertificateRepository) :
    BaseUseCase<Unit, Flow<Resource<List<CertificateModel>>>>() {

    override suspend fun buildUseCase(params: Unit): Flow<Resource<List<CertificateModel>>> {
        return repository.getCategories()
    }
}

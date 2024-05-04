package com.beettechnologies.agreena.home.data.mapper

import com.beettechnologies.agreena.home.data.local.entity.CertificateEntity
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CertificateMapper @Inject constructor() {

    fun map(response: List<CertificateEntity>): List<CertificateModel> {
        return response.map {
            CertificateModel(
                id = it.id,
                owner = it.owner,
                originator = it.originator,
                status = it.status,
                isFavorite = it.isFavorite
            )
        }
    }
}

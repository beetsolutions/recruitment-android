package com.beettechnologies.agreena.home.domain.model

data class CertificateModel(
    val id: String,
    val originator: String,
    val owner: String,
    val status: String,
    val isFavorite: Boolean
)

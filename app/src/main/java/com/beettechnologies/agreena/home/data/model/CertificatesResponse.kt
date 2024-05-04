package com.beettechnologies.agreena.home.data.model

import com.google.gson.annotations.SerializedName

data class CertificateResponse(
    val id: String,
    val originator: String,
    @SerializedName("originator-country")
    val originatorCountry: String,
    val owner: String,
    @SerializedName("owner-country")
    val ownerCountry: String,
    val status: String,
)

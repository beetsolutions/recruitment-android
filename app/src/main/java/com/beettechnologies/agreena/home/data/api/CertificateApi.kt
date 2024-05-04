package com.beettechnologies.agreena.home.data.api

import com.beettechnologies.agreena.home.data.model.CertificateResponse
import retrofit2.Response
import retrofit2.http.GET

interface CertificateApi {

    @GET("/tech-test/certificates?limit=20&page=1")
    suspend fun certificates(): Response<List<CertificateResponse>>
}

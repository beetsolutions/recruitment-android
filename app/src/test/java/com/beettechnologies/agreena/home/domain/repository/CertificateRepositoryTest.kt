package com.beettechnologies.agreena.home.domain.repository

import com.beettechnologies.agreena.common.data.model.Resource
import com.beettechnologies.agreena.common.data.model.Status
import com.beettechnologies.agreena.home.data.api.CertificateApi
import com.beettechnologies.agreena.home.data.local.dao.CertificateDao
import com.beettechnologies.agreena.home.data.mapper.CertificateMapper
import com.beettechnologies.agreena.home.data.model.CertificateResponse
import com.beettechnologies.agreena.home.data.repository.CertificateRepositoryImpl
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

class CertificateRepositoryTest {

    private val certificateApi = mockk<CertificateApi>()
    private val dao = mockk<CertificateDao>()
    private val sut: CertificateRepository = CertificateRepositoryImpl(certificateApi, dao, CertificateMapper())

    @Test
    fun `get certificates , server error, return Error`() = runTest {

        val response = "{\n" +
                "  \"detail\": \"Oops! Something happened.\"\n" +
                "}"

        coEvery {
            certificateApi.certificates()
        } returns Response.error(402, response.toResponseBody("application/json".toMediaTypeOrNull()))

        val actual = mutableListOf<Resource<List<CertificateModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "Oops! Something happened.")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }

    @Test
    fun `get certificates with no internet , return Error`() = runTest {

        coEvery { certificateApi.certificates() } throws UnknownHostException()

        val actual = mutableListOf<Resource<List<CertificateModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "No internet connection available!")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }

    @Test
    fun `get certificates , return Success`() = runTest {

        val response = listOf(
                CertificateResponse(
                    "dummy-id",
                    "dummy-originator",
                    "dummy-originator-country",
                    "dummy-owner",
                    "dummy-owner-country",
                    "dummy-status"
                )
        )

        coEvery { certificateApi.certificates() } returns Response.success(response)

        val actual = mutableListOf<Resource<List<CertificateModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, null)
        assertEquals(actual[1].status, Status.SUCCESS)
        assertEquals(actual[1].data?.get(0)?.id, "dummy-id")
        assertEquals(actual[1].data?.get(0)?.originator, "dummy-originator")
        assertEquals(actual[1].data?.get(0)?.owner, "dummy-owner")
        assertEquals(actual[1].data?.get(0)?.status, "dummy-status")
    }
}

package com.beettechnologies.agreena.home.domain.mapper

import com.beettechnologies.agreena.home.data.local.entity.CertificateEntity
import com.beettechnologies.agreena.home.data.mapper.CertificateMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CertificateMapperTest {

    private val sut = CertificateMapper()

    @Test
    fun `map response , from responses, return list of CertificateModel`() {

        val response = CertificateEntity(
                    "dummy-id",
                    "dummy-originator",
                    "dummy-owner",
                    "dummy-status",
                    false
                )

        val responses = listOf(response)

        val models = sut.map(responses)

        assertEquals(models[0].id, "dummy-id")
        assertEquals(models[0].originator, "dummy-originator")
        assertEquals(models[0].status, "dummy-status")
        assertEquals(models[0].owner, "dummy-owner")
        assertEquals(models[0].isFavorite, false)
    }
}

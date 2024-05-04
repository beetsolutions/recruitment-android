package com.beettechnologies.agreena.home.presentation

import com.beettechnologies.agreena.CoroutineTestRule
import com.beettechnologies.agreena.common.data.model.Resource
import com.beettechnologies.agreena.common.presentation.model.UiStateModel
import com.beettechnologies.agreena.home.domain.interactor.GetCertificatesUseCase
import com.beettechnologies.agreena.home.domain.interactor.UpdateFavoriteUseCase
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    private val certificatesUseCase = spyk(GetCertificatesUseCase(mockk()))
    private val favoriteUseCase = spyk(UpdateFavoriteUseCase(mockk()))
    private val sut = spyk(HomeViewModel(certificatesUseCase, favoriteUseCase))

    @Test
    fun `get categories  , no internet, return Error`() = runTest {
        coEvery { certificatesUseCase(Unit) } returns flow { emit(Resource.error(ERROR_MESSAGE,null)) }

        sut.getCertificates()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Error
        Assert.assertEquals(res.errorMessage , ERROR_MESSAGE)
        coVerify { certificatesUseCase(Unit) }
    }

    @Test
    fun `get categories , fetching with no errors, return Success`() = runTest {
        coEvery { certificatesUseCase(Unit) } returns flow { emit(Resource.success(CERTIFICATES)) }
        sut.getCertificates()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Success
        Assert.assertEquals(res.value, CERTIFICATES)
        coVerify { certificatesUseCase(Unit) }
    }

    @Test
    fun `get categories , fetching, return Loading`() = runTest {
        coEvery { certificatesUseCase(Unit) } returns flow { emit(Resource.loading()) }
        sut.getCertificates()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Loading
        Assert.assertEquals(res, UiStateModel.Loading)
        coVerify { certificatesUseCase(Unit) }
    }

    companion object {
        private const val ERROR_MESSAGE = "No internet available."
        private val CERTIFICATES = listOf(CertificateModel("dummy-id", "dummy-originator", "dummy-owner", "dummy-status", false))
    }
}

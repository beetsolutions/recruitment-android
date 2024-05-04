package com.beettechnologies.agreena.home.di

import com.beettechnologies.agreena.home.data.api.CertificateApi
import com.beettechnologies.agreena.home.data.repository.CertificateRepositoryImpl
import com.beettechnologies.agreena.home.domain.repository.CertificateRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideCountryApi(retrofit: Retrofit): CertificateApi {
        return retrofit.create(CertificateApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMealRepository(mealRepository: CertificateRepositoryImpl): CertificateRepository
}

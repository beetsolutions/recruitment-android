package com.beettechnologies.agreena.app.di

import android.content.Context
import com.beettechnologies.agreena.app.App
import com.beettechnologies.agreena.app.AppDatabase
import com.beettechnologies.agreena.home.data.local.dao.CertificateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): App = context as App

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCertificateDao(db: AppDatabase): CertificateDao = db.certificateDao()
}

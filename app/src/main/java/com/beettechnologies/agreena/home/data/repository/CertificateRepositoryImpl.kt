package com.beettechnologies.agreena.home.data.repository

import com.beettechnologies.agreena.common.data.model.ApiResponse
import com.beettechnologies.agreena.common.data.model.Resource
import com.beettechnologies.agreena.common.data.repository.NetworkResource
import com.beettechnologies.agreena.home.data.api.CertificateApi
import com.beettechnologies.agreena.home.data.local.dao.CertificateDao
import com.beettechnologies.agreena.home.data.local.entity.CertificateEntity
import com.beettechnologies.agreena.home.data.mapper.CertificateMapper
import com.beettechnologies.agreena.home.data.model.CertificateResponse
import com.beettechnologies.agreena.home.domain.model.CertificateModel
import com.beettechnologies.agreena.home.domain.repository.CertificateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CertificateRepositoryImpl @Inject constructor(
    private val certificateApi: CertificateApi,
    private val dao: CertificateDao,
    private val certificateMapper: CertificateMapper) : CertificateRepository {

    override suspend fun getCategories(): Flow<Resource<List<CertificateModel>>> {
        return object : NetworkResource<List<CertificateModel>, List<CertificateResponse>>() {
            override suspend fun loadResults(item: List<CertificateResponse>?): Flow<List<CertificateModel>> {
                return flow {
                    item?.let {
                        val entities = it.map { cert ->
                            CertificateEntity(
                                id = cert.id,
                                originator = cert.originator,
                                owner = cert.owner,
                                status = cert.status
                            )
                        }
                        dao.upsertCertificates(entities)
                        emit(loadFromDb())
                    }
                }
            }

            override suspend fun createNetworkRequest(): ApiResponse<List<CertificateResponse>> {
                return try {
                    ApiResponse.create(certificateApi.certificates())
                } catch (throwable: Throwable) {
                    val error = if (throwable is UnknownHostException) {
                        Throwable(message = "No internet connection available!")
                    } else throwable
                    ApiResponse.create(error)
                }
            }

            override suspend fun loadFromDb(): List<CertificateModel> {
                return certificateMapper.map(dao.getAll())
            }
        }.asFlow()
    }

    override suspend fun updateFavorite(id: String, value: Boolean): Flow<List<CertificateModel>> {
        return flow {
            dao.update(id, value)
            emit(certificateMapper.map(dao.getAll()))
        }
    }
}

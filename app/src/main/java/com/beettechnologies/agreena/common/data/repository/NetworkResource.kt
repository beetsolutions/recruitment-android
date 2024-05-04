package com.beettechnologies.agreena.common.data.repository

import androidx.annotation.MainThread
import com.beettechnologies.agreena.common.data.model.ApiResponse
import com.beettechnologies.agreena.common.data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkResource<ResultType, RequestType> {

    fun asFlow() = flow<Resource<ResultType>> {

        if (shouldSkipLocalFetch()) {
            emit(Resource.loading())
        } else {
            emit(Resource.loading(data = loadFromDb()))
        }

        when (val apiResponse = createNetworkRequest()) {
            is ApiResponse.ApiSuccessResponse -> {
                val flow = loadResults(processResponse(apiResponse))
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiEmptyResponse -> {
                val flow = loadResults()
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiErrorResponse -> {
                emit(Resource.error(apiResponse.errorMessage, data = loadFromDb()))
            }
        }
    }

    protected open fun processResponse(response: ApiResponse.ApiSuccessResponse<RequestType>) = response.body

    protected abstract suspend fun loadResults(item: RequestType? = null): Flow<ResultType>

    protected abstract suspend fun createNetworkRequest(): ApiResponse<RequestType>

    @MainThread
    protected open suspend fun loadFromDb(): ResultType? = null

    @MainThread
    protected open fun shouldSkipLocalFetch(): Boolean = false
}

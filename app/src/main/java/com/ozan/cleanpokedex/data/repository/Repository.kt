package com.ozan.cleanpokedex.data.repository

import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.datasource.network.BaseError
import com.ozan.cleanpokedex.data.datasource.network.BaseResponse
import retrofit2.HttpException

abstract class Repository {

    protected suspend fun  <R> networkCall(
            callFunction: suspend () -> R
    ) : Resource<R> {
        return try {
            val response= callFunction.invoke()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}
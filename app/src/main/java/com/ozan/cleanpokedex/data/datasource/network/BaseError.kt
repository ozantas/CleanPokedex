package com.ozan.cleanpokedex.data.datasource.network

sealed class BaseError(

) : Throwable() {

    class NetworkError(e: Throwable) : BaseError()
    class MappingError : BaseError()
    class EmptyResult(errorMessage: String? = null) : BaseError()

}
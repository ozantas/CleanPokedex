package com.ozan.cleanpokedex.extension

import com.ozan.cleanpokedex.data.Resource

fun <T> Resource<T>.onSuccessResource(doOnSuccess: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        doOnSuccess(data)
    }
    return this
}

suspend fun <T> Resource<T>.onSuccessResourceSuspend(doOnSuccess: suspend (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        doOnSuccess(data)
    }
    return this
}

fun <T> Resource<T>.onErrorResource(doOnError: (Throwable) -> Unit): Resource<T> {
    if (this is Resource.Error) {
        doOnError(error)
    }
    return this
}

suspend fun <T> Resource<T>.onErrorResourceSuspend(doOnError: suspend (Throwable) -> Unit): Resource<T> {
    if (this is Resource.Error) {
        doOnError(error)
    }
    return this
}

fun <FROM, TO> Resource<FROM>.map(
        onSuccess: (FROM) -> Resource<TO>,
        onError: (Throwable) -> Resource.Error<TO>
): Resource<TO> {
    return when (this) {
        is Resource.Success -> {
            onSuccess(data)
        }
        is Resource.Error -> {
            onError(error)
        }
    }
}

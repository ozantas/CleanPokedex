package com.ozan.cleanpokedex.extension

import com.ozan.cleanpokedex.data.Resource
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.onException(onError: (Throwable) -> Unit): Flow<T> {
    return flow {
        try {
            collect { value ->
                emit(value)
            }
        } catch (e: Exception) {
            onError(e)
            throw e
        }
    }
}

fun <T> Flow<T>.delay(millis: Long): Flow<T> {
    return flow {
        delay(millis)
        collect {
            emit(it)
        }
    }
}

fun <P:Any, R: Any> Flow<List<P>>.mapEach(function: (P) -> R): Flow<List<R>> {
    return map {
        it.map(function)
    }
}

fun <P:Any, R: Any> Flow<List<P>>.mapEachIndexed(function: (Int,P) -> R): Flow<List<R>> {
    return map {
        it.mapIndexed { index, p ->
            function.invoke(index,p)
        }
    }
}

fun <T:Any> Flow<Resource<T>>.onSuccessResource(function: (T) -> Unit): Flow<Resource<T>> {
    return onEach {
        if(it is Resource.Success){
            function.invoke(it.data)
        }
    }
}

fun <T:Any> Flow<Resource<T>>.onErrorResource(function: (Throwable) -> Unit): Flow<Resource<T>> {
    return onEach {
        if(it is Resource.Error){
            function.invoke(it.error)
        }
    }
}
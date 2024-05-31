package com.example.imdbmoviesapp.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: String?) : NetworkResult<T>(message = message)
}

inline fun <T> Flow<NetworkResult<T>>.onSuccess(
    crossinline NetworkResult: suspend T.() -> Unit
): Flow<NetworkResult<T>> = transform { value ->
    if (value is NetworkResult.Success) value.data?.NetworkResult()
    return@transform emit(value)
}

inline fun <T> Flow<NetworkResult<T>>.onLoading(
    crossinline NetworkResult: suspend T?.() -> Unit
): Flow<NetworkResult<T>> = transform { value ->
    if (value is NetworkResult.Loading) value.data.NetworkResult()
    return@transform emit(value)
}

inline fun <T> Flow<NetworkResult<T>>.onError(
    crossinline NetworkResult: suspend String.() -> Unit
): Flow<NetworkResult<T>> = transform { value ->
    if (value is NetworkResult.Error) NetworkResult(value.message ?: "")
    return@transform emit(value)
}


inline fun <T> NetworkResult<T>.onSuccess(
    crossinline NetworkResult: T.() -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success) data?.NetworkResult()
}

inline fun <T> NetworkResult<T>.onLoading(
    crossinline NetworkResult: T?.() -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Loading) data.NetworkResult()
}

inline fun <T> NetworkResult<T>.onError(
    crossinline NetworkResult: String.() -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error) (message ?: "").NetworkResult()
}

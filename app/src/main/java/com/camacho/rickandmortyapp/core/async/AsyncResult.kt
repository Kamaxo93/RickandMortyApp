package com.camacho.rickandmortyapp.core.async

sealed class AsyncResult<T> {
    class Loading<T> : AsyncResult<T>()
    class Success<T>(val data: T) : AsyncResult<T>()
    class Error<T>(val error: AsyncError) : AsyncResult<T>()
}
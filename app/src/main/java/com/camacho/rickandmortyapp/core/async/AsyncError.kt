package com.camacho.rickandmortyapp.core.async

sealed class AsyncError {
    data object ConnectionError : AsyncError()
    data object TimeoutError : AsyncError()
    data object DataParseError : AsyncError()
    data class ServerError(val code: Int, val errorMessage: String?) : AsyncError()
    data class UnknownError(val errorThrown: Throwable? = null) : AsyncError()
}
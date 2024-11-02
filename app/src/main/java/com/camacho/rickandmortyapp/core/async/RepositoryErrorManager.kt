package com.camacho.rickandmortyapp.core.async

import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

object RepositoryErrorManager {

    suspend inline fun <T> wrap(crossinline block: suspend () -> T): Flow<AsyncResult<T>> =
        flow {
            emit(AsyncResult.Loading())
            try {
                emit(AsyncResult.Success(block()))

            } catch (e: CancellationException) {
                // CancellationException should never be intercepted, throw it as-is
                throw e

            } catch (e: Exception) {
                emit(AsyncResult.Error(e.toAsyncError()))
            }
        }

    fun Exception.toAsyncError(): AsyncError {
        return when (this) {
            is HttpException -> AsyncError.ServerError(
                code = code(),
                errorMessage = when (code()) {
                    400 -> "Bad Request"
                    401 -> "Unauthorized"
                    403 -> "Forbidden"
                    404 -> "Not Found"
                    500 -> "Internal Server Error"
                    else -> "Internal Server Error"
                },
            )

            is UnknownHostException -> AsyncError.ConnectionError
            is SocketTimeoutException -> AsyncError.TimeoutError
            is JsonParseException -> AsyncError.DataParseError
            else -> AsyncError.UnknownError(this)
        }
    }
}

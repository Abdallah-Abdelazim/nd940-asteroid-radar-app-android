package com.abdallah_abdelazim.asteroidradar.data.remote.utitls

import com.abdallah_abdelazim.asteroidradar.data.remote.exception.UnsuccessfulNetworkResponseException
import retrofit2.Response

@Throws(
    UnsuccessfulNetworkResponseException::class,
    IllegalStateException::class
)
inline fun <T, R> handleResponse(response: Response<T>, bodyConverter: T.() -> R): R {
    if (response.isSuccessful) {
        return with(checkNotNull(response.body()) { "Response body is null" }) {
            bodyConverter()
        }
    } else {
        throw UnsuccessfulNetworkResponseException(response.code())
    }
}
package com.abdallah_abdelazim.asteroidradar.data.remote.exception

class UnsuccessfulNetworkResponseException(
    val responseCode: Int,
) : RuntimeException("Response failed with code: $responseCode")
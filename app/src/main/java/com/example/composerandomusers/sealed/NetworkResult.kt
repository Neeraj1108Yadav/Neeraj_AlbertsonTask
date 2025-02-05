package com.example.composerandomusers.sealed

sealed class NetworkResult<out T>{
    data class Success<out T>(val data:T): NetworkResult<T>()
    data class Failure(val message:String): NetworkResult<Nothing>()
    object Loading: NetworkResult<Nothing>()
}
package com.example.composerandomusers.repo

import android.util.Log
import com.example.composerandomusers.listeners.UserFetchListener
import com.example.composerandomusers.model.UserResult
import com.example.composerandomusers.network.RetrofitClient
import com.example.composerandomusers.sealed.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchRandomUserRepo : UserFetchListener{

    override suspend fun getRandomUser(records:Int): Flow<NetworkResult<UserResult>> {
        return flow {
            emit(NetworkResult.Loading)
            val response = RetrofitClient.apiClient.getRandomUser(records)
            if(response.isSuccessful && response.body() != null){
                emit(NetworkResult.Success(response.body()!!))
            }else{
                emit(NetworkResult.Failure("Something went wrong"))
            }
        }.onStart {
            emit(NetworkResult.Loading)
        }.catch {exception->
            exception.message?.let {
                emit(NetworkResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

}
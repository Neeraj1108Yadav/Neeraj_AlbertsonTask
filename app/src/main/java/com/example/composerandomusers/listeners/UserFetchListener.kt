package com.example.composerandomusers.listeners

import com.example.composerandomusers.model.UserResult
import com.example.composerandomusers.sealed.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserFetchListener {

    suspend fun getRandomUser(records:Int) : Flow<NetworkResult<UserResult>>
}
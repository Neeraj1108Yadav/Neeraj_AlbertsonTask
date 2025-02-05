package com.example.composerandomusers.sealed

import com.example.composerandomusers.model.User


sealed class UserListUiState {
    object Loading: UserListUiState()
    data class onSuccess(val users:List<User>) : UserListUiState()
    data class onFailure(val message:String?) : UserListUiState()
}
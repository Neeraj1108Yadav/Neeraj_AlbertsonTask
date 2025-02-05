package com.example.composerandomusers.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composerandomusers.listeners.UserFetchListener
import com.example.composerandomusers.screens.users.UserListViewModel
import kotlin.jvm.java

class UserListViewModelFactory(private val listener: UserFetchListener): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if(modelClass.isAssignableFrom(UserListViewModel::class.java)){
            return UserListViewModel(listener) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
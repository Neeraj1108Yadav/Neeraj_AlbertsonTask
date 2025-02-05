package com.example.composerandomusers.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerandomusers.listeners.UserFetchListener
import com.example.composerandomusers.sealed.NetworkResult
import com.example.composerandomusers.sealed.UserListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val listener: UserFetchListener) : ViewModel() {

    private var _users: MutableStateFlow<UserListUiState> = MutableStateFlow(UserListUiState.Loading)
    val users: StateFlow<UserListUiState> = _users

    private var _showSnackBarMsg: MutableStateFlow<String?> = MutableStateFlow(null)
    val showSnackBar: StateFlow<String?> = _showSnackBarMsg

    fun getRandomUser(records:Int = 10){
        viewModelScope.launch{
           listener.getRandomUser(records)
               .collect { user->
                   when(user){
                       is NetworkResult.Loading ->{
                           _users.value = UserListUiState.Loading
                       }
                       is NetworkResult.Failure ->{
                           _users.value = UserListUiState.onFailure(user.message)
                           _showSnackBarMsg.value = user.message
                       }
                       is NetworkResult.Success ->{
                           _users.value = UserListUiState.onSuccess(user.data.results)
                       }
                   }
            }
        }
    }

    fun clearSnackBarMsg(){
        _showSnackBarMsg.value = null
    }
}

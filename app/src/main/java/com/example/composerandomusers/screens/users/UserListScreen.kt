package com.example.composerandomusers.screens.users

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composerandomusers.components.CustomInputDialog
import com.example.composerandomusers.components.IndeterminateCircular
import com.example.composerandomusers.components.TopAppBar
import com.example.composerandomusers.components.UserCard
import com.example.composerandomusers.factory.UserListViewModelFactory
import com.example.composerandomusers.model.User
import com.example.composerandomusers.repo.FetchRandomUserRepo
import com.example.composerandomusers.sealed.UserListUiState

@Composable
fun UserListScreen(
    fetchRandomUserRepo: FetchRandomUserRepo,
    onSelectUser: (User) -> Unit
){

    val viewModel: UserListViewModel = viewModel<UserListViewModel>(
        factory = UserListViewModelFactory(fetchRandomUserRepo)
    )

    val snackbarHostState = remember { SnackbarHostState() }

    val dataFetchedKey = rememberSaveable { mutableStateOf(0) }
    val defaultUserFetched = rememberSaveable { mutableStateOf(false) }
    val hideLoader = rememberSaveable { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val inputRecord = remember {mutableStateOf("")}

    val userData = viewModel.users.collectAsStateWithLifecycle()
    val showSnackBarOnFail = viewModel.showSnackBar.collectAsState()

    LaunchedEffect(dataFetchedKey.value){
        if(!defaultUserFetched.value){
            viewModel.getRandomUser()
        }else{
            if(inputRecord.value.isNotBlank() && dataFetchedKey.value > 0){
                hideLoader.value = false
                viewModel.getRandomUser(inputRecord.value.toInt())
            }
        }
    }

    LaunchedEffect(showSnackBarOnFail.value) {
        showSnackBarOnFail.value?.let { message->
            snackbarHostState.showSnackbar(message)
            viewModel.clearSnackBarMsg()
        }
    }

    if(showDialog.value){
        CustomInputDialog{record ->
            if(record.isNotEmpty()){
                inputRecord.value = record
                dataFetchedKey.value += 1
            }
            showDialog.value = false
        }
    }

    Scaffold(
        topBar =  {
            TopAppBar(title = "Home")
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                modifier = Modifier.testTag("button_float"),
                onClick = {
                        showDialog.value = true
                },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){
            Box(
                contentAlignment = Alignment.Center
            ){
                when(val result = userData.value){
                    is UserListUiState.Loading->{
                        IndeterminateCircular(hideLoader.value,modifier = Modifier.testTag("loading"))
                    }
                    is UserListUiState.onFailure->{
                        hideLoader.value = true
                    }
                    is UserListUiState.onSuccess->{
                        hideLoader.value = true
                        defaultUserFetched.value = true
                        LazyColumn(
                            modifier = Modifier.testTag("user_list")
                        ) {
                            itemsIndexed(
                                items = result.users,
                                key = {index,user->
                                    index
                                },
                                itemContent = {index,user->
                                    UserCard(user,{
                                        onSelectUser.invoke(user)
                                    })
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
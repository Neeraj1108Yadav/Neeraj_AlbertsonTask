package com.example.composerandomusers.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composerandomusers.model.User
import com.example.composerandomusers.repo.FetchRandomUserRepo
import com.example.composerandomusers.screens.info.UserInfoScreen
import com.example.composerandomusers.screens.users.UserListScreen

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UserList.route
    ) {
        composable(route = UserList.route){
            UserListScreen(FetchRandomUserRepo()) { user ->
                // Store data in savedStateHandle
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = UserInfo.userInfo,
                    value = user
                )
                // Navigate to UserInfo without arguments
                navController.navigate(UserInfo.route){
                    popUpTo(UserList.route){ inclusive = false}
                }
            }
        }

        composable(route = UserInfo.route){navBackStackEntry->
            // Retrieve data from savedStateHandle
            val user = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<User>(UserInfo.userInfo)
            if(user != null){
                UserInfoScreen(user)
            }
        }
    }
}
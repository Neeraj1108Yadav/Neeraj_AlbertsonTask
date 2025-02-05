package com.example.composerandomusers.nav

interface AppDestinations {
    val route: String
}

object UserList : AppDestinations{
    override val route: String
        get() = "users"

}

object UserInfo : AppDestinations{
    override val route: String
        get() = "user_info"
    const val userInfo:String = "userInfo"
}
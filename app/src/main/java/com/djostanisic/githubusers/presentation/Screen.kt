package com.djostanisic.githubusers.presentation


sealed class Screen(val route: String) {
    object UserListScreen: Screen("user_list_screen")
    object UserDetailsScreen: Screen("user_details_screen")
}
package com.djostanisic.githubusers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.djostanisic.githubusers.common.Constants
import com.djostanisic.githubusers.presentation.ui.theme.GithubUsersAppTheme
import com.djostanisic.githubusers.presentation.user_details.UserDetailsScreen
import com.djostanisic.githubusers.presentation.user_list.UserListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserListScreen.route
                    ) {
                        composable(
                            route = Screen.UserListScreen.route
                        ) {
                            UserListScreen(navController)
                        }
                        composable(
                            route = Screen.UserDetailsScreen.route + "/{${Constants.PARAM_USER_NAME}}",
                            arguments = listOf(navArgument(Constants.PARAM_USER_NAME) {
                                type = NavType.StringType
                            })
                        ) {
                            UserDetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
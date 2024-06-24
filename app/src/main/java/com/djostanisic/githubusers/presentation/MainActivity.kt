package com.djostanisic.githubusers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.djostanisic.githubusers.presentation.ui.theme.GithubUsersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                }
            }
        }
    }
}
package com.example.to_do_app.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_do_app.ui.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
){
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = {}
    )
}
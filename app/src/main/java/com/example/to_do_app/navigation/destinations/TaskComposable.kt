package com.example.to_do_app.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do_app.ui.screens.task.TaskScreen
import com.example.to_do_app.ui.util.Action
import com.example.to_do_app.ui.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_do_app.ui.util.Constants.LIST_SCREEN
import com.example.to_do_app.ui.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_do_app.ui.util.Constants.TASK_SCREEN
import com.example.to_do_app.ui.viewmodels.ShareViewModel

fun NavGraphBuilder.taskComposable(
    shareViewModel: ShareViewModel,
    navigateToListScreen: (Action) -> Unit
){
    composable (
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId) {
            shareViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by shareViewModel.selectedTask.collectAsState()

        TaskScreen(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}
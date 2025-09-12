package com.example.to_do_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_do_app.navigation.destinations.listComposable
import com.example.to_do_app.navigation.destinations.taskComposable
import com.example.to_do_app.ui.util.Action
import com.example.to_do_app.ui.util.Constants
import com.example.to_do_app.ui.util.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
        navController: NavHostController
){
    val screen = remember ( navController ) {
        Screens(navController =  navController)
    }

    // Construct the start destination route with the default action
    val startDestinationRoute = Constants.LIST_SCREEN.replace(
        "{${Constants.LIST_ARGUMENT_KEY}}",
        Action.NO_ACTION.name
    )

    NavHost(navController = navController,
        startDestination = startDestinationRoute
    ){
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable (
            navigateToListScreen = screen.list
        )
    }
}

package com.example.to_do_app.navigation

import androidx.navigation.NavHostController
import com.example.to_do_app.ui.util.Action
import com.example.to_do_app.ui.util.Constants.LIST_SCREEN

class Screens (navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}
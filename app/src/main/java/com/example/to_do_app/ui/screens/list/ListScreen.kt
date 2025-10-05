package com.example.to_do_app.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.to_do_app.R
import com.example.to_do_app.ui.util.SearchAppBarState
import com.example.to_do_app.ui.viewmodels.ShareViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
){
    LaunchedEffect(key1 = true) {
        shareViewModel.getAllTasks()
    }

    val allTasks by shareViewModel.allTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState

    Scaffold (
        topBar = {
            ListAppBar(
                shareViewModel = shareViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = { padding ->
            ListContent(
                modifier = Modifier.padding(padding),
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab ( onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
){
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button)
        )
    }
}
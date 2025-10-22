package com.example.to_do_app.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.to_do_app.data.models.Priority
import com.example.to_do_app.data.models.ToDoTask
import com.example.to_do_app.ui.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
){
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {
            padding ->
            if (selectedTask != null) {
                TaskContent(
                    title = selectedTask.title,
                    onTitleChange = {},
                    description = selectedTask.description,
                    onDescriptionChange = {},
                    priority = selectedTask.priority,
                    onPrioritySelected = {},
                    modifier = Modifier.padding(padding)
                )
            }
        }
    )
}
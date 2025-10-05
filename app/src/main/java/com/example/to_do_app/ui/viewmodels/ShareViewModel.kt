package com.example.to_do_app.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.data.models.ToDoTask
import com.example.to_do_app.data.repositories.ToDoRepository
import com.example.to_do_app.ui.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    val searchAppBarState:  MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> =
        mutableStateOf("")


    private val _allTasks =
        MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks    //Publicly exposed

    fun getAllTasks(){
        viewModelScope.launch {
            repository.getAllTasks.collect {
                _allTasks.value = it
            }

        }
    }
}
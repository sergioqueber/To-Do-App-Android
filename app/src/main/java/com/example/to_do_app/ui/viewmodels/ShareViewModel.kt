package com.example.to_do_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.data.models.ToDoTask
import com.example.to_do_app.data.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    private val _allTasks =
        MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks    //Publicly stored

    fun getAllTasks(){
        viewModelScope.launch {
            repository.getAllTasks.collect {
                _allTasks.value = it
            }

        }
    }
}
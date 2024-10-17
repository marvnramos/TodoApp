package com.example.tasker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskManagerViewModel: ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title


    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    fun onTextFieldChanged(title: String, description: String){
        _title.value = title
        _description.value = description
    }
}
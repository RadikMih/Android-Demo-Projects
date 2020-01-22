package com.testingviews.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<ParentData>>()
    private val _selected = MutableLiveData<Data>()

    val categories: LiveData<List<ParentData>>
        get() = _categories

    val selected: LiveData<Data>
        get() = _selected

    fun select(item: Data) {
        if (item != _selected.value) {
            _selected.value = item
        }
    }
}
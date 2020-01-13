package com.testingviews.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<ParentData>>()
    val categories: LiveData<List<ParentData>>
        get() = _categories

    val selected = MutableLiveData<Data>()

    fun select(item: Data) {
        selected.value = item

    }

}
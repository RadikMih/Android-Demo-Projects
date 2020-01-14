package com.testingviews.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    public val test = "asdasd"
    private val _categories = MutableLiveData<List<ParentData>>()
    private val _selected = MutableLiveData<Data>()


    val categories: LiveData<List<ParentData>>
        get() = _categories


    val selected: LiveData<Data>
        get() = _selected


    fun select(item: Data) {
        _selected.value = item
    }
}
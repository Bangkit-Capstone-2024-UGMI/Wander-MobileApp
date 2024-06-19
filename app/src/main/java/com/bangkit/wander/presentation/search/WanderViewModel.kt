package com.bangkit.wander.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.wander.data.model.Places
import com.bangkit.wander.data.repository.WanderRepository

class WanderViewModel (
    private val repository: WanderRepository
) : ViewModel() {

    val placeTitle = MutableLiveData<String>()
    val placeLocation = MutableLiveData<String>()
    val placeImage = MutableLiveData<String>()
    fun getPlaceList() : LiveData<List<Places>> {
        return repository.getPlaceList()
    }
}
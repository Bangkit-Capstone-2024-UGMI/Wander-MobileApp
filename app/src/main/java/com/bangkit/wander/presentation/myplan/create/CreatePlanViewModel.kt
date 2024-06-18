package com.bangkit.wander.presentation.myplan.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreatePlanViewModel() : ViewModel() {

    private val _planNameText = MutableLiveData("")
    val planNameText: LiveData<String> = _planNameText

    private val _dateText = MutableLiveData("")
    val dateText: LiveData<String> = _dateText

    private val _locationText = MutableLiveData("")
    val locationText: LiveData<String> = _locationText

    private val _destinationList = MutableLiveData<List<String>>(listOf(""))
    val destinationList: LiveData<List<String>> = _destinationList

    fun onPlanNameTextChanged(newText: String) {
        _planNameText.value = newText
    }

    fun onDateTextChanged(newText: String) {
        _dateText.value = newText
    }

    fun onLocationTextChanged(newText: String) {
        _locationText.value = newText
    }

    fun onDestinationListChanged(newList: List<String>) {
        _destinationList.value = newList
    }

    fun addDestination(newDestination: String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.add(newDestination)
        _destinationList.value = currentList
    }

    fun removeDestination(destination: String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.remove(destination)
        _destinationList.value = currentList
    }

    fun clearDestinationList() {
        _destinationList.value = listOf()
    }

    fun clearAllFields() {
        _planNameText.value = ""
        _dateText.value = ""
        _locationText.value = ""
        _destinationList.value = listOf()
    }
}
package com.bangkit.wander.presentation.myplan.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreatePlanViewModel : ViewModel() {

    private val _planNameText = MutableLiveData("")
    val planNameText: LiveData<String> = _planNameText

    private val _dateText = MutableLiveData("")
    val dateText: LiveData<String> = _dateText

    private val _locationText = MutableLiveData("")
    val locationText: LiveData<String> = _locationText

    private val _destinationList = MutableLiveData(listOf(""))
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

    fun addDestination(newDestination: String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.add(newDestination)
        _destinationList.value = currentList
    }

    fun onDestinationTextCahnge(index: Int, newText:String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList[index] = newText
        _destinationList.value = currentList
    }

    fun removeDestination(index: Int) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.removeAt(index)
        _destinationList.value = currentList
    }
}
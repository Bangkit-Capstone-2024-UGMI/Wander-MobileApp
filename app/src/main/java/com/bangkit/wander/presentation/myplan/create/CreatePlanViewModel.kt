package com.bangkit.wander.presentation.myplan.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.repository.PlanRepository
import com.bangkit.wander.data.request.HotelsRequest
import kotlinx.coroutines.launch

class CreatePlanViewModel (
    private val planRepository: PlanRepository
) : ViewModel() {

    private val _planNameText = MutableLiveData("")
    val planNameText: LiveData<String> = _planNameText

    private val _dateText = MutableLiveData("")
    val dateText: LiveData<String> = _dateText

    private val _locationText = MutableLiveData("")
    val locationText: LiveData<String> = _locationText

    private val _destinationList = MutableLiveData(listOf(""))
    val destinationList: LiveData<List<String>> = _destinationList

    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> = _hotels

    fun fetchHotels() {
        viewModelScope.launch {
            val request = HotelsRequest(
                // TODO: get the actual location from the locationText
                tourInterests = listOf(
                    "Borobudur Temple",
                    "Prambanan Temple",
                )
            )
            val fetchedHotels = planRepository.findHotels(request)
            _hotels.value = fetchedHotels
        }
    }

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

    fun onDestinationTextCahnge(index: Int, newText: String) {
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
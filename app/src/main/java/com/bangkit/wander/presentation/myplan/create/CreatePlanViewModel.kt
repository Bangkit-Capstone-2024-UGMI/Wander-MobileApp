package com.bangkit.wander.presentation.myplan.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.wander.data.local.TemporaryData
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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchHotels(request: HotelsRequest) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val fetchedHotels = planRepository.findHotels(request)
                _hotels.value = fetchedHotels
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch hotels: ${e.message}. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }

    fun saveHotelsRequest() {
        TemporaryData.hotelsRequest = HotelsRequest(
            userId = 123,
            topN = 10,
            tourInterests = listOf(
                "Borobudur Temple",
                "Prambanan Temple",
            )
        )
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
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
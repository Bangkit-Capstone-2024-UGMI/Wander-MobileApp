package com.bangkit.wander.presentation.myplan.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.data.model.Destination
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.model.Location
import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.repository.PlanRepository
import com.bangkit.wander.data.request.HotelsRequest
import kotlinx.coroutines.delay
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

    private val _isFormValid = MutableLiveData(false)
    val isFormValid: LiveData<Boolean> = _isFormValid

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

    fun createPlan(plan: Plan) {
        _loading.value = true
        viewModelScope.launch {
            try {
                Log.d("CreatePlanViewModel", "createPlan: ${TemporaryData.newPlan}")
                // buat contoh pake delay dulu
                delay(4000)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to create plan: ${e.message}. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }

    fun saveHotelsRequest() {
        // filter without "" and remove trailing spaces
        val destinationListString = _destinationList.value.orEmpty().filter { it.isNotBlank() }.map { it.trim() }

        val destinationList = destinationListString.map {
            Destination(
                name = it,
                location = Location(
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
        }
        TemporaryData.newPlan = Plan(
            title = _planNameText.value.orEmpty(),
            date = _dateText.value.orEmpty(),
            city = _locationText.value.orEmpty(),
            destinations = destinationList
        )
        TemporaryData.hotelsRequest = HotelsRequest(
            userId = 123,
            topN = 10,
            tourInterests = destinationListString
        )
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun onPlanNameTextChanged(newText: String) {
        _planNameText.value = newText
        validateForm()
    }

    fun onDateTextChanged(newText: String) {
        _dateText.value = newText
        validateForm()
    }

    fun onLocationTextChanged(newText: String) {
        _locationText.value = newText
        validateForm()
    }

    fun addDestination(newDestination: String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.add(newDestination)
        _destinationList.value = currentList
        validateForm()
    }

    fun onDestinationTextChange(index: Int, newText: String) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList[index] = newText
        _destinationList.value = currentList
        validateForm()
    }

    fun removeDestination(index: Int) {
        val currentList = _destinationList.value.orEmpty().toMutableList()
        currentList.removeAt(index)
        _destinationList.value = currentList
        validateForm()
    }

    private fun validateForm() {
        _isFormValid.value = !(_planNameText.value.isNullOrEmpty() ||
                _dateText.value.isNullOrEmpty() ||
                _locationText.value.isNullOrEmpty() ||
                _destinationList.value.isNullOrEmpty() ||
                _destinationList.value?.any { it.isEmpty() } == true)
    }

}
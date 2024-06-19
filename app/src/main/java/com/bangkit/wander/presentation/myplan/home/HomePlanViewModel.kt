package com.bangkit.wander.presentation.myplan.home

import android.util.Log
import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.repository.PlanRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HomePlanViewModel(
    private val repository: PlanRepository,
) : ViewModel() {

    private val _planList = MutableLiveData<List<Plan>>()
    val planList: LiveData<List<Plan>> = _planList

    private val _planDetail = MutableLiveData<Plan>()
    val planDetail: LiveData<Plan> = _planDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getPlanList() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val fetchedPlans = repository.getAllPlan()
                _planList.value = fetchedPlans
                Log.d("PLAN LIST", fetchedPlans.toString())
            } catch (e: Exception) {
                Log.d("PLAN LIST", e.message.toString())
                _errorMessage.value = "Failed to fetch plans: ${e.message}. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }

    fun getEmptyList() : LiveData<List<Plan>> {
        return MutableLiveData(
            listOf()
        )
    }

    fun getPlanById(id: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val fetchedPlan = repository.getPlanById(id)
                _planDetail.value = fetchedPlan
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch plan: ${e.message}. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
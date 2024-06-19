package com.bangkit.wander.presentation.myplan.home

import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.repository.PlanRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomePlanViewModel(
    private val repository: PlanRepository,
) : ViewModel() {

    fun getPlanList() : LiveData<List<Plan>> {
        return repository.getPlanList()
    }

    fun getEmptyList() : LiveData<List<Plan>> {
        return MutableLiveData(
            listOf()
        )
    }

    fun getPlanById(id: String): Plan? {
        return repository.getPlanById(id)
    }
}
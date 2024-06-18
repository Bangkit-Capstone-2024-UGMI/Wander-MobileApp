package com.bangkit.wander.data.repository

import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.model.Plan
import androidx.lifecycle.LiveData

class PlanRepository (
    private val planService : FakePlanService
) {
    fun getPlanList(): LiveData<List<Plan>> = planService.getPlanList()

    fun getPlanById(id: String): Plan? = planService.getPlanById(id)

    companion object {
        @Volatile
        private var instance: PlanRepository? = null
        fun getInstance(
            planService: FakePlanService,
        ): PlanRepository =
            instance ?: synchronized(this) {
                instance ?: PlanRepository(
                    planService = planService
                )
            }.also { instance = it }
    }
}
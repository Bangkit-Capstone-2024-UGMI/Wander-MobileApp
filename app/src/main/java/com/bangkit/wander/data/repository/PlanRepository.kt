package com.bangkit.wander.data.repository

import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.model.Plan
import androidx.lifecycle.LiveData
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.remote.HotelsService
import com.bangkit.wander.data.request.HotelsRequest
import java.util.concurrent.Flow

class PlanRepository (
    private val planService : FakePlanService,
    private val hotelsService: HotelsService
) {
    fun getPlanList(): LiveData<List<Plan>> = planService.getPlanList()

    fun getPlanById(id: String): Plan? = planService.getPlanById(id)

    suspend fun findHotels(request: HotelsRequest) : List<Hotel> {
        return hotelsService.findHotels(request)
    }

    companion object {
        @Volatile
        private var instance: PlanRepository? = null
        fun getInstance(
            planService: FakePlanService,
            hotelsService: HotelsService
        ): PlanRepository =
            instance ?: synchronized(this) {
                instance ?: PlanRepository(
                    planService = planService,
                    hotelsService = hotelsService
                )
            }.also { instance = it }
    }
}
package com.bangkit.wander.data.repository

import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.remote.HotelService
import com.bangkit.wander.data.remote.PlanService
import com.bangkit.wander.data.request.HotelsRequest
import com.bangkit.wander.data.request.PlanHotelRequest
import com.bangkit.wander.data.request.PlanRequest
import com.bangkit.wander.data.response.PlanResponse

class PlanRepository (
    private val planService : PlanService,
    private val hotelService: HotelService
) {
//    fun getPlanList(): LiveData<List<Plan>> = planService.getPlanList()
//
//    fun getPlanById(id: String): Plan? = planService.getPlanById(id)

    suspend fun findHotels(request: HotelsRequest) : List<Hotel> {
        return hotelService.findHotels(request)
    }

    suspend fun getAllPlan() : List<Plan> {
        return planService.getAllPlans()
    }

    suspend fun getPlanById(id: String) : Plan {
        return planService.getPlanById(id)
    }

    suspend fun createPlan(planRequest: PlanRequest) : PlanResponse {
        return planService.createPlan(planRequest)
    }

    suspend fun addHotelToPlan(id: String, planHotelRequest: PlanHotelRequest) {
        planService.addHotelToPlan(id, planHotelRequest)
    }

    companion object {
        @Volatile
        private var instance: PlanRepository? = null
        fun getInstance(
            planService: PlanService,
            hotelService: HotelService
        ): PlanRepository =
            instance ?: synchronized(this) {
                instance ?: PlanRepository(
                    planService = planService,
                    hotelService = hotelService
                )
            }.also { instance = it }
    }
}
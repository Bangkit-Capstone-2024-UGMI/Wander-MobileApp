package com.bangkit.wander.data.remote

import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.request.PlanHotelRequest
import com.bangkit.wander.data.request.PlanRequest
import com.bangkit.wander.data.response.PlanResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanService {

    @POST("api/itineraryPlan")
    suspend fun createPlan(@Body request: PlanRequest) : PlanResponse

    @POST("api/itineraryPlan/{id}/hotel")
    suspend fun addHotelToPlan(@Path("id") id: String, @Body request: PlanHotelRequest)

    @GET("api/itineraryPlan")
    suspend fun getAllPlans(): List<Plan>

    @GET("api/itineraryPlan/{id}")
    suspend fun getPlanById(@Path("id") id: String): Plan
}
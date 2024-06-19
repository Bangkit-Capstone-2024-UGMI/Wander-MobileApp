package com.bangkit.wander.data.remote

import com.bangkit.wander.data.model.Plan
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanService {

//    @POST("itineraryPlan")
//    suspend fun createPlan(@Body request: PlanRequest)

    @GET("api/itineraryPlan")
    suspend fun getAllPlans(): List<Plan>

    @GET("api/itineraryPlan/{id}")
    suspend fun getPlanById(@Path("id") id: String): Plan
}
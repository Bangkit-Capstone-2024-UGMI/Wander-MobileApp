package com.bangkit.wander.di

import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.repository.AuthRepository
import com.bangkit.wander.data.repository.PlanRepository
import android.content.Context
import com.bangkit.wander.core.network.ApiConfig
import com.bangkit.wander.data.remote.HotelService
import com.bangkit.wander.data.remote.PlanService
import com.google.firebase.auth.FirebaseAuth

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val authService = FirebaseAuth.getInstance()
        return AuthRepository.getInstance(
            authService = authService
        )
    }

    fun providePlanRepository(context: Context): PlanRepository {
        val planService = ApiConfig.getApiService(PlanService::class.java)
        val hotelService = ApiConfig.getMLService(HotelService::class.java)
        return PlanRepository.getInstance(
            planService = planService,
            hotelService = hotelService
        )
    }
}
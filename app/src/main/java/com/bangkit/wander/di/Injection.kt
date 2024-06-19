package com.bangkit.wander.di

import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.repository.AuthRepository
import com.bangkit.wander.data.repository.PlanRepository
import android.content.Context
import com.bangkit.wander.data.fake.WanderService
import com.bangkit.wander.data.repository.WanderRepository
import com.google.firebase.auth.FirebaseAuth

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val authService = FirebaseAuth.getInstance()
        return AuthRepository.getInstance(
            authService = authService
        )
    }

    fun providePlanRepository(context: Context): PlanRepository {
        val planService = FakePlanService()
        return PlanRepository.getInstance(
            planService = planService
        )
    }

    fun provideWanderRepository(context: Context): WanderRepository {
        val wanderService = WanderService()
        return WanderRepository.getInstance(
            wanderService = wanderService
        )
    }
}
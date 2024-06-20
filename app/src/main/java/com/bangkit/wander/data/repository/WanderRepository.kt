package com.bangkit.wander.data.repository

import androidx.lifecycle.LiveData
import com.bangkit.wander.data.fake.FakePlanService
import com.bangkit.wander.data.fake.WanderService
import com.bangkit.wander.data.model.Places
import com.bangkit.wander.data.model.Plan

class WanderRepository(
    private val wanderService: WanderService
) {
    fun getPlaceList(): LiveData<List<Places>> = wanderService.getPlaceList()

    companion object {
        @Volatile
        private var instance: WanderRepository? = null
        fun getInstance(
            wanderService: WanderService,
        ): WanderRepository =
            instance ?: synchronized(this) {
                instance ?: WanderRepository(
                    wanderService = wanderService
                )
            }.also { instance = it }
    }
}
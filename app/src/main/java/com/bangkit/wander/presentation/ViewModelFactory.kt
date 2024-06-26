package com.bangkit.wander.presentation

import com.bangkit.wander.data.repository.AuthRepository
import com.bangkit.wander.data.repository.PlanRepository
import com.bangkit.wander.di.Injection
import com.bangkit.wander.presentation.main.MainViewModel
import com.bangkit.wander.presentation.login.LoginViewModel
import com.bangkit.wander.presentation.myplan.create.CreatePlanViewModel
import com.bangkit.wander.presentation.myplan.home.HomePlanViewModel
import com.bangkit.wander.presentation.profile.ProfileViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.wander.presentation.saved.SavedViewModel
import com.bangkit.wander.data.repository.WanderRepository
import com.bangkit.wander.presentation.search.WanderViewModel
import com.bangkit.wander.presentation.search.maps.MapViewModel


class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val planRepository: PlanRepository,
    private val wanderRepository: WanderRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(HomePlanViewModel::class.java) -> {
                HomePlanViewModel(planRepository) as T
            }
            modelClass.isAssignableFrom(CreatePlanViewModel::class.java) -> {
                CreatePlanViewModel(planRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(SavedViewModel::class.java)->{
                SavedViewModel() as T
            }
            modelClass.isAssignableFrom(MapViewModel::class.java)->{
                MapViewModel() as T
            }
            modelClass.isAssignableFrom(WanderViewModel::class.java)->{
                WanderViewModel(wanderRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        Injection.provideAuthRepository(context),
                        Injection.providePlanRepository(context),
                        Injection.provideWanderRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}

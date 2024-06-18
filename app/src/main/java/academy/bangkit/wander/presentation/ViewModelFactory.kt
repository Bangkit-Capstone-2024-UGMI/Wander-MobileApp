package academy.bangkit.wander.presentation

import academy.bangkit.wander.data.repository.AuthRepository
import academy.bangkit.wander.data.repository.PlanRepository
import academy.bangkit.wander.di.Injection
import academy.bangkit.wander.presentation.main.MainViewModel
import academy.bangkit.wander.presentation.login.LoginViewModel
import academy.bangkit.wander.presentation.myplan.create.CreatePlanViewModel
import academy.bangkit.wander.presentation.myplan.home.HomePlanViewModel
import academy.bangkit.wander.presentation.profile.ProfileViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val planRepository: PlanRepository,
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
                CreatePlanViewModel() as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(authRepository) as T
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
                        Injection.providePlanRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}

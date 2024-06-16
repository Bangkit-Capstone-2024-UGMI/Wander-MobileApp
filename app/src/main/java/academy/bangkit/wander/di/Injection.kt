package academy.bangkit.wander.di

import academy.bangkit.wander.data.fake.FakePlanService
import academy.bangkit.wander.data.repository.AuthRepository
import academy.bangkit.wander.data.repository.PlanRepository
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
}
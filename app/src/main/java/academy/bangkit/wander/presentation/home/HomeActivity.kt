package academy.bangkit.wander.presentation.home

import academy.bangkit.wander.core.base.BaseActivity
import academy.bangkit.wander.databinding.ActivityHomeBinding
import academy.bangkit.wander.presentation.ViewModelFactory
import academy.bangkit.wander.presentation.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkCurrentUser()
    }

    override fun ActivityHomeBinding.initialBinding() {
        setupAction()
    }

    private fun setupAction() {
        viewModel.currentUser.observe(this) { user ->
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.btnLogout.setOnClickListener{
            viewModel.signOut()
        }
    }


    }
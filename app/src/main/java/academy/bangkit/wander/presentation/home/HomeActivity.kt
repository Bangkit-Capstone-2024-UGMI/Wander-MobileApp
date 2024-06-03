package academy.bangkit.wander.presentation.home

import academy.bangkit.wander.core.base.BaseActivity
import academy.bangkit.wander.databinding.ActivityHomeBinding
import academy.bangkit.wander.presentation.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }
    override fun ActivityHomeBinding.initialBinding() {

        // go to login
        startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        finish()
    }


}
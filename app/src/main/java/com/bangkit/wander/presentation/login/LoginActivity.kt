package com.bangkit.wander.presentation.login

import com.bangkit.wander.core.base.BaseActivity
import com.bangkit.wander.databinding.ActivityLoginBinding
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.main.MainActivity
import android.app.Activity
import android.content.Intent
import android.graphics.Matrix
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            viewModel.handleActivityResult(data)
        } else {
            showMessage("Sign in failed")
        }
    }

    override fun ActivityLoginBinding.initialBinding() {
        setupView()
        setupAction()
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkCurrentUser()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener{
            viewModel.signIn()
        }
    }

    private fun setupView() {
        setupBackground()

        viewModel.initialize(this, activityResultLauncher)

        viewModel.currentUser.observe(this) { user ->
            updateUI(user)
        }

        viewModel.signInFailure.observe(this) { exception ->
            showMessage(exception?.message ?: "Sign in failed")
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            showMessage("Welcome, ${user.displayName}")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setupBackground() {
        val loginBg: ImageView = binding.loginBg

        // Post the matrix setup to ensure ImageView has been laid out
        loginBg.post {
            val imageMatrix = Matrix()
            val drawable = loginBg.drawable

            // Get dimensions of the drawable
            val imageWidth = drawable?.intrinsicWidth ?: 0
            val imageHeight = drawable?.intrinsicHeight ?: 0

            // Get dimensions of the ImageView
            val viewWidth = loginBg.width
            val viewHeight = loginBg.height

            // Calculate scale to fit the image into the ImageView
            val scaleX = viewWidth.toFloat() / imageWidth.toFloat()
            val scaleY = viewHeight.toFloat() / imageHeight.toFloat()

            // Use the larger scale factor to ensure the entire image is visible
            val scale = scaleX.coerceAtLeast(scaleY)

            // Calculate translation to center the image in the ImageView
            val dx = (viewWidth - imageWidth * scale) / 2
            val dy = (viewHeight - imageHeight * scale) / 2

            // Set the matrix scale and translation
            imageMatrix.setScale(scale, scale)
            imageMatrix.postTranslate(dx, dy)

            // Apply the matrix to ImageView
            loginBg.imageMatrix = imageMatrix
        }
    }
}

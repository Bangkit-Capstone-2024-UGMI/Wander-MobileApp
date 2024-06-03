package academy.bangkit.wander.presentation.login

import academy.bangkit.wander.core.base.BaseActivity
import academy.bangkit.wander.databinding.ActivityLoginBinding
import academy.bangkit.wander.presentation.ViewModelFactory
import android.graphics.Matrix
import android.widget.ImageView
import androidx.activity.viewModels

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun ActivityLoginBinding.initialBinding() {
        setupView()
        setupAction()
    }

    private fun setupAction() {
        TODO("Not yet implemented")
    }

    private fun setupView() {
        setupBackground()
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

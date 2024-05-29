package academy.bangkit.wander.core.base

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

/**
 * Base class for all activities in the application that use ViewBinding.
 *
 * @param VB The type of ViewBinding class associated with the activity.
 * @property inflate Function to inflate the ViewBinding instance.
 */
abstract class BaseActivity<VB: ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity() {

    /**
     * Lazily initialized ViewBinding instance.
     */
    private val binding: VB by lazy {
        inflate.invoke(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // Perform initial setup or binding for views here
        binding.initialBinding()
    }

    /**
     * Perform any initial setup or binding logic specific to the activity.
     */
    open fun VB.initialBinding() {}

    /**
     * Checks if the app has the specified permission.
     *
     * @param permission The permission to check.
     * @return `true` if the permission is granted, otherwise `false`.
     */
    protected fun checkPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    /**
     * Shows a toast message on the screen.
     *
     * @param message The message to display in the toast.
     */
    private fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Sets an onClickListener to navigate to the specified activity when the view is clicked.
     *
     * @param destination The destination activity class to navigate to.
     */
    protected infix fun View.goTo(destination: Class<*>) {
        setOnClickListener {
            startActivity(Intent(this@BaseActivity, destination))
        }
    }

    /**
     * Starts the specified activity and finishes the current activity.
     *
     * @receiver The destination activity class to navigate to.
     */
    protected fun Class<*>.goWithFinish() {
        startActivity(Intent(this@BaseActivity, this).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    /**
     * Starts the specified activity.
     *
     * @receiver The destination activity class to navigate to.
     */
    protected fun Class<*>.go() {
        startActivity(Intent(this@BaseActivity, this))
    }
}

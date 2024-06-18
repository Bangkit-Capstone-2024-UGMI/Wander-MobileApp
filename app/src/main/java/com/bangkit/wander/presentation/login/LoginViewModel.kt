package com.bangkit.wander.presentation.login

import com.bangkit.wander.data.repository.AuthRepository
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    private val _signInFailure = MutableLiveData<Exception?>()
    val signInFailure: LiveData<Exception?> get() = _signInFailure

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    fun initialize(context: Activity, activityResultLauncher: ActivityResultLauncher<Intent>) {
        googleSignInClient = authRepository.getGoogleSignInClient(context)
        this.activityResultLauncher = activityResultLauncher
    }

    fun checkCurrentUser() {
        _currentUser.value = authRepository.getCurrentUser()
    }

    fun handleActivityResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            _signInFailure.value = e
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        authRepository.firebaseAuthWithGoogle(idToken).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _currentUser.value = authRepository.getCurrentUser()
            } else {
                _signInFailure.value = task.exception
            }
        }
    }

    fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        activityResultLauncher.launch(signInIntent)
    }

    companion object {
        private const val TAG = "GoogleSignInViewModel"
    }
}
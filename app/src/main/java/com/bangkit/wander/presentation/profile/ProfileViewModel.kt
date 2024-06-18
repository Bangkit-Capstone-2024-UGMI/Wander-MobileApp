package com.bangkit.wander.presentation.profile

import com.bangkit.wander.data.repository.AuthRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(private val authRepository: AuthRepository) : ViewModel(){

    private val _currentUser = MutableLiveData<FirebaseUser?>(null)
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    init {
        checkCurrentUser()
    }

    fun checkCurrentUser() {
        _currentUser.value = authRepository.getCurrentUser()
    }

    fun signOut() {
        authRepository.signOut()
        checkCurrentUser()
    }

}
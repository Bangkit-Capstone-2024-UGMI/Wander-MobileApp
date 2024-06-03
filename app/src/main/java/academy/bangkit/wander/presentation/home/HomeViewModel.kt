package academy.bangkit.wander.presentation.home

import academy.bangkit.wander.data.repository.AuthRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class HomeViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    fun checkCurrentUser() {
        _currentUser.value = authRepository.getCurrentUser()
    }

    fun signOut() {
        authRepository.signOut()
        checkCurrentUser()
    }
}
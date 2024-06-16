package academy.bangkit.wander.data.repository

import academy.bangkit.wander.R
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository(
    private val authService: FirebaseAuth
) {


    fun getCurrentUser(): FirebaseUser? {
        return authService.currentUser
    }

    fun firebaseAuthWithGoogle(idToken: String) = authService.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun signOut() {
        authService.signOut()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            authService: FirebaseAuth
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(
                    authService = authService,
                )
            }.also { instance = it }
    }
}
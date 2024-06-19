package com.bangkit.wander.presentation.profile


import android.content.Context
import android.content.Intent
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.profile.widgets.ProfileCard
import com.bangkit.wander.presentation.profile.widgets.SettingCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.login.LoginActivity

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val profileViewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Profile",
                showBackButton = false
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(15.dp))
                ProfileCard()
                SettingCard(
                    string = "Name",
                    onClick = { navController.navigate(AppRoute.ACCOUNT_NAME) }
                )
                SettingCard(
                    string = "Email",
                    onClick = { navController.navigate(AppRoute.ACCOUNT_EMAIL) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    profileViewModel.signOut()
                    navigateToLoginActivity(context)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3261E),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Logout", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

private fun navigateToLoginActivity(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
}






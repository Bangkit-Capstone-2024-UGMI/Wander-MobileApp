package academy.bangkit.wander.presentation.myplan.create

import academy.bangkit.wander.R
import academy.bangkit.wander.app.navigation.AppRoute
import academy.bangkit.wander.app.widgets.MyButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SuccessScreen(
    navController: NavHostController
) {
    Scaffold {
        paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp / 2)
                            .aspectRatio(1f)

                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.success_state_plan
                            ),
                            contentDescription = "Success State Plan",
                            modifier = Modifier.fillMaxSize(),

                            )
                    }
                    Text(
                        text = "Your vacation plan is ready!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xCC1D5D9B),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Let the adventure begin.",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xCC1D5D9B),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Box(
                        modifier = Modifier.padding(horizontal = LocalConfiguration.current.screenWidthDp.dp / 4)
                    ) {
                        MyButton(
                            text = "Back to Home",
                            onClick = {
                                navController.navigate(AppRoute.MAIN) {
                                    popUpTo(AppRoute.MAIN) {
                                        inclusive = false
                                    }
                                }
                            }
                        )
                    }
                }
            }
    }
}
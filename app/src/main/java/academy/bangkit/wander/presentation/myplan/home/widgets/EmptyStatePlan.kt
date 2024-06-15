package academy.bangkit.wander.presentation.myplan.home.widgets

import academy.bangkit.wander.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyStatePlan() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp/2)
                    .aspectRatio(1f)

            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.empty_state_plan
                    ),
                    contentDescription = "Empty State Plan",
                    modifier = Modifier.fillMaxSize(),

                )
            }
            Text(
                text = "You don't have a dream vacation yet?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xCC1D5D9B),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Let's plan it & make it happen!",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xCC1D5D9B),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
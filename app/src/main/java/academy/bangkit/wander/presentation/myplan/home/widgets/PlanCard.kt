package academy.bangkit.wander.presentation.myplan.home.widgets

import academy.bangkit.wander.R
import academy.bangkit.wander.app.theme.AppColor
import academy.bangkit.wander.data.model.Plan
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PlanCard(data: Plan) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .shadow(
            elevation = 4.dp,
            spotColor = Color(0x1A08335D),
            ambientColor = Color(0x1A08335D)
        )
        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                AsyncImage(
                    model = data.image,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .weight(1f),
            ) {
                 Text(
                     text = data.name,
                     style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = AppColor.PrimaryDark)
                 )
                 Spacer(modifier = Modifier.size(6.dp))
                 Text(
                     text = data.location,
                     style = TextStyle(fontSize = 14.sp, color = AppColor.PrimaryDark)
                 )
                Spacer(modifier = Modifier.size(6.dp))

                Text(
                    text = data.date,
                    style = TextStyle(fontSize = 14.sp, color = AppColor.PrimaryDark)
                )
            }
             IconButton(
                 onClick = { /*TODO*/ },
                 modifier = Modifier
                     .size(40.dp)
                     .padding(end = 16.dp)
             ) {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_share),
                     contentDescription = "Share Icon",
                     tint = AppColor.PrimaryDark
                 )
             }
        }
    }
}
package com.bangkit.wander.presentation.myplan.create.hotels.widgets

import com.bangkit.wander.app.theme.AppColor
import com.bangkit.wander.data.model.Hotel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun HotelCard(data: Hotel, onClick: () -> Unit){
    val location = data.formattedAddress.split(", ")
    val city = location[location.size - 3]
    val district = location[location.size - 4]

    val formattedDistance = String.format("%.2f", data.distance)

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .shadow(
            elevation = 6.dp,
            spotColor = Color(0x592B2002),
            ambientColor = Color(0x59463005)
        )
        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp)),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = AppColor.PrimaryDark,
            disabledContainerColor = Color(0xFFE0E0E0),
            disabledContentColor = Color(0xFFB0B0B0),
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                AsyncImage(
                    model = "https://www.gstatic.com/webp/gallery/1.jpg",
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .weight(1f),
            ) {
                Text(
                    maxLines = 2,
                    text = data.name,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = AppColor.PrimaryDark)
                )
                Spacer(modifier = Modifier.size(6.dp))

                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    text = "$district, $city",
                    style = TextStyle(fontSize = 14.sp, color = AppColor.PrimaryDark)
                )
                Spacer(modifier = Modifier.size(6.dp))

                Row {
                    Text(
                        text = "$formattedDistance KM ",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = AppColor.PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "from the midpoint",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = AppColor.PrimaryBlue,
                        )
                    )
                }
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription ="rating", tint = AppColor.PrimaryYellow )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "5.0",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = AppColor.PrimaryBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
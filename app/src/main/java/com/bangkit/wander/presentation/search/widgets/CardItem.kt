package com.bangkit.wander.presentation.search.widgets

import com.bangkit.wander.R
import com.bangkit.wander.data.model.Places
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val items = listOf(
    Places(
        placeImage = "s",
        placeTitle = "Hotel Kurniawan",
        placeLocation = "Cianjur"
    ),

    Places(
        placeImage = "s",
        placeTitle = "Hotel Kurniawan",
        placeLocation = "Cianjur"
    ),

    Places(
        placeImage = "s",
        placeTitle = "Hotel Kurniawan",
        placeLocation = "Cianjur"
    ),

    Places(
        placeImage = "s",
        placeTitle = "Hotel Kurniawan",
        placeLocation = "Cianjur"
    ),

    Places(
        placeImage = "s",
        placeTitle = "Hotel Kurniawan",
        placeLocation = "Cianjur"
    )
)

@Composable
fun CardItem(
    index: Int,
    //onClick: () -> Unit
){
    val card = items[index]
    var lastItemPaddingEnd = 0.dp
    if (index == items.size -1){
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.example_location)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp)
            .height(190.dp)
            .clickable {},
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4D160)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = image,
                contentDescription = card.placeImage,
                modifier = Modifier
                    .width(144.dp)
                    .height(114.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = card.placeTitle, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.placeLocation,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}


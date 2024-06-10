package academy.bangkit.wander.presentation.search

import academy.bangkit.wander.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val items = listOf(
    Card(
        cardImage = "s",
        cardTitle = "Cafe Kurniawan",
        cardLocation = "Cianjur"
    ),

    Card(
        cardImage = "s",
        cardTitle = "Cafe Kurniawan",
        cardLocation = "Cianjur"
    ),

    Card(
        cardImage = "s",
        cardTitle = "Cafe Kurniawan",
        cardLocation = "Cianjur"
    ),

    Card(
        cardImage = "s",
        cardTitle = "Cafe Kurniawan",
        cardLocation = "Cianjur"
    ),

    Card(
        cardImage = "s",
        cardTitle = "Cafe Kurniawan",
        cardLocation = "Cianjur"
    )
)

@Preview
@Composable
fun CardSection(){
    LazyRow(){
        items(items.size){index ->
            CardItem(index)
        }
    }

}


@Composable
fun CardItem(
    index: Int
){
    val card = items[index]
    var lastItemPaddingEnd = 0.dp
    if (index == items.size -1){
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.example_place)

    Box(
        modifier =
            Modifier.padding(start = 8.dp, end = lastItemPaddingEnd)
    ){
        Column(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .width(160.dp)
            .height(190.dp)
            .clickable{}
            .padding(vertical = 8.dp, horizontal = 8.dp)
        ){
            Image(
                painter = image,
                contentDescription = card.cardImage,
                Modifier.clip(RoundedCornerShape(10.dp))
                    .height(114.dp)
                    .width(144.dp)

            )

            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = card.cardTitle,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = card.cardLocation,
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }


}


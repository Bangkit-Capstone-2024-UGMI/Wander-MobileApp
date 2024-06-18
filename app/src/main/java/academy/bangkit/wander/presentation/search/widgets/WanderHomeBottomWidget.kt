package academy.bangkit.wander.presentation.search.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomWidgetContent(onExpand: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Latest in the area..",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    onExpand()
                },
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Hotels Nearby",
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardSection()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cafes Nearby",
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardSection()

    }
}
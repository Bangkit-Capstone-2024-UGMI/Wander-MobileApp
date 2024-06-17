package academy.bangkit.wander.presentation.myplan.home.widgets

import academy.bangkit.wander.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x592B2002),
                ambientColor = Color(0x59463005)
            )
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Ready for some adventure?",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF1D5D9B),
                    fontWeight = FontWeight(700),
                )
            )
            OutlinedTextField(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF1D5D9B),
                    unfocusedBorderColor = Color(0xFF1D5D9B),
                    cursorColor = Color(0xFF1D5D9B),
                    containerColor = Color(0x211D5D9B),
                ),
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Explore amazing destinations here", style = TextStyle(color=Color(0xCC1D5D9B))) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        tint = Color(0xFF1D5D9B)
                    )
                },
            )
        }
    }
}
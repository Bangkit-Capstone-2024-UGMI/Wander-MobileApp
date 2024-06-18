package com.bangkit.wander.app.widgets

import com.bangkit.wander.app.theme.AppColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyTextField(
    label : String? = null,
    placeholder: String? = null,
    value : String,
    onValueChange : (String) -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false
) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        if (label != null) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColor.PrimaryDark
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        OutlinedTextField(
            readOnly = readOnly,
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(
                    text = placeholder?:"",
                    style = TextStyle(color = AppColor.PrimaryDarkVariant)
                )
            },
            leadingIcon = icon
            ,
        )
    }
}
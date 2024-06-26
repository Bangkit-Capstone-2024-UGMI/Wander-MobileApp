package com.bangkit.wander.app.widgets

import com.bangkit.wander.app.theme.AppColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    enable: Boolean = true,
    text : String,
    onClick : () -> Unit,
    icon: @Composable (() -> Unit)? = null,
    isSecondary : Boolean = false
) {
    Button(
        enabled = enable,
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        border = if (isSecondary) BorderStroke(1.dp, AppColor.PrimaryYellow) else null,
        colors = ButtonDefaults.buttonColors(
            containerColor  = if (isSecondary) Color(0xFFFFFBF0) else AppColor.PrimaryYellow,
            contentColor = if (isSecondary) AppColor.PrimaryYellow else AppColor.PrimaryDark
        )
    ) {
        Box(modifier =Modifier.padding(4.dp)) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    icon()
                }
                Text(text = text, style = TextStyle(color = if (isSecondary) AppColor.PrimaryYellow else AppColor.PrimaryDark))
            }
        }
    }
}
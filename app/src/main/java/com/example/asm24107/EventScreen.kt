package com.example.asm24107

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun EventScreen(navController: NavHostController) {
    val singledigit= listOf(0,1,2,3,4,5,6,7,8,9)
    LazyColumn {
       items(singledigit){message ->
            ListItem(
                headlineContent = { Text(message.toString()) },
                modifier = Modifier.clickable {
                    navController.navigate("event/$message")
                },
                leadingContent = {

                }
            )
            HorizontalDivider()
        }
    }
}
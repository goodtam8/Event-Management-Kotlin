package com.example.asm24107

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asm24107.ui.theme.Asm24107Theme
import kotlinx.coroutines.launch

@Composable
fun Search(navController: NavHostController) {
    val padding = 16.dp
    var keyword by remember { mutableStateOf("") }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="Type your title")
        TextField(
            maxLines = 1,
            value = keyword,
            onValueChange = { keyword = it }

        )
        Spacer(Modifier.size(padding))


        Button(onClick = {

            navController.navigate("result/${keyword}")//can i only make assumption of only passing
            //title


        }) {
            Text(text = "Search")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    Asm24107Theme {
        Search(rememberNavController())
    }
}
package com.example.asm24107


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asm24107.R
import com.example.asm24107.ui.theme.Asm24107Theme
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun FeedBack(snackbarHostState: SnackbarHostState,navController: NavHostController) {
    val padding = 16.dp
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="Email")
        TextField(
            maxLines = 1,
            value = email,
            onValueChange = { email = it }

        )
        Spacer(Modifier.size(padding))
        Text(text="Password")
        TextField(
            maxLines = 1,
            value = password,
            onValueChange = { password = it },
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Button(onClick = {


//Regular volunteers can only access their own information, making the ID path parameter
// unnecessary for them. Hence, when requesting their own information,
// a dummy value can be used for the ID parameter. Volunteer field is now removed from the result.
            //pagination how to do
            var login=KtorClient.logininfo(email, password)
            coroutineScope.launch {
                if(password==""||email==""){
                    snackbarHostState.showSnackbar("You have not fill the information to login")
                }
                else{
                val stringBody: String = KtorClient.postFeedback(login)
                if(stringBody!="fail"){
                snackbarHostState.showSnackbar("Login successful")
                    items= listOf("Home", "Events", "Search", "User")

                    navController.navigate("user")}
                else{
                    snackbarHostState.showSnackbar("Login fail. Your password or id is wrong!")
                }

            }}
        }) {
            Text(text = "Login")
        }
    }
}
@Composable
fun redirect(navController: NavHostController){
        Column {

                ListItem(
                    headlineContent = { Text("Register to become volunteer") },
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    },

                    leadingContent = {

                    }
                )
            }
        }



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Asm24107Theme  {
        Column {
            LoginScreen(SnackbarHostState(), rememberNavController())
        }
    }
}

@Composable
fun LoginScreen(snackbarHostState: SnackbarHostState,navController: NavHostController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FeedBack(snackbarHostState,navController)
        redirect(navController)
    }
}
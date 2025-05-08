package com.example.asm24107

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.asm24107.ui.theme.Asm24107Theme
var items=listOf("Home", "Events", "Search", "Login")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
        val feeds by produceState(
            initialValue = alldata(
                listOf( Feed(
                "4124142141",
                "test event ",
                "hkbu",
                "error",
                "2023-08-11",
                "no information",
                "https://picsum.photos/seed/2023-11-10T21:34/800/800",
                69,
                true,
                "2021-09-04",
                "2023-12-31",
                listOf("1213131", "asasa")
            )),
                4,
                4,
                4),
            producer = {
                value = KtorClient.gethighlightfeed()
            }
        )

        Scaffold(
        topBar = {
            TopAppBar(

            title = { Text("Event Management System") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                        modifier = Modifier.testTag(item),
                        label = { Text(item) },
                        selected = selectedItem == index,

                        onClick = {
                            selectedItem = index
                            navController.navigate(items[selectedItem]) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                when (selectedItem) {
//                0 -> InfoScreen()
//                1 -> InfoScreen()
//                2 -> InfoScreen()
//                4 -> InfoScreen()
                }
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ) {
                    composable("home") { FeedScreen(feeds,navController) }
                    composable("detail/{_id}") { backStackEntry ->
                        backStackEntry.arguments?.getString("_id")
                            ?.let { DetailScreen(it,snackbarHostState,navController) }}
                    composable("multiplepage/{pagenum}") { backStackEntry ->
                        backStackEntry.arguments?.getString("pagenum")
                            ?.let { MultipleScreen(it,navController) }}
                    composable("search") { Search(navController) }
                    composable("result/{_id}") { backStackEntry ->
                      backStackEntry.arguments?.getString("_id")
                        ?.let { ResultScreen(it,navController) }}
                    composable("events") { EventScreen(navController) }
                    composable("event/{_id}") { backStackEntry ->
                        backStackEntry.arguments?.getString("_id")
                            ?.let { EventDetailScreen(it,navController) }}
                    composable("login") { LoginScreen(snackbarHostState,navController) }
                    composable("register") { RegisterScreen(snackbarHostState,navController) }
                    composable("user") { UserScreen(navController) }
                }


            }
        })


}


@Preview(showBackground = true)
@Composable
fun ScaffoldScreenPreview() {
    Asm24107Theme {
        ScaffoldScreen()
    }
}
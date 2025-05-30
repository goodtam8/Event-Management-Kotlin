package com.example.asm24107

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(kw: String, navHostController: NavHostController) {
    var pagenum by remember {
        mutableIntStateOf(1)
    }
    val coroutineScope = rememberCoroutineScope()

    val feeds by produceState(
        initialValue = alldata(
            listOf(
                Feed(
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
                )
            ),
            4,
            4,
            4
        ), key1 = pagenum,
        producer = {
            value = KtorClient.getSearchwithpageFeeds(kw, pagenum)

        }

    )

    Column {

        Box(modifier = Modifier.weight(0.9f)) {

            LazyColumn {
                items(feeds.events) { feed ->
                    Card(
                        onClick = {
                            navHostController.navigate("detail/${feed._id}")

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        Column {
                            AsyncImage(
                                model = feed.image,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.size(16.dp))

                            HorizontalDivider()
                            Box(Modifier.fillMaxSize()) {
                                Text(
                                    feed._id, Modifier.align(Alignment.Center)
                                )


                            }

                        }
                    }
                }
            }
        }
        Box {

            Row {
                Button(onClick = {
                    navHostController.navigate("search")


                }) {
                    Text(text = "Search")
                }
                if (pagenum != 1) {
                    Button(onClick = {
                        pagenum--


                    }) {
                        Text(text = "Previous Page")
                    }
                }
                if (pagenum <= feeds.total / 6) {

                    Button(onClick = {
                        var c = pagenum++
                        c++


                    }) {
                        Text(text = "Next Page")
                    }

                }
            }
        }
    }
}

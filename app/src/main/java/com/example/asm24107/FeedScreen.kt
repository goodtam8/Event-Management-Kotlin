package com.example.asm24107

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.asm24107.ui.theme.Asm24107Theme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class alldata(
    val events: List<Feed>,
    val total: Int,
    val perPage: Int? = null,
    val page: Int? = null
) {
    companion object {
        val data = listOf(
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
            ),
            4,
            4,
            4
        )
    }
}

@Serializable
data class Feed(
    val _id: String,
    val title: String,
    val organiser: String,
    val description: String,
    val event_date: String,
    val location: String,
    val image: String,
    val quota: Int,
    val highlight: Boolean,
    val createdAt: String,
    val modifiedAt: String,
    val volunteers: List<String>?
) {
    companion object {
        // data class 有兩層
        val data = listOf(
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
            ),
        )
    }
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(feeds: alldata, navHostController: NavHostController) {
    var pagenum = remember {
        1
    }
    val coroutineScope = rememberCoroutineScope()

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
        Box() {
            Button(
                onClick = {
                    pagenum++
                    coroutineScope.launch {

                        navHostController.navigate("multiplepage/$pagenum")


                        //pagination how to do

                    }


                },
            ) {
                Text(text = "Next Page")
            }

        }
    }}


    @Preview(showBackground = true)
    @Composable
    fun FeedPreview() {
        Asm24107Theme {
            println(Feed.data)
            //FeedScreen(alldata.data)
        }
    }
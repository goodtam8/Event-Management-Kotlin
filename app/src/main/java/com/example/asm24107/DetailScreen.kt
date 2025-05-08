package com.example.asm24107

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.asm24107.ui.theme.Asm24107Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import kotlinx.serialization.Serializable

@Serializable
data class detail(
    val _id: String,
    val title: String,
    val organiser: String,
    val description: String,
    val event_date: String,
    val location: String,
    val image: String,
    val quota: Int,
    val highlight: String,
    val createdAt: String,
    val modifiedAt: String,
    val volunteers: List<String>?

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
                "https://picsum.photos/seed/2023-11-10T21:34/800/800".toString(),
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
@Composable
fun detailget(
    id: String,
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val detail by produceState(
            initialValue =
            detail(
                "4124142141",
                "test event ",
                "hkbu",
                "error",
                "2023-08-11",
                "no information",
                "https://picsum.photos/seed/2023-11-10T21:34/800/800".toString(),
                69,
                "true",//error
                "2021-09-04",
                "2023-12-31",
                rememberSaveable {
                    listOf("1213131", "asasa")
                }
            ),

            producer = {
                value = KtorClient.getdetail(id)
            }
        )
//    return detail

//    LazyColumn {
//        item(detail) {
        Card(
            onClick = { /* Do something */ },
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, false)
        ) {
            Box(Modifier.fillMaxSize()) {

                Column {

                    Text("Title:" + detail.title)
                    Text("Organizer:" + detail.organiser)
                    Text("Description:" + detail.description)
                    Text("Date Time:" + detail.event_date)
                    Text("Quota:" + detail.quota.toString())
                    //add column
                    HorizontalDivider()


                }
            }
                Column {

                    Button(onClick = {


                        navHostController.navigate("home")
                    }) {
                        Text(text = "Back to Home")
                    }
                    if (KtorClient.token != "") {
                        val volun by produceState(
                            initialValue =
                            KtorClient.volunteerinfo(
                                "4124142141",
                                "test event ",
                                "hkbu",
                                "error",
                                "2023-08-11",
                                "no information",

                                true,
                                "true",//error
                                "2021-09-04",
                                true,
                                rememberSaveable {
                                    listOf("1213131", "asasa")
                                }
                            ),

                            producer = {
                                value = KtorClient.getvolunteerinfo()
                            }
                        )
                        var joined = checkjoin(volun.events, detail._id)

                        if (detail.quota > 0 && joined == false) {
                            //wait to add when
                            Button(
                                onClick = {


                                    //pagination how to do
                                    coroutineScope.launch {
                                        val stringBody: String = KtorClient.joinevent(detail._id)
                                        snackbarHostState.showSnackbar(stringBody)
                                    }
                                }
                            ) {
                                Text(text = "Join Event")
                            }

                        } else {
                            Button(
                                onClick = {


                                    //pagination how to do
                                    coroutineScope.launch {
                                        val stringBody: String = KtorClient.unregister(detail._id)
                                        snackbarHostState.showSnackbar(stringBody)
                                    }
                                }, modifier = Modifier
                                    .padding(vertical = 200.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Unregister")
                            }
                        }

                    }

                }
            }
        }

}

//        }
//    }
fun checkjoin(myevent: List<String>, detailevent: String): Boolean {
    for (i in 0..myevent.size - 1) {
        if (myevent.get(i).equals(detailevent))
            return true
    }

    return false
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    feeds: String,
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
) {

    detailget(id = feeds, snackbarHostState, navHostController)
}


@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Asm24107Theme {
        println(Feed.data)

        // DetailScreen(detail)
    }
}
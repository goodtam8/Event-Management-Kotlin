package com.example.asm24107


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object KtorClient {
    var token: String = ""
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
        install(Logging)
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", "Bearer " + token)
        }
        expectSuccess = true
    }
    suspend fun getLocationFeeds(location:String,page:Int): alldata {
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/?page=$page&location=$location").body()
        } catch (e: Exception) {

            alldata(
                listOf(
                    Feed(
                        "${e.message}",
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
                ),6,6,1)

        }
    }
    suspend fun getSearchwithpageFeeds(kw:String, page: Int): alldata {
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/?page=$page&search=$kw").body()
        } catch (e: Exception) {

            alldata(
                listOf(
                    Feed(
                        "${e.message}",
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
                ),6,6,1)

        }
    }


    suspend fun getFeeds(): alldata {
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events").body()
        } catch (e: Exception) {

                alldata(
                    listOf(
                    Feed(
                    "${e.message}",
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
                ),6,6,1)

        }
    }
    suspend fun gethighlightfeed(): alldata {
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/?highlight=true").body()
        } catch (e: Exception) {

            alldata(
                listOf(
                    Feed(
                        "${e.message}",
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
                ),6,6,1)

        }
    }

    suspend fun getvolunteerFeeds():alldata{
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/volunteers/31321/events").body()
        } catch (e: Exception) {

            alldata(
                listOf(
                    Feed(
                        "${e.message}",
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
                ),6,6,1)

        }

    }
    suspend fun getvolunteerinfo():volunteerinfo{
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/volunteers/xxx").body()
        } catch (e: Exception) {

           volunteerinfo(
               "4321",
               "${e.message}",
               "asa",
               "dasda"
               ,"sasa",
               "sasa",
               true,
               "sasa",
               "wewa",
               true,
               listOf("12367")
           )

        }

    }
    suspend fun getpageFeeds(pagenum:String): alldata {
        return try {
            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/?page=$pagenum&highlight=true").body()
        } catch (e: Exception) {

            alldata(
                listOf(
                    Feed(
                        "${e.message}",
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
                ),6,6,1)

        }
    }
    suspend fun getdetail(id:String):detail{//how to call this function?

        return httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/$id").body()
//        return try {
////            httpClient.get("https://comp4107-spring2024.azurewebsites.net/api/events/$id").body()
////        }
////        catch (e: Exception) {
////                detail(
////                    "4124142141",
////                    "test event ",
////                    "${e.message}",
////                    "error",
////                    "2023-08-11",
////                    "no information",
////                    "https://picsum.photos/seed/2023-11-10T21:34/800/800",
////                    69,
////                    "true",
////                    "2021-09-04",
////                    "2023-12-31",
////                    listOf("1213131", "asasa")
////
////            )
////        }
    }


    suspend fun postFeedback(feedback: logininfo): String {
try{
        val response: loginresponse =
            httpClient.post("https://comp4107-spring2024.azurewebsites.net/api/login") {
                setBody(feedback)
            }.body()



        token = response.token
        return response.toString()}
    catch (e:Exception){
        var errormessage="fail"
    return errormessage
}

    }
    suspend fun joinevent(eventid: String): String {

        val response:String =//don't know the format
            httpClient.post("https://comp4107-spring2024.azurewebsites.net/api/events/$eventid/volunteers") {
             //.set body?
            }.body()



        //token = response.token
        return response.toString()

    }
    suspend fun postRegister(feedback:volunteer): String {

        //val response: registerresponse = httpClient.post("https://httpbin.org/post") {

        val response: registerresponse = httpClient.post("https://comp4107-spring2024.azurewebsites.net/api/volunteers/") {
            setBody(feedback)
        }.body()

        //token = response.headers["X-Amzn-Trace-Id"].toString()
        return response.toString()
    }
    suspend fun unregister(delete:String): String {

        //val response: registerresponse = httpClient.post("https://httpbin.org/post") {

        val response: String = httpClient.delete("https://comp4107-spring2024.azurewebsites.net/api/events/$delete/volunteers") {

        }.body()

        //token = response.headers["X-Amzn-Trace-Id"].toString()
        return response.toString()
    }
    //response 要對返岩
    @Serializable
    data class logininfo(
        val email: String,
        val password: String
    )
    @Serializable
    data class registerresponse(
        val id: ID,

    )
    @Serializable
    data class ID(
        val acknowledged: Boolean,
        val insertedId:String

        )
    @Serializable
    data class joinreponse(//change wt
        val sawa:String
    )
    @Serializable
    data class loginresponse(
       val token:String
    )
    @Serializable
    data class volunteer(
        val email: String,
        val password: String,
        val name: String,
        val contact:String,
        val age_group: String,
        val about: String,
        val terms: String,
    )
  @Serializable
  data class volunteerinfo(
      val _id:String,
      val email:String,
      val name:String,
      val contact:String,
      val age_group:String,
      val about:String,
      val terms:Boolean,
      val createdAt:String,
      val modifiedAt:String,
      val isAdmin:Boolean,
      val events:List<String>


  )
}



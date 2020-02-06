package com.shreyas.fcmtestingadmin.service

import com.shreyas.fcmtestingadmin.Noti.NotiResponse
import com.shreyas.fcmtestingadmin.Noti.NotifiactionModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @POST("fcm/send")
    fun sendNoti(@Body model: NotifiactionModel): Call<NotiResponse>


//    companion object {
//        operator fun invoke(): ApiService {
//            val httpClient = OkHttpClient.Builder()
//            httpClient.addInterceptor { chain ->
//                val original = chain.request()
//                val builder = original.newBuilder()
//                builder.addHeader("Authorization", "key=AIzaSyDkMA6phEJIjD3J5yAyw9ZH4RD57_zvu_0")
//                builder.addHeader("Content-Type", "application/json")
//                val request = builder.build()
//                chain.proceed(request)
//            }
////            val interceptor = HttpLoggingInterceptor()
////            val logLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
////            interceptor.level = logLevel//BODY->to show logs//NONE->viceversa
////
////            httpClient.addInterceptor(interceptor)
//            httpClient.connectTimeout(20, TimeUnit.SECONDS)
//            httpClient.readTimeout(30, TimeUnit.SECONDS)
//            val client = httpClient.build()
//            return Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(RetrofirBuilder.BASE_URL)
//                .client(client)
//                .build()
//                .create(ApiService::class.java)
//        }
//    }


}
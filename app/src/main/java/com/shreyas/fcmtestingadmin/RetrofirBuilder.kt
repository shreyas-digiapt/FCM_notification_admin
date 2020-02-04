package com.shreyas.fcmtestingadmin

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor.Companion.invoke
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


object RetrofirBuilder {

    const val BASE_URL = "https://fcm.googleapis.com/"

    val gson = GsonBuilder()
        .registerTypeAdapter(HttpUrl::class.java, UrlDeserializer())
        .create()

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(StringConverterFactory())
            .client(getOkHttp())
    }

    fun getOkHttp() : OkHttpClient {
        val loggingInterPretor = HttpLoggingInterceptor()
        loggingInterPretor.level = HttpLoggingInterceptor.Level.HEADERS

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(invoke {
                chain: Interceptor.Chain ->
                chain.proceed(getRequest(chain)!!)
            }).addNetworkInterceptor(loggingInterPretor)
            .build()
        return okHttpClient
    }

    private fun getRequest(chain: Interceptor.Chain): Request? {
        val original = chain.request()
        try {
            val request:Request
            request = original.newBuilder()
                .header("Authorization", "key=AIzaSyDkMA6phEJIjD3J5yAyw9ZH4RD57_zvu_0")
                .header("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()
            return request
        }catch (e:Exception) {
            Log.d("RetrofirBuilder1", "asd: ${e.message}")
        }
        return null
    }

    val apiService:ApiService by lazy {
        retrofitBuilder.build()
            .create(ApiService::class.java)
    }


    internal class StringConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> {
            return Converter<ResponseBody, String> { value -> value.string() }
        }

        override fun requestBodyConverter(
            type: Type,
            parameterAnnotations: Array<Annotation>,
            methodAnnotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<*, RequestBody>? {
            if (TypeToken.get(type).rawType.isAssignableFrom(String()::class.java)) {
                return Converter<String?, RequestBody> { value ->
                    value?.let {
                        RequestBody.create(
                            "text/plain".toMediaTypeOrNull(),
                            it
                        )
                    }
                }
            }
            return null
        }
    }

    class UrlDeserializer : JsonDeserializer<HttpUrl> {
        override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): HttpUrl =
            json.asString.toHttpUrl()

    }
}

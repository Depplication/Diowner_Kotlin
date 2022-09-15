package kr.hs.diowner

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
    var api: API
    private var serverIP: String = "10.80.162.117:8082/"

    init {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://" + serverIP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(API::class.java)
    }
}
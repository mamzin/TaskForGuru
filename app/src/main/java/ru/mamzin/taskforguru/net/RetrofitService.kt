package ru.mamzin.taskforguru.net

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.mamzin.taskforguru.model.BestSeller
import ru.mamzin.taskforguru.model.HomeStore
import ru.mamzin.taskforguru.model.ModelResponseStore

interface RetrofitService {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    fun getHomeStore(): Call<ModelResponseStore<List<HomeStore>>>

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    fun getBestSeller(): Call<ModelResponseStore<List<BestSeller>>>

    companion object {
        private var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
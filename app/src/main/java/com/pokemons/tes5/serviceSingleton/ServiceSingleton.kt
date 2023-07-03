package com.pokemons.tes5.serviceSingleton

import com.pokemons.tes5.interfaces.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceSingleton private constructor() {

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService =  retrofit.create(ApiService::class.java)
    }

    companion object{
        private var instance: ServiceSingleton? = null

        fun getInstance() : ServiceSingleton {
            if (instance == null){
                instance = ServiceSingleton()
            }
            return instance as ServiceSingleton
        }
    }

    fun getApiService() :ApiService{
        return apiService
    }

}
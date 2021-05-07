package pe.edu.upc.myapplication.data.remote.user

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiClient{
    private const val API_BASE_URL = "https://cubiv1.herokuapp.com/"
    private var userApiService:UserApiService? = null

    fun build(): UserApiService?{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userApiService = retrofit.create(UserApiService::class.java)

        return userApiService as UserApiService
    }

}
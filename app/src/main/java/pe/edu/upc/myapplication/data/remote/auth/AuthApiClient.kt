package pe.edu.upc.myapplication.data.remote.auth

import pe.edu.upc.myapplication.data.remote.user.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthApiClient {

    private const val API_BASE_URL = "https://cubiv1.herokuapp.com/"
    private var authApiService: AuthApiService? = null

    fun build(): AuthApiService?{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApiService = retrofit.create(AuthApiService::class.java)

        return authApiService as AuthApiService
    }
}
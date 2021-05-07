package pe.edu.upc.myapplication.data.remote.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth")
    fun authenticate(@Body authRequest: AuthRequest):Call<AuthResponse>

}
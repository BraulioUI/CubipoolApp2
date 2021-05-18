package pe.edu.upc.myapplication.data.remote

import pe.edu.upc.myapplication.data.remote.auth.AuthRequest
import pe.edu.upc.myapplication.data.remote.auth.AuthResponse
import pe.edu.upc.myapplication.data.remote.user.UserRequest
import pe.edu.upc.myapplication.data.remote.user.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //USER
    @POST("auth")
    fun authenticate(@Body authRequest: AuthRequest): Call<AuthResponse>

    @POST("users")
    fun postUser(@Body userRequest: UserRequest): Call<UserResponse>

}
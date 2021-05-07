package pe.edu.upc.myapplication.data.remote.user

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("users")
    fun postUser(@Body userRequest:UserRequest): Call<UserRequest>
}
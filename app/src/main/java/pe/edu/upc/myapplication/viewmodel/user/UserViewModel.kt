package pe.edu.upc.myapplication.viewmodel.user
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import pe.edu.upc.myapplication.data.remote.user.UserApiClient
import pe.edu.upc.myapplication.data.remote.user.UserRequest
import pe.edu.upc.myapplication.data.remote.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel(){

    var message = MutableLiveData<String>()

    var isCorrect = MutableLiveData<Boolean>()

    fun regis(code:String,name:String,lastName:String,password:String){
        val register = UserRequest(code,name,lastName,password)
        val userResponse = UserApiClient.build()?.postUser(register)
        userResponse?.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    isCorrect.value = true

                }else{
                    when(response.code()){
                        400 -> {
                            message.value = "Datos invalidos"
                        }
                        409 -> {
                            message.value = "usuarío ya registrado"
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                message.value = "GGAAAAAAA"
            }


        })
    }
}
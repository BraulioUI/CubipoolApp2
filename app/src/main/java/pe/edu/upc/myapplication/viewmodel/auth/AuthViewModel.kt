package pe.edu.upc.myapplication.viewmodel.auth

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.myapplication.data.remote.auth.AuthApiClient
import pe.edu.upc.myapplication.data.remote.auth.AuthRequest
import pe.edu.upc.myapplication.data.remote.auth.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel: ViewModel() {


    private var authMutableLiveData = MutableLiveData<AuthRequest>()
    var message: String = "fallo"

    fun getmessage(): String {
        return message
    }


    fun onClick(code:String,password:String){
        val login = AuthRequest(code,password)

        authMutableLiveData.value = login

        auth()
    }

    private fun auth(){

       val authResponse = AuthApiClient.build()?.authenticate(authMutableLiveData.value!!)

       authResponse?.enqueue(object: Callback<AuthResponse> {
           override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
               if (response.isSuccessful){
                   val res = response.body()
                   Log.i("PruebaLogin: ",res?.code!!)
                   message= "Ingreso correctamente"
                   Log.i("MESSAGE1: ", message)
               }else{
                   when(response.code()){
                       404 -> {
                           message = "Credenciales Inválidas"
                           Log.i("MESSAGE2: ", message)
                       }
                   }
               }
           }

           override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
               Log.i("PruebaLogin","Algo salio mal")
               message = "Algo salio mal"
               Log.i("MESSAGE3: ", message)
           }

       })
   }

}

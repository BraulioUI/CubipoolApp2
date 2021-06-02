package pe.edu.upc.myapplication.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.myapplication.data.remote.ApiClient
import pe.edu.upc.myapplication.data.remote.reservation.UserReservationsAvailables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationViewModel : ViewModel() {

    var qReservation = MutableLiveData<Int>()

    private val code = "u202120211"

    fun getQuantityReservationsAvailable(){

        val getQReservation = ApiClient.build()?.getReservationsAvailables(code)

        getQReservation?.enqueue(object : Callback<ArrayList<UserReservationsAvailables>>{
            override fun onResponse(
                call: Call<ArrayList<UserReservationsAvailables>>,
                response: Response<ArrayList<UserReservationsAvailables>>
            ) {
                qReservation.postValue(response.body()?.size)
            }

            override fun onFailure(
                call: Call<ArrayList<UserReservationsAvailables>>,
                t: Throwable
            ) {
                Log.i("VM ERROR: ","No se pudo cargar las reservas")
            }


        })

    }

}
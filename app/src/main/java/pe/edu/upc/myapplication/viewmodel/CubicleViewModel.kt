package pe.edu.upc.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.myapplication.data.entities.Cubicle
import pe.edu.upc.myapplication.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CubicleViewModel : ViewModel(){

    private val cubiclesAvailables: List<Cubicle> = ArrayList()
    var _cubiclesAvailables = MutableLiveData<List<Cubicle>>()

    private var _dateForSubmit = MutableLiveData<String>()

    private var _date = MutableLiveData<String>()
    private var _hour = MutableLiveData<String>()
    private var _qHours = MutableLiveData<String>()

    var firstTimeSelected = MutableLiveData<Boolean>()
    var isCubicleSelected = MutableLiveData<Boolean>()
    var lastCubicle = MutableLiveData<Cubicle>()

    fun startVariables(date: String,hour:String,qHours:String){
        _date.value = date
        _hour.value = hour
        _qHours.value = qHours
    }

    private fun startDateSubmit(){
        updateDate(_date.value!!)
    }

    private fun updateDate(date: String) {
        if (date == "Hoy"){
            _dateForSubmit.value = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        }else if(date == "Mañana"){
            _dateForSubmit.value = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_DATE)
        }
    }

    fun searchAllCubicle(){
        val findAllCubicle = ApiClient.build()?.findAllAvailableCubicles(_date.value!!,_hour.value!!,
            _qHours.value!!)

        findAllCubicle?.enqueue(object : Callback<ArrayList<Cubicle>>{
            override fun onResponse(
                call: Call<ArrayList<Cubicle>>,
                response: Response<ArrayList<Cubicle>>
            ) {
                if (response.isSuccessful){
                    _cubiclesAvailables.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Cubicle>>, t: Throwable) {
                Log.i("CViewModel: ", "ERROR")
            }

        })
    }

    fun reservationSubmit(){

    }
}
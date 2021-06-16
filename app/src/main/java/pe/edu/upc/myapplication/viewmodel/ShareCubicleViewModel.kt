package pe.edu.upc.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.myapplication.data.remote.ApiClient
import pe.edu.upc.myapplication.data.remote.offer.CreateOfferReservation
import pe.edu.upc.myapplication.data.remote.offer.UpdateOfferModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShareCubicleViewModel : ViewModel(){

    var reservationId = MutableLiveData<Int>()
    var offerId = MutableLiveData<Int>()
    var appleTv = MutableLiveData<Boolean>()
    var board = MutableLiveData<Boolean>()
    var seat = MutableLiveData<Int>()
    var isOfferDetail = MutableLiveData<Boolean>()

    var isEdit = MutableLiveData<Boolean>()
    var isToReservation = MutableLiveData<Boolean>()

    private var seatsAvailable: MutableList<Int> = ArrayList()
    var _seatsAvailable = MutableLiveData<MutableList<Int>>()

    var isOfferDeleted = MutableLiveData<Boolean>()

    fun createOffer(){

        val offerUpdate = UpdateOfferModel(appleTv.value!!,board.value!!,seat.value!!)
        val offerCreate = CreateOfferReservation(reservationId.value!!,appleTv.value!!,board.value!!,seat.value!!)


        Log.i("ShareVM0: ",offerCreate.reservaId.toString())
        Log.i("ShareVM0: ",offerCreate.apple.toString())
        Log.i("ShareVM0: ",offerCreate.pizarra.toString())
        Log.i("ShareVM0: ",offerCreate.sitios.toString())


        if (isEdit.value == true){
            val update = ApiClient.build()?.updateOffer(offerId.value!!, offerUpdate)
            update?.enqueue(object: Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful){
                        isToReservation.value = true
                    }else{
                        Log.i("ShareVM: ","There is a problem!")

                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("ShareVM: ","FAIL!")
                }

            })
        }else if (isEdit.value == false){
            val create = ApiClient.build()?.createOfferReservation(offerCreate)
            create?.enqueue(object : Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful){
                        isToReservation.value = true
                    }else{
                        Log.i("ShareVM2: ","There is a problem!")
                        Log.i("ShareVM2: ", response.message())
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("ShareVM2: ","FAIL!")
                }

            })
        }

    }

    fun initSeatsAvailable(){

        if (_seatsAvailable.value == null) {
            seatsAvailable.add(1)
            seatsAvailable.add(2)
            seatsAvailable.add(3)
            seatsAvailable.add(4)

            _seatsAvailable.postValue(seatsAvailable)
        }
    }

    fun getOfferDetail(){
        val getOffer = ApiClient.build()?.findOfferById(offerId.value!!)

        getOffer?.enqueue(object : Callback<CreateOfferReservation>{
            override fun onResponse(
                call: Call<CreateOfferReservation>,
                response: Response<CreateOfferReservation>
            ) {
                if (response.isSuccessful){
                    isOfferDetail.value = true
                    appleTv.value = response.body()?.apple!!
                    board.value = response.body()?.pizarra!!
                    seat.value = response.body()?.sitios!!

                    Log.i("ShareVM3: ",appleTv.value.toString())
                    Log.i("ShareVM3: ",board.value.toString())
                    Log.i("ShareVM3: ",seat.value.toString())
                }else{
                    Log.i("ShareVM3: ","There is a Problem!")
                }
            }

            override fun onFailure(call: Call<CreateOfferReservation>, t: Throwable) {
                Log.i("ShareVM3: ","FAIL!")
            }

        })
    }

    fun deleteOffer(){
        val delete = ApiClient.build()?.deleteOffer(offerId.value!!)

        delete?.enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful){
                    isOfferDeleted.value = true
                }else{
                    Log.i("ShareVM4: ","There is a Problem!")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("ShareVM4: ","FAIL!")
            }

        })
    }

}
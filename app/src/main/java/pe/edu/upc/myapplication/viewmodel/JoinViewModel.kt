package pe.edu.upc.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import pe.edu.upc.myapplication.data.remote.ApiClient
import pe.edu.upc.myapplication.data.remote.offer.CreateJoinOffer
import pe.edu.upc.myapplication.data.remote.offer.CreateOfferResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinViewModel : ViewModel() {

    val joinCubiclesOffer = MutableLiveData<List<CreateOfferResponse>>()

    val createJoinOffer = MutableLiveData<CreateJoinOffer>()

    var appleTv = MutableLiveData<Boolean>()
    var _board = MutableLiveData<Boolean>()

    fun initJoinCubiclesOffer(){

        val getAllOffers = ApiClient.build()?.findAllOffers()

        getAllOffers?.enqueue(object : Callback<ArrayList<CreateOfferResponse>>{
            override fun onResponse(
                call: Call<ArrayList<CreateOfferResponse>>,
                response: Response<ArrayList<CreateOfferResponse>>
            ) {
                if (response.isSuccessful){
                    joinCubiclesOffer.postValue(response.body())
                }else{
                    Log.i("JoinVM1: ","There is a problem!")
                }
            }

            override fun onFailure(call: Call<ArrayList<CreateOfferResponse>>, t: Throwable) {
                Log.i("JoinVM1: ","FAIL!")
            }

        })

    }


    fun joinOffer(code:String,offerId:Int){

        Log.i("OFFERVM: ",code)
        Log.i("OFFERVM: ",offerId.toString())

        if (_board.value == null){
            _board.value = false
        }

        if (appleTv.value == null){
            appleTv.value = false
        }

        Log.i("OFFERVM2: ",_board.value!!.toString())
        Log.i("OFFERVM2: ",appleTv.value!!.toString())

        val _createJoinOffer = CreateJoinOffer(code,appleTv.value!!,_board.value!!,offerId)

        val join = ApiClient.build()?.joinOffer(_createJoinOffer)

        join?.enqueue(object:Callback<JSONObject>{
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful){
                    createJoinOffer.postValue(_createJoinOffer)
                }else{
                    Log.i("JoinVM2: ","There is a problem!")
                }
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Log.i("JoinVM2: ","FAIL!")
            }

        })

    }

}
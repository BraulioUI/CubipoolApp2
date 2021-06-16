package pe.edu.upc.myapplication.data.remote.offer

import com.google.gson.annotations.SerializedName

class CreateJoinOffer (

    @SerializedName("codigo")
    var code:String,

    @SerializedName("apple")
    var appleTv: Boolean,

    @SerializedName("pizarra")
    var board: Boolean,

    @SerializedName("ofertaId")
    var offerId: Int

)
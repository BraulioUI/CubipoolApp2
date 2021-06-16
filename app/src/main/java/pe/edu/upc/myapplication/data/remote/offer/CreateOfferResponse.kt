package pe.edu.upc.myapplication.data.remote.offer

import com.google.gson.annotations.SerializedName

class CreateOfferResponse (

    @SerializedName("cubiculoNombre")
    var cubicleName:String,

    @SerializedName("horaInicio")
    var hourInit: String,

    @SerializedName("horaFin")
    var hourEnd: String,

    @SerializedName("apple")
    var appleTv: Boolean,

    @SerializedName("pizarra")
    var board: Boolean,

    @SerializedName("sitios")
    var seats: Int,

    @SerializedName("tema")
    var theme: String,

    @SerializedName("offerId")
    var offerId:Int

)